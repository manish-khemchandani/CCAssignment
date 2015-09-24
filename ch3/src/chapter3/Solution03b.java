package chapter3;

import java.util.ArrayList;
import java.util.List;

public class Solution03b<T> {
    List<FixedSizeStack<T>> setOfStacks;
    int currentStackPosition;
    int lastStackPosition;
    int stackSize;

    public Solution03b(int stackSize) {
        setOfStacks = new ArrayList<>();
        this.stackSize = stackSize;
        setOfStacks.add(new FixedSizeStack<T>(stackSize));
        currentStackPosition = 0;
        lastStackPosition = 0;
    }

    public void push(T data) {
        while(!setOfStacks.get(currentStackPosition).push(data)) {
            currentStackPosition++;
            if(currentStackPosition == setOfStacks.size()) {
                FixedSizeStack<T> stack = new FixedSizeStack<>(stackSize);
                setOfStacks.add(stack);
            }
        }
    }

    public T pop() {
        FixedSizeStack<T> currentStack = setOfStacks.get(currentStackPosition);
        T data = currentStack.pop();
        if(currentStack.isEmpty()) {
            if(currentStackPosition != 0) {
                setOfStacks.remove(currentStackPosition);
                currentStackPosition--;
            }
        }
        return data;
    }

    public T popAt(int index) {
        if(index > setOfStacks.size() - 1) {
            return null;
        }
        FixedSizeStack<T> currentStack = setOfStacks.get(index);
        T data = currentStack.pop();
        if(currentStackPosition > index) {
            currentStackPosition = index;
        }
        if(currentStackPosition == index && currentStack.isEmpty()) {
            if(currentStackPosition != 0) {
                setOfStacks.remove(currentStackPosition);
                currentStackPosition--;
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
        Solution03b<Integer> setOfStacks = new Solution03b<>(3);
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
        System.out.println("Popped stack 1: " + setOfStacks.popAt(1));
        System.out.println("Inserting 33...");
        setOfStacks.push(33);
        System.out.println("Inserting 75...");
        setOfStacks.push(75);
        System.out.println("Popped: " + setOfStacks.pop());
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