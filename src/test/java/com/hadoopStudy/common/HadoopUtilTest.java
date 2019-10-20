package com.hadoopStudy.common;

import com.hadoopStudy.hadoopCommon.HadoopUtil;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class HadoopUtilTest {

    @Test
    public void testDeletePath(){
        HadoopUtil.deletePath(new Path("/user/chenwu/hadoop/birthday_result.txt"));
    }
}
