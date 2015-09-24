package chapter3;

import java.util.ArrayList;
import java.util.List;

public class Solution03a<T> {
    // Here, we cannot choose to pop from a stack; only the stack to which the last element was added is popped.
    // When we add an element and the current stack is already full, we add another stack. When we pop an element
    // from a stack leading to the stack being empty, we remove a stack if there is already an empty stack. In other
    // words, when popping elements, we maintain one extra empty stack. This is done to improve performance.
    List<FixedSizeStack<T>> setOfStacks;
    int currentStackPosition;
    int emptyStackPosition;
    int stackSize;

    public Solution03a(int stackSize) {
        setOfStacks = new ArrayList<>();
        this.stackSize = stackSize;
        currentStackPosition = -1;
        emptyStackPosition = -1;
        obtainEmptyStack();
    }

    private void obtainEmptyStack() {
        if(emptyStackPosition == -1) {
            FixedSizeStack<T> stack = new FixedSizeStack<>(stackSize);
            setOfStacks.add(stack);
            currentStackPosition++;
        } else {
            currentStackPosition = emptyStackPosition;
            emptyStackPosition = -1;
        }
    }

    public void push(T data) {
        if(!setOfStacks.get(currentStackPosition).push(data)) {
            obtainEmptyStack();
            setOfStacks.get(currentStackPosition).push(data);
        }
    }

    public T pop() {
        FixedSizeStack<T> currentStack = setOfStacks.get(currentStackPosition);
        T data = currentStack.pop();
        if(currentStack.isEmpty()) {
            if(emptyStackPosition == -1) {
                if(currentStackPosition != 0) {
                    emptyStackPosition = currentStackPosition;
                    currentStackPosition--;
                }
            } else {
                if(currentStackPosition != 0) {
                    setOfStacks.remove(emptyStackPosition);
                    emptyStackPosition = currentStackPosition;
                    currentStackPosition--;
                }
            }
        }
        return data;
    }

    public T peek() {
        return setOfStacks.get(currentStackPosition).peek();
    }

    public boolean isEmpty() {
        if(currentStackPosition == 0 && setOfStacks.get(currentStackPosition).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfStacks() {
        return setOfStacks.size();
    }

    public static void main(String[] args) {
        Solution03a<Integer> setOfStacks = new Solution03a<>(3);
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Inserting 3...");
        setOfStacks.push(3);
        System.out.println("Inserting 7...");
        setOfStacks.push(7);
        System.out.println("Inserting 8...");
        setOfStacks.push(8);
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Inserting 13...");
        setOfStacks.push(13);
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Inserting 14...");
        setOfStacks.push(14);
        System.out.println("Inserting 18...");
        setOfStacks.push(18);
        System.out.println("Inserting 21...");
        setOfStacks.push(21);
        System.out.println("Inserting 23...");
        setOfStacks.push(23);
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Popped: " + setOfStacks.pop());
        System.out.println("Number of stacks: " + setOfStacks.getNumberOfStacks());
    }
}