package it.unibo.pps.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A {@link CircularQueue} implementation based on {@link List}.
 */
public class CircularList implements CircularQueue {
    private final List<Integer> buffer;
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    private final int capacity;

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
        return size() == getCapacity();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public Optional<Integer> peek() {
        try {
            return Optional.ofNullable(this.buffer.get(this.front));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public void enqueue(int valueToEnqueue) {
        if (isFull()) {
            overwriteFront(valueToEnqueue);
        } else {
            append(valueToEnqueue);
        }
        nextRear();
    }

    @Override
    public Optional<Integer> dequeue() {
        try {
            final var previousFirst = removeAndRetrieveIfPresent();
            if (previousFirst.isPresent()) {
                this.size--;
                nextFront();
            }
            return previousFirst;
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    private Optional<Integer> removeAndRetrieveIfPresent() {
        return Optional.ofNullable(this.buffer.set(this.front, null));
    }

    private void overwriteFront(int valueToEnqueue) {
        this.buffer.set(this.rear, valueToEnqueue);
        nextFront();
    }

    private void append(int valueToEnqueue) {
        this.buffer.addLast(valueToEnqueue);
        this.size++;
    }

    private void nextFront() {
        this.front = (this.front + 1) % this.capacity;
    }

    private void nextRear() {
        this.rear = (this.rear + 1) % this.capacity;
    }
}
