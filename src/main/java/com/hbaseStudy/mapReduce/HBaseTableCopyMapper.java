package com.hbaseStudy.mapReduce;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * 拷贝test表到testCopy表的{@link Mapper}<br/>
 * 只拷贝列名比b小的列
 *
 * @author chenwu on 2020.3.28
 */
public class HBaseTableCopyMapper extends TableMapper<ImmutableBytesWritable,Put> {

    private String compareName;

        protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        compareName = "b";
    }

    @Override
    protected void map(ImmutableBytesWritable key, Result result, Context context) throws IOException, InterruptedException {
        if (result == null || result.listCells() == null) {
            return;
        }
        byte[] rowKey = key.get();
        List<Cell> cells = result.listCells();
        for (Cell cell : cells) {
            String columName = Bytes.toString(CellUtil.cloneQualifier(cell));
            if (columName.compareTo(compareName) <= 0) {
                Put newPut = new Put(rowKey);
                newPut.addColumn(CellUtil.cloneFamily(cell), Bytes.toBytes(columName), CellUtil.cloneValue(
                        cell
                ));
                context.write(key, newPut);
            }
        }
    }

}
