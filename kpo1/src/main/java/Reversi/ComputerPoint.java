package Reversi;

import java.util.ArrayList;
import java.util.List;

public class ComputerPoint {
    private final int value1;

    private final int value2;

    private List<String> keys;

    private int count;

    public ComputerPoint(int value1, int value2, List<String> keys, int count) {
        this.value1 = value1;
        this.value2 = value2;
        //this.keys = new ArrayList<String>();
        this.keys = keys;
        this.count = count;
    }

    public int first() {
        return value1;
    }

    public int second() {
        return value2;
    }

    public List<String> keys() {
        return keys;
    }

    public int count() {
        return count;
    }

    public void addcount(int a) {
        this.count = this.count + a;
    }

    public void addkey(String str) {
        keys.add(str);
    }
}
