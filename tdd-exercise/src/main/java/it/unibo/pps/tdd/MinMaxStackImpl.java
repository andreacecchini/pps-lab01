package it.unibo.pps.tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    final Stack<Integer> stack = new Stack<>();
    final Stack<Integer> maxStack = new Stack<>();
    final Stack<Integer> minStack = new Stack<>();
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    @Override
    public void push(int value) {
        this.stack.push(value);
        updateMinMaxAfterPush(value);
    }

    @Override
    public int pop() {
        checkEmpty();
        int poppedValue = this.stack.pop();
        updateMinMaxAfterPop();
        return poppedValue;
    }

    @Override
    public int peek() {
        checkEmpty();
        return this.stack.peek();
    }

    @Override
    public int getMin() {
        checkEmpty();
        return this.minStack.peek();
    }

    @Override
    public int getMax() {
        checkEmpty();
        return this.maxStack.peek();
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

    private void updateMinMaxAfterPush(int value) {
        int currentMax = this.maxStack.isEmpty() ? value : getMax();
        int currentMin = this.minStack.isEmpty() ? value : getMin();
        if (value >= currentMax) {
            this.maxStack.push(value);
        } else {
            this.maxStack.push(getMax());
        }
        if (value <= currentMin) {
            this.minStack.push(value);
        } else {
            this.minStack.push(getMin());
        }
    }

    private void updateMinMaxAfterPop() {
        this.maxStack.pop();
        this.minStack.pop();
    }
}
