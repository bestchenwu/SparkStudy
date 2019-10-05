package com.java8Study.summary;
import java.io.File;
import java.io.FileFilter;

/**
 * 将函数作为值的练习
 *
 * @author chenwu on 2019.10.5
 */
public class FuntionAsValue {

    public static void main(String[] args){
        //java8的写法
        //这里把File的isHidden方法当做值传递进来
        File[] hiddenFiles = new File("").listFiles(File::isHidden);
        //java7及以下的写法
        File[] hiddenFiles1 = new File("").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
    }
}
