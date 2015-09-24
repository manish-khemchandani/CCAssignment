package chapter3;

public class Solution04<T> {
    // We maintain a queue using two stacks. The newestFirstStack is used when enqueuing an element.
    // The oldestFirstStack is used when dequeuing an element.
    Stack<T> newestFirstStack;
    Stack<T> oldestFirstStack;

    public Solution04() {
        newestFirstStack = new Stack<>();
        oldestFirstStack = new Stack<>();
    }

    public void enqueue(T data) {
        if(data == null) {
            return;
        }
        newestFirstStack.push(data);
    }

    public T dequeue() {
        if(oldestFirstStack.isEmpty()) {
            T popped;
            while((popped = newestFirstStack.pop()) != null) {
                System.out.println("Shifting " + popped + " from newestFirstStack to oldestFirstStack...");
                oldestFirstStack.push(popped);
            }
        }
        return oldestFirstStack.pop();
    }

    public static void main(String[] args) {
        Solution04<Integer> queueUsingStacks = new Solution04<>();
        System.out.println("Enqueuing 13");
        queueUsingStacks.enqueue(13);
        System.out.println("Enqueuing 75");
        queueUsingStacks.enqueue(75);
        System.out.println("Enqueuing 83");
        queueUsingStacks.enqueue(83);
        System.out.println("Dequeued " + queueUsingStacks.dequeue());
        System.out.println("Dequeued " + queueUsingStacks.dequeue());
        System.out.println("Enqueuing 24");
        queueUsingStacks.enqueue(24);
        System.out.println("Enqueuing 62");
        queueUsingStacks.enqueue(62);
        System.out.println("Dequeued " + queueUsingStacks.dequeue());
        System.out.println("Dequeued " + queueUsingStacks.dequeue());
        System.out.println("Dequeued " + queueUsingStacks.dequeue());
    }
}
