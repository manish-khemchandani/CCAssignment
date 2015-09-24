package chapter3;

public class Solution05<T extends Comparable<T>> {
    public Stack<T> sortStack(Stack<T> stack) {
        Stack<T> sortedStack = new Stack<>();
        T stackPopped;
        while((stackPopped = stack.pop()) != null) {
            T sortedPopped;
            while((sortedPopped = sortedStack.pop()) != null && sortedPopped.compareTo(stackPopped) > 0) {
                stack.push(sortedPopped);
            }
            if(sortedPopped != null) {
                sortedStack.push(sortedPopped);
            }
            sortedStack.push(stackPopped);
        }
        return sortedStack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(13);
        stack.push(4);
        stack.push(12);
        stack.push(17);
        Solution05<Integer> stackSorter = new Solution05<>();
        Stack<Integer> sortedStack = stackSorter.sortStack(stack);
        System.out.println("Popped: " + sortedStack.pop());
        System.out.println("Popped: " + sortedStack.pop());
        System.out.println("Popped: " + sortedStack.pop());
        System.out.println("Popped: " + sortedStack.pop());
    }
}
