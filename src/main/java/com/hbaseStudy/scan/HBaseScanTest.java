package com.hbaseStudy.scan;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * hbase scan测试类
 */
public class HBaseScanTest {

    public static void main(String[] args) throws Exception{
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Table table = hBaseClient.getTable();
        Configuration conf = hBaseClient.getConf();
        Scan scan = new Scan();
        //设置缓存大小,缓存大小面向行级别的
        scan.setCaching(500);
        //设置批处理大小,面向列级别的,一次性返回多少列
        scan.setBatch(100);
        ResultScanner scanner = table.getScanner(scan);
        long scanTimeoutPeriod = conf.getLong(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, 100l);
        Thread.sleep(scanTimeoutPeriod+5000l);
        try{
            while(true){
                Result result = scanner.next();
                if(result!=null){
                    System.out.println(result);

                }else{
                    break;
                }
            }

        }catch(Exception e){
            //会输出NotServingRegionException
            e.printStackTrace();
        }
        scanner.close();
    }
}
