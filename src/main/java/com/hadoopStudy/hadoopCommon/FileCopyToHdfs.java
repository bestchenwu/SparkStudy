package com.hadoopStudy.hadoopCommon;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

public class FileCopyToHdfs {

    public static void main(String[] args) throws IOException {
        String src = args[0];
        String dest = args[1];
        File file = new File(src);
        if(!file.exists()){
            throw new IllegalStateException(src+" is not existed");
        }
        FileInputStream fis = new FileInputStream(new File(src));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dest),conf);
        FSDataOutputStream fdos = fs.create(new Path(dest), () -> System.out.print("."));
        IOUtils.copyBytes(fis,fdos,2048,true);
    }
}
