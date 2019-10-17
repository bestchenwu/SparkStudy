package com.hadoopStudy.hadoopIO;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 实现一对Text组成的Writable
 *
 * @author  chenwu on 2019.10.17
 */
public class TextPairWritable implements WritableComparable<TextPairWritable> {

    private Text first;
    private Text second;

    public TextPairWritable(){
        first = new Text();
        second = new Text();
    }

    public TextPairWritable(String first,String second){
        this.first = new Text(first) ;
        this.second = new Text(second);
    }

    @Override
    public int hashCode() {
        return first.hashCode()*31+second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TextPairWritable)){
            return false;
        }
        TextPairWritable textPairWritable = (TextPairWritable)obj;
        return first.equals(textPairWritable.first)&&second.equals(textPairWritable.second);
    }

    @Override
    public int compareTo(TextPairWritable o) {
        int firstCompairResult = first.compareTo(o.first);
        return firstCompairResult!=0?firstCompairResult:second.compareTo(o.second);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first.readFields(in);
        second.readFields(in);
    }
}
