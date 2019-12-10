package com.common;

import java.io.IOException;
import java.util.List;

public class CommonFileUtilTest {

    public static void main(String[] args) {
        try{
            List<String> list = CommonFileUtil.readFile("D:\\logs\\xiaohongshu_user.txt",true);
            CommonFileUtil.writeFile("D:\\logs\\xiaohongshu_user_distinct.txt",list);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}
