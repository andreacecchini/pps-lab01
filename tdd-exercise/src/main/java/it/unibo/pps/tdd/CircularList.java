package it.unibo.pps.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CircularList implements CircularQueue {
    final List<Integer> buffer;
    int front = 0;
    int rear = 0;
    int size = 0;
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
            this.buffer.set(this.rear, valueToEnqueue);
            this.front = (this.front + 1) % this.capacity;
        } else {
            this.buffer.addLast(valueToEnqueue);
            this.size++;
        }
        this.rear = (this.rear + 1) % this.capacity;
    }

    @Override
    public Optional<Integer> dequeue() {
        try {
            final var firstOfTheQueue = Optional.ofNullable(this.buffer.set(this.front, null));
            if (firstOfTheQueue.isPresent()) {
                this.size--;
                this.front = (this.front + 1) % this.capacity;
            }
            return firstOfTheQueue;
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
