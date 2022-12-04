package Reversi;

public class Point {
    private final int value1;
    private final int value2;
    private final String key;

    private final int count;

    public Point(int value1, int value2, String key, int count) {
        this.value1 = value1;
        this.value2 = value2;
        this.key = key;
        this.count = count;
    }

    public int first() {
        return value1;
    }

    public int second() {
        return value2;
    }

    public String key() {
        return key;
    }

    public int count() {
        return count;
    }
}