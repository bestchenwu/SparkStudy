package com.hadoopStudy.highFeature;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * todo:自定义的IntPair怎么读取
 */
@Deprecated
public class SelfPartitionOutputReader {

    public static void main(String[] args) {
        try{
            Configuration conf = new Configuration();
            Path inputPath = new Path("hdfs://127.0.0.1:9000/user/chenwu/hadoop1/year_temperatrue/");
            FileSystem fs = FileSystem.get(inputPath.toUri(),conf);
            RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(inputPath, true);
            while(listFiles.hasNext()){
                LocatedFileStatus fileStatus = listFiles.next();
                if(fileStatus.isDirectory()||fileStatus.getLen()==0){
                    continue;
                }
                Path path0 = fileStatus.getPath();
                SequenceFile.Reader reader = new SequenceFile.Reader(conf,SequenceFile.Reader.file(path0));
                IntPair key = (IntPair)ReflectionUtils.newInstance(reader.getKeyClass(),conf);
                NullWritable value = (NullWritable)ReflectionUtils.newInstance(reader.getValueClass(),conf);
                while(reader.next(key,value)){
                    System.out.println("year:"+key.getFirst()+",temperature:"+key.getSecond());
                }
                IOUtils.closeStream(reader);
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }


    }
}
