package com.flink.scala.stream.window;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;


/**
 * 原文可以参考:https://blog.csdn.net/xsdxs/article/details/82415450<br/>
 *
 * 关于flink的watermark的描述可以参考:
 * https://blog.csdn.net/lmalds/article/details/52704170<br/>
 * 或者http://www.imooc.com/article/252967
 *
 */
public class TimeWindowDemo {

    public static void main(String[] args) throws Exception {
        long delay = 5100L;
        int windowSize = 10;

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置数据源
        env.setParallelism(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStream<Tuple3<String, String, Long>> dataStream = env.addSource(new TimeWindowDemo.DataSource()).name("Demo Source");

        // 设置水位线
        DataStream<Tuple3<String, String, Long>> watermark = dataStream.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Tuple3<String, String, Long>>() {
            private final long maxOutOfOrderness = delay;
            private long currentMaxTimestamp = 0L;
            private Watermark waterMark = null;
            @Override
            public Watermark getCurrentWatermark() {
                waterMark =  new Watermark(currentMaxTimestamp - maxOutOfOrderness);

                return waterMark;
            }

            @Override
            public long extractTimestamp(Tuple3<String, String, Long> element, long previousElementTimestamp) {
                long timestamp = element.f2;
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                System.out.println(element.f1 + " -> " + timestamp+",waterMark:"+waterMark);
                currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
                //System.out.println(getCurrentWatermark());
                return timestamp;
            }
        });

        // 窗口函数进行处理
        /*
        DataStream<Tuple3<String, String, Long>> resStream = watermark.keyBy(0).timeWindow(Time.seconds(windowSize)).reduce(
                new ReduceFunction<Tuple3<String, String, Long>>() {
                    @Override
                    public Tuple3<String, String, Long> reduce(Tuple3<String, String, Long> value1, Tuple3<String, String, Long> value2) throws Exception {
                        return Tuple3.of(value1.f0, value1.f1 + "" + value2.f1, 1L);
                    }
                }
        );
        */
        DataStream resStream =  watermark.keyBy(0).timeWindow(Time.seconds(windowSize)).apply(new WindowFunction<Tuple3<String, String, Long>, Object, Tuple, TimeWindow>() {

            @Override
            public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple3<String, String, Long>> input, Collector<Object> out) throws Exception {
                int sum = 0;
                for(Tuple3<String,String,Long> item:input){
                    System.out.println(item.f0+"-"+item.f1+"-"+item.f2);
                    sum+=Integer.parseInt(item.f1);
                }
                System.out.println("sum="+sum);
                out.collect(new Tuple2<Long,Long>(window.getStart(),window.getEnd()));
            }
        });
        resStream.print();

        env.execute("event time demo");
    }

    private static class DataSource extends RichParallelSourceFunction<Tuple3<String, String, Long>> {
        private volatile boolean running = true;

        @Override
        public void run(SourceFunction.SourceContext<Tuple3<String, String, Long>> ctx) throws InterruptedException {
            Tuple3[] elements = new Tuple3[]{
                    Tuple3.of("a", "1", 1000000050000L),
                    Tuple3.of("a", "2", 1000000054000L),
                    Tuple3.of("a", "3", 1000000079900L),
                    Tuple3.of("a", "4", 1000000115000L),
                    Tuple3.of("b", "5", 1000000100000L),
                    Tuple3.of("b", "6", 1000000108000L)
            };

            int count = 0;
            while (running && count < elements.length) {
                ctx.collect(new Tuple3<>((String) elements[count].f0, (String) elements[count].f1, (Long) elements[count].f2));
                count++;
                Thread.sleep(1000);
            }
        }

        @Override
        public void cancel() {
            running = false;
        }
    }

}
