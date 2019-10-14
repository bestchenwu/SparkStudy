package com.java8Study.unit12_new_time_api;



import java.time.LocalDate;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.List;

/**
 * 获得下一个工作日
 *
 * @author chenwu on 2019.10.14
 */
public class NextWorkingDay implements TemporalAdjuster {

    private List<Integer> WORKDAY_LIST = Arrays.asList(1,2,3,4,5);

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal nextWorkingday = temporal;
        if(nextWorkingday == null){
            nextWorkingday  = LocalDate.now();
        }
        do{
            nextWorkingday = nextWorkingday.plus(1l, ChronoUnit.DAYS);
        }while(!WORKDAY_LIST.contains(nextWorkingday.get(ChronoField.DAY_OF_WEEK)));
        return nextWorkingday;
    }
}
