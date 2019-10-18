package com.hadoopStudy.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;

import java.util.Map;

/**
 * 读取configuration
 *
 * @author chenwu on 2019.10.18
 */
public class ConfigurationPointer extends Configured implements Tool {

    public ConfigurationPointer(Configuration conf ){
        super(conf);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        for(Map.Entry<String,String> entry : conf){
            System.out.println(String.format("key=%s,value=%s",entry.getKey(),entry.getValue()));
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.addResource("hadoop/hadoop-cluster.xml");
        ConfigurationPointer configurationPointer = new ConfigurationPointer(conf);
        configurationPointer.run(args);
    }
}
