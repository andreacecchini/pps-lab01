package it.unibo.pps.tdd;

public class CircularList implements CircularQueue {
    public CircularList(int capacity) {
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return 0;
    }
}
