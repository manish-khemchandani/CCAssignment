package chapter3;

public class Solution02 {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public Solution02() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int data) {
        if(min() >= data) {
            minStack.push(data);
        }
        stack.push(data);
    }

    public Integer pop() {
        Integer data = stack.pop();
        if(data != null && min() == data) {
            minStack.pop();
        }
        return data;
    }

    public int min() {
        if(minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return minStack.peek();
        }
    }

    public Integer peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution02 integerStack = new Solution02();
        integerStack.push(5);
        integerStack.push(7);
        integerStack.push(4);
        integerStack.push(6);
        integerStack.push(8);
        System.out.println(integerStack.peek());
        System.out.println(integerStack.min());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.min());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.min());
        System.out.println(integerStack.isEmpty());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.min());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.min());
        System.out.println(integerStack.isEmpty());
    }
}
