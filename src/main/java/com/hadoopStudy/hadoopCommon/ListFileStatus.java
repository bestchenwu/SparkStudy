package com.hadoopStudy.hadoopCommon;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 展示文件状态
 */
public class ListFileStatus {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(args[0]), conf);
        List<Path> pathList = IntStream.range(0,args.length).mapToObj(i->new Path(args[i])).collect(Collectors.toList());
        Path[] paths = pathList.toArray(new Path[pathList.size()]);
        FileStatus[] statuses = fileSystem.listStatus(paths);
        Path[] pathsList = FileUtil.stat2Paths(statuses);
        Arrays.stream(pathsList).forEach(System.out::println);
    }
}
