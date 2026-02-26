package it.unibo.pps.tdd;

public class CircularList implements CircularQueue {
    final int capacity;

    public CircularList(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
