package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

    private List<NestedInteger> list = new ArrayList<>();
    private Integer num;

    public NestedInteger() {

    }

    ;

    public NestedInteger(int value) {
        this.num = value;
    }

    public boolean isInteger() {
        return num != null;
    }

    public Integer getInteger() {
        return num;
    }

    public void setInteger(int value) {
        this.num = value;
    }

    public void add(NestedInteger ni) {
        this.list.add(ni);
    }

    public List<NestedInteger> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        if (num != null && list.size() > 0) {
            return "NestedInteger{num=" + num + ",list=" + list + "}";
        } else if (num != null) {
            return "NestedInteger{num=" + num + "}";
        } else {
            return "NestedInteger{list=" + list + "}";
        }
    }
}
