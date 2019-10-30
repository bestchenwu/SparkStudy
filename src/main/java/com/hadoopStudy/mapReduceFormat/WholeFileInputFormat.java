package com.hadoopStudy.mapReduceFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 不分割文件,文件整体读入
 *
 * @author  chenwu on 2019.10.29
 */
public class WholeFileInputFormat extends FileInputFormat<NullWritable, BytesWritable> {

    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false;
    }

    @Override
    public RecordReader<NullWritable, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        WholeFileRecordReader reader = new WholeFileRecordReader();
        reader.initialize(split, context);
        return reader;
    }
}

/**
 * 不切分文件的读入
 *
 * @author  chenwu on 2019.10.29
 */
class WholeFileRecordReader extends RecordReader<NullWritable, BytesWritable>{

    private FileSplit fileSplit;
    private Configuration conf;
    private BytesWritable bytesWritable;
    private boolean isProcessed;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.fileSplit = (FileSplit)split;
        this.conf = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        if(!isProcessed){
            int length = (int)fileSplit.getLength();
            byte[] content = new byte[length];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Path path = fileSplit.getPath();
            FileSystem fs = FileSystem.get(path.toUri(),conf);
            IOUtils.readFully(fs.open(path),content,0,length);
            bytesWritable.set(content,0,length);
            isProcessed = true;
        }
        return bytesWritable;
    }



    @Override
    public float getProgress() throws IOException, InterruptedException {
        return isProcessed?1.0f:0f;
    }

    @Override
    public void close() throws IOException {

    }
}
