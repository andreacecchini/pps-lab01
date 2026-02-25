package it.unibo.pps.tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    final Stack<Integer> stack = new Stack<>();
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    @Override
    public void push(int value) {
        if (value > this.max) {
            this.max = value;
        }
        if (value < min) {
            this.min = value;
        }
        this.stack.push(value);
    }

    @Override
    public int pop() {
        checkEmpty();
        return this.stack.pop();
    }

    @Override
    public int peek() {
        checkEmpty();
        return this.stack.peek();
    }

    @Override
    public int getMin() {
        checkEmpty();
        return this.min;
    }

    @Override
    public int getMax() {
        checkEmpty();
        return this.max;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    private void checkEmpty() {
        if (this.stack.isEmpty()) {
            throw new IllegalStateException();
        }
    }
}
