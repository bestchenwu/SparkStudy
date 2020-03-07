package com.hbaseStudy.common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * hbase统一操作的工具类
 *
 * @author chenwu on 2020.3.7
 */
public class HBaseClient {

    private transient Connection connection;
    private transient Admin admin;
    private transient TableName table;

    public HBaseClient(String hbaseSiteXml,String tableName) throws IOException {
        Configuration conf = new Configuration();
        conf.addResource(hbaseSiteXml);
        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
        table = TableName.valueOf(tableName);
    }

    public void queryWithMultiVersions(String rowkey,String family,String columnName,int maxVersions){
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(columnName));
        if(maxVersions>0){
            get = get.readVersions(maxVersions);
        }

    }
}
