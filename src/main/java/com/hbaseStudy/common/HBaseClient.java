package com.hbaseStudy.common;

import com.common.constants.CacheConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.util.Triple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * hbase统一操作的工具类
 *
 * @author chenwu on 2020.3.7
 */
public class HBaseClient {

    private transient Connection connection;
    private transient Admin admin;
    private transient Table table;

    /**
     * 第一个参数为hbase的配置文件
     * 第二个参数为表名
     *
     * @param hbaseSiteXml
     * @param tableName
     * @throws IOException
     * @author chenwu on 2020.3.7
     */
    public HBaseClient(String hbaseSiteXml,String tableName) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource(hbaseSiteXml);
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
        table = connection.getTable(TableName.valueOf(tableName));
    }

    /**
     * 获取多个版本的row和value
     *
     * @param rowkey
     * @param family
     * @param columnName
     * @param maxVersions
     * @return List<Triple<String,String,Long>>
     * @throws IOException
     * @author chenwu on 2020.3.7
     */
    public List<Triple<String,String,Long>> queryWithMultiVersions(String rowkey, String family, String columnName, int maxVersions) throws IOException{
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(columnName));
        if(maxVersions>0){
            get = get.readVersions(maxVersions);
        }
        Result result = table.get(get);
        List<Triple<String,String,Long>> rowkeyValueAndVersionList = new ArrayList<>();
        if(result==null||result.isEmpty()){
            return rowkeyValueAndVersionList;
        }
        List<Cell> columnCells = result.getColumnCells(Bytes.toBytes(family), Bytes.toBytes(columnName));
        if(CollectionUtils.isNotEmpty(columnCells)){
            for(Cell cell : columnCells){
                String row = Bytes.toString(CellUtil.cloneRow(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                Long timestamp = cell.getTimestamp();
                rowkeyValueAndVersionList.add(Triple.create(row,value,timestamp));
            }
        }
        return rowkeyValueAndVersionList;
    }

    /**
     * 增加一个列簇
     *
     * @param newFamily
     * @throws IOException
     * @author chenwu on 2020.3.7
     */
    public void addNewColumnFamily(String newFamily) throws IOException{
        admin.disableTable(table.getName());
        ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.of(newFamily);
        admin.addColumnFamily(table.getName(),columnFamilyDescriptor);
        admin.enableTable(table.getName());
    }

    /**
     * 修改一个列簇,这里尝试将它的最大版本变成4,同时设置过期时间为1填
     *
     * @param updateFamily
     * @throws IOException
     * @author chenwu on 2020.3.7
     */
    public void updateColumnFamily(String updateFamily) throws IOException{
        admin.disableTable(table.getName());
        ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.of(updateFamily);
        ColumnFamilyDescriptorBuilder builder = ColumnFamilyDescriptorBuilder.newBuilder(columnFamilyDescriptor);
        builder.setMaxVersions(4).setTimeToLive(CacheConstants.ExpirSeconds_1_day);
        admin.modifyColumnFamily(table.getName(),builder.build());
        admin.enableTable(table.getName());
    }

    /**
     * 批量查询rowkey
     *
     * @param row
     * @param family
     * @param columnName
     * @throws Exception
     * @return Map<String,String> rowkey和value的对应关系
     * @author chenwu on 2020.3.8
     */
    public Map<String,String> multiGet(List<String> row,String family,String columnName) throws Exception{
        List<Get> gets = row.stream().map(rowkey -> {
           Get get =  new Get(Bytes.toBytes(rowkey));
           get.addColumn(Bytes.toBytes(family),Bytes.toBytes(columnName));
           return get;
        }).collect(Collectors.toList());
        Object[]    results = new Object[gets.size()];
        table.batch(gets,results);
        Map<String,String> rowkeyValueMap = new HashMap<>();
        for(Object object:results){
            Result result = (Result)object;
            if(result.isEmpty()){
               continue;
            }
            String rowkey = Bytes.toString(result.getRow());
            String value = Bytes.toString(result.getValue(Bytes.toBytes(family),Bytes.toBytes(columnName)));
            rowkeyValueMap.put(rowkey,value);
        }
        return rowkeyValueMap;
    }

    /**
     * 扫描指定列簇和列
     *
     * @param family
     * @param columnName
     * @throws IOException
     * @author chenwu on 2020.3.10
     */
    public Map<String,String> scanTable(String family,String columnName) throws IOException {
        ResultScanner scanner = table.getScanner(Bytes.toBytes(family), Bytes.toBytes(columnName));
        Map<String,String> map = new HashMap<>();
        try{
            for(Result result :scanner){
                String row = Bytes.toString(result.getRow());
                String columnValue = Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(columnName)));
                map.put(row,columnValue);
            }
        }finally {
            scanner.close();
        }
        return map;
    }
}
