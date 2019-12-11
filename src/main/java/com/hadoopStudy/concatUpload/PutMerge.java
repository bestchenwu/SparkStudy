package com.hadoopStudy.concatUpload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/**
 * 功能：在向HDFS上传文件的过程中，进行合并文件
 * @author ZhuXY
 * @time   2016-3-7 上午9:24:13
 */
public class PutMerge {
    /**
     * 复制上传文件，并将文件合并
     * @param localDir：本地上传的文件目录
     * @param hdfsFile：HDFS上传文件的名称，包括路径
     */
    public static void putMerge(String localDir,String hdfsFile) {
        //从hdfsFile里面hdfs://10.40.11.13:8020/user/hive/warehouse提取出hdfs://10.40.11.13:8020
        String fsDefaultName = hdfsFile.substring(0,hdfsFile.indexOf("8020"))+"8020";
        //1)获取配置信息
        Configuration conf=new Configuration();
        conf.set("fs.default.name", fsDefaultName);
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.file.impl","org.apache.hadoop.fs.LocalFileSystem");
        //2)路径
        Path localPath=new Path(localDir);
        Path hdfsPath=new Path(hdfsFile);

        try {
            //获取本地文件系统
            FileSystem localFs=FileSystem.getLocal(conf);

            //获取HDFS文件系统
            FileSystem hdfs=FileSystem.get(conf);

            FileStatus[] fileStatus=localFs.listStatus(localPath);

            //打开HDFS文件的输出流
            FSDataOutputStream outputStream=hdfs.create(hdfsPath);

            //循环遍历本地文件
            for (FileStatus fiStatus:fileStatus) {
                //获取文件
                Path path=fiStatus.getPath();

                System.out.println("文件为"+path.getName());

                //打开文件的输入流
                FSDataInputStream inputStream=localFs.open(path);

                //进行流的读写操作
                byte[] buffer =new byte[1024];

                int len=0;
                while ((len=inputStream.read(buffer))>0) {
                    outputStream.write(buffer,0,len);

                }
                inputStream.close();
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String localDir=args[0];
        //String hdfsFile="hdfs://10.40.11.13:8020/user/hive/warehouse/ods.db/idata_xiaohongshu_user/dt=2019-12-10/xiaohongshuUser.data";
        String hdfsFile = args[1];
        //String fsDefaultName = hdfsFile.substring(0,hdfsFile.indexOf("8020"))+"8020";
        putMerge(localDir, hdfsFile);
    }
}
