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
        return 0;
    }

    @Override
    public int peek() {
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
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return 0;
    }
}
