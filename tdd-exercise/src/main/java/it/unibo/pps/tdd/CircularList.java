package it.unibo.pps.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CircularList implements CircularQueue {
    final List<Integer> buffer;
    final int capacity;

    public CircularList(int capacity) {
        this.capacity = capacity;
        this.buffer = new ArrayList<>(this.capacity);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return this.size() == this.capacity;
    }

    @Override
    public int size() {
        return this.buffer.size();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public Optional<Integer> peek() {
        try {
            return Optional.ofNullable(this.buffer.getFirst());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public void enqueue(int valueToEnqueue) {
        this.buffer.addLast(valueToEnqueue);
    }

    @Override
    public Optional<Integer> dequeue() {
        try {
            return Optional.ofNullable(this.buffer.removeFirst());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
