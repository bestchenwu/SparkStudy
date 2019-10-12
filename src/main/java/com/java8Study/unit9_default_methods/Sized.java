package com.java8Study.unit9_default_methods;

public interface Sized {

    public int getSize();

    /**
     * jdk1.8中的新特性,允许在接口里定义默认方法<br/>
     * 这一点和scala里的特质很相似
     *
     * @return
     */
    default int getDefaultSize(){
        return 0;
    }
}
