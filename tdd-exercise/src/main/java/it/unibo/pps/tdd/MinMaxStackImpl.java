package it.unibo.pps.tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    final Stack<Integer> stack = new Stack<>();
    final Stack<Integer> maxStack = new Stack<>();
    final Stack<Integer> minStack = new Stack<>();

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
        updateMinAfterPush(value);
        updateMaxAfterPush(value);
    }

    private void updateMinMaxAfterPop() {
        updateMinAfterPop();
        updateMaxAfterPop();
    }

    private void updateMinAfterPush(int value) {
        int currentMin = this.minStack.isEmpty() ? value : getMin();
        this.minStack.push(Math.min(value, currentMin));
    }

    private void updateMaxAfterPush(int value) {
        int currentMax = this.maxStack.isEmpty() ? value : getMax();
        this.maxStack.push(Math.max(value, currentMax));
    }

    private void updateMinAfterPop() {
        this.minStack.pop();
    }

    private void updateMaxAfterPop() {
        this.maxStack.pop();
    }
}
