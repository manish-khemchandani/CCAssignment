package solution04;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Partition a linked list around a value x, such that all nodes
// less than x come before all nodes greater than or equal to x.
public class Solution04 {
    // In our approach, we create a second linked list to store values larger than or equal to the partitioning value.
    // Whenever we encounter such a value, we remove it from the first LL and insert it into the second.
    // Finally, we create a reference from the end of the smaller values list to the start of the larger values list.
    public static void partitionLinkedList(int partitioningValue, SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList<Integer> largerValues = new SinglyLinkedList<>();
        Iterator<Integer> listIterator = linkedList.iterator();
        while(listIterator.hasNext()) {
            int currentValue = listIterator.next();
            if(currentValue >= partitioningValue) {
                listIterator.remove();
                largerValues.addLast(currentValue);
            }
        }
        if(largerValues.isEmpty()) {
            return;
        } else if(linkedList.isEmpty()) {
            linkedList.first = largerValues.first;
            linkedList.last = largerValues.last;
        } else {
            linkedList.last.next = largerValues.first;
            linkedList.last = largerValues.last;
        }
    }

    private static SinglyLinkedList<Integer> generateRandomIntLinkedList(int listLength) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        for(int elementCounter = 0; elementCounter < listLength; elementCounter++) {
            linkedList.addLast((int) (Math.random() * listLength));
        }
        return linkedList;
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        int listLength = Integer.parseInt(readLineFromConsole("Enter the LinkedList length: "));
        SinglyLinkedList<Integer> linkedList = generateRandomIntLinkedList(listLength);
        System.out.println(linkedList.toString());
        int partitioningValue = Integer.parseInt(readLineFromConsole("Enter the partitioning value: "));
        partitionLinkedList(partitioningValue, linkedList);
        System.out.println(linkedList.toString());
    }
}

class SinglyLinkedList<T> implements Iterable<T> {
    public Node first;
    public Node last;

    public SinglyLinkedList() {
        first = null;
        last = null;
    }

    public class Node {
        public T data;
        public Node next;

        Node(T data) {
            this.data = data;
        }
    }

    public void addLast(T data) {
        if(last == null) {
            last = new Node(data);
            first = last;
        } else {
            Node node = new Node(data);
            last.next = node;
            last = node;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        StringBuilder linkedListBuilder = new StringBuilder();
        Node current = first;
        while(current != null && current != last) {
            linkedListBuilder.append(current.data);
            linkedListBuilder.append(" -> ");
            current = current.next;
        }
        if(last != null) {
            linkedListBuilder.append(last.data);
        }
        return linkedListBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<T> {
        Node previousNode;
        Node currentNode;
        Node nextNode;

        SinglyLinkedListIterator() {
            previousNode = first;
            currentNode = first;
            nextNode = first;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = nextNode.next;
            return currentNode.data;
        }

        @Override
        public void remove() {
            if(currentNode == null) {
                throw new NoSuchElementException();
            }
            if(currentNode == first) {
                if(currentNode == last) {
                    last = null;
                }
                first = currentNode.next;
                previousNode = first;
                currentNode = first;
                nextNode = first;
            } else if(currentNode == last) {
                last = previousNode;
                last.next = null;
                currentNode = null;
                nextNode = null;
            } else {
                previousNode.next = nextNode;
                currentNode = previousNode;
            }
        }
    }
}
