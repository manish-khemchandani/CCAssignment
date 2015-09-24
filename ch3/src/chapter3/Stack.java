package chapter3;

public class Stack<T> {
    SinglyLinkedList<T> stack;

    public Stack() {
        stack = new SinglyLinkedList<>();
    }

    public void push(T data) {
        stack.addFirst(data);
    }

    public T pop() {
        return stack.removeFirst();
    }

    public T peek() {
        if(!stack.isEmpty()) {
            return stack.first.data;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(15);
        integerStack.push(12);
        integerStack.push(8);
        integerStack.push(17);
        integerStack.push(17);
        System.out.println(integerStack.pop());
        System.out.println(integerStack.peek());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.isEmpty());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());
        System.out.println(integerStack.isEmpty());
    }
}
