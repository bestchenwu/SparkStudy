package com.MultiThread2rdVersion.Chapter1_thread_api;

class Test{

    public void a(){
        b();
    }

    public void b(){
        c();
    }
    public void c(){
        d();
    }
    public void d(){
        e1();
    }

//    public void e(){
//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        if(stackTrace!=null){
//            for(StackTraceElement element : stackTrace){
//                System.out.println("element class name:"+element.getClassName()+",method name:"+element.getMethodName()+",filename:"+element.getFileName()+",lineNumber:"+element.getLineNumber());
//            }
//        }
//    }

    public void e1(){
        //将堆栈信息输出到标准错物流
        Thread.dumpStack();
    }
}

public class StackTraceElementTest {

    public static void main(String[] args) {
        Test test = new Test();
        test.a();
    }
}
