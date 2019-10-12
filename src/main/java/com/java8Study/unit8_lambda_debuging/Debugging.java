package com.java8Study.unit8_lambda_debuging;

import java.util.Arrays;
import java.util.List;

public class Debugging {

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);
        //运行这段程序会输出
        /**
         * Exception in thread "main" java.lang.NullPointerException
         * 	at com.java8Study.unit8_lambda_debuging.Debugging.lambda$main$0(Debugging.java:10)
         * 	at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:193)
         * 	at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
         * 	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
         * 	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
         * 	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
         * 	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
         * 	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
         * 	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
         * 	at com.java8Study.unit8_lambda_debuging.Debugging.main(Debugging.java:10)
         */
        //无法从错误信息里推断出是哪一行，只能通过加日志调试
    }
}
