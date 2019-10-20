package com.hadoopStudy.hadoopCommon;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * hadoop的工具类
 *
 * @author chenwu on 2019.10.20
 */
public class HadoopUtil {

    public static final Configuration configuration = new Configuration();

    /**
     * 删除路径
     *
     * @param path
     */
    public static void deletePath(Path path){
       try(FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000/"),configuration);){
           fs.delete(path,true);
       }catch(IOException e){
           throw new RuntimeException(e);
       }


    }
}
