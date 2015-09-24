package chapter3;

public class FixedSizeStack<T> {
    SinglyLinkedList<T> stack;
    int numberOfElements;
    int stackSize;
    private static final int DEFAULT_CAPACITY = 10;

    public FixedSizeStack() {
        stack = new SinglyLinkedList<>();
        this.stackSize = DEFAULT_CAPACITY;
        numberOfElements = 0;
    }

    public FixedSizeStack(int stackSize) {
        stack = new SinglyLinkedList<>();
        this.stackSize = stackSize;
        numberOfElements = 0;
    }

    public boolean push(T data) {
        if(numberOfElements == stackSize) {
            return false;
        } else {
            stack.addFirst(data);
            numberOfElements++;
            return true;
        }
    }

    public T pop() {
        T data = stack.removeFirst();
        if(data != null) {
            numberOfElements--;
        }
        return data;
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
        FixedSizeStack<Integer> integerStack = new FixedSizeStack<>(4);
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
