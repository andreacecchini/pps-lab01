package it.unibo.pps.tdd;

import java.util.Optional;

/**
 * Task 3 - TDD for Circular Queue
 * A simple CircularQueue that stores integers with a **fixed** capacity.
 * When full, new elements overwrite the oldest ones.
 * <br>
 * When removing elements, the oldest ones are removed first.
 * Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 * <br>
 * For the exercise:
 * - Think about the test cases you need to write.
 * - Introduce methods in the interface in order to make the tests pass.
 * - Refactor
 */
public interface CircularQueue {
    /**
     *
     * @return whether the queue is empty.
     */
    boolean isEmpty();

    /**
     *
     * @return whether the queue has reach the capacity.
     */
    boolean isFull();

    /**
     *
     * @return the number of elements inside the queue.
     */
    int size();

    /**
     *
     * @return the queue capacity.
     */
    int getCapacity();

    /**
     * Retrieves, but does not remove, the first element of the queue.
     *
     * @return an {@link Optional} containing the first element, if present.
     */
    Optional<Integer> peek();

    /**
     * Enqueue a new value.
     *
     * @param valueToEnqueue
     *         the value to be enqueued.
     */
    void enqueue(int valueToEnqueue);

    /**
     * Removes and returns the first element of the queue.
     *
     * @return an {@link Optional} containing the first element of the queue, if present.
     */
    Optional<Integer> dequeue();
}
