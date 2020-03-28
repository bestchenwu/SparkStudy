package com.hbaseStudy.mapReduce;

import com.sun.corba.se.spi.ior.Writeable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 拷贝test表到testCopy表的{@link Reducer}
 *
 * @author chenwu on 2020.3.28
 */
public class HBaseTableCopyReducer extends TableReducer<ImmutableBytesWritable, Put, ImmutableBytesWritable> {

    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        if (values == null) {
            return;
        }
        for (Put put : values) {
            context.write(key, put);
        }
    }

}
