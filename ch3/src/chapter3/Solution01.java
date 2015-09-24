package chapter3;

public class Solution01 {
    // In this approach, we divide the entire array space into 3 equal chunks which are used as 3 separate arrays.
    private final int NUMBER_OF_STACKS = 3;
    private int capacityPerStack;
    private int[] stackValues;
    int[] stackTops;

    public Solution01(int capacityPerStack) {
        this.capacityPerStack = capacityPerStack;
        stackValues = new int[capacityPerStack * NUMBER_OF_STACKS];
        stackTops = new int[NUMBER_OF_STACKS];
        for(int stackNumber = 0; stackNumber < NUMBER_OF_STACKS; stackNumber++) {
            stackTops[stackNumber] = stackNumber * capacityPerStack;
        }
    }

    public boolean push(int data, int stackNumber) {
        if(stackNumber < 0 || stackNumber > 2 || isFull(stackNumber)) {
            return false;
        }
        stackValues[stackTops[stackNumber]++] = data;
        return true;
    }

    public int pop(int stackNumber) {
        if(stackNumber < 0 || stackNumber > 2 || isEmpty(stackNumber)) {
            return Integer.MIN_VALUE;
        }
        return stackValues[--stackTops[stackNumber]];
    }

    public int peek(int stackNumber) {
        if(stackNumber < 0 || stackNumber > 2 || isEmpty(stackNumber)) {
            return Integer.MIN_VALUE;
        }
        return stackValues[stackTops[stackNumber] - 1];
    }

    public boolean isFull(int stackNumber) {
        return stackTops[stackNumber] == (stackNumber + 1) * capacityPerStack;
    }

    public boolean isEmpty(int stackNumber) {
        return stackTops[stackNumber] == stackNumber * capacityPerStack;
    }

    public static void main(String[] args) {
        Solution01 stacks = new Solution01(3);
        System.out.println("Pushing 7 onto stack 1: " + stacks.push(7, 1));
        System.out.println("Pushing 8 onto stack 2: " + stacks.push(8, 2));
        System.out.println("Pushing 3 onto stack 1: " + stacks.push(3, 1));
        System.out.println("Pushing 5 onto stack 1: " + stacks.push(5, 1));
        System.out.println("Pushing 9 onto stack 1: " + stacks.push(9, 1));
        System.out.println("Popping from stack 1: " + stacks.pop(1));
        System.out.println("Popping from empty stack 0: " + stacks.pop(0));
        System.out.println("Peeking at stack 2: " + stacks.peek(2));
    }
}
