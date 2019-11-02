package com.hadoopStudy.highFeature;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 输出{@link SelfParitionMapper}的{@link IntPair}
 *
 * @author chenwu on 2019.11.1
 */
public class SelfPartitionReducer extends Reducer<IntPair, NullWritable, NullWritable, Text> {

    @Override
    protected void reduce(IntPair key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(NullWritable.get(),new Text(key.getFirst()+ SymbolConstants.SYMBOL_TAB+key.getSecond()));
    }
}
