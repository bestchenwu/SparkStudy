package com.hadoopStudy.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.junit.Assert;

/**
 * 测试Hadoop的{@link org.apache.hadoop.conf.Configuration}读写配置文件<br/>
 * 内部使用的org.w3c.Document类来完成读写
 *
 * @author  chenwu on 2019.10.18
 */
public class ConfigurationTest {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("hadoop/configuration.xml");
        Assert.assertEquals("yellow",conf.get("color"));
        Assert.assertEquals(10,conf.getInt("size",0));
        Assert.assertEquals("wide",conf.get("breadth","wide"));
    }
}
