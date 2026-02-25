package it.unibo.pps.tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    final Stack<Integer> stack = new Stack<>();

    @Override
    public void push(int value) {
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
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
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
