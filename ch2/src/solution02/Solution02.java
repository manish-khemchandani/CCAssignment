package solution02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Implement and algorithm to find the kth to last element of a singly linked list.
public class Solution02 {
    // In this method, we create a reference to the head of the linked list and increase it k times.
    // Once that is done, we advance another pointer in synchronization with the first one.
    // When the first pointer reaches the end of the linked list, the second pointer
    // points to the kth last node.
    public static SinglyLinkedList.Node findKthLastElement(int k, SinglyLinkedList linkedList) {
        // Checks to see if the input values for k and the linked list are correct.
        if(k <= 0 || linkedList == null) {
            return null;
        }
        SinglyLinkedList.Node current = linkedList.first;
        while(k != 0) {
            // If we reach the end of the list before we have advanced "current" k times,
            // return null.
            if(current == null) {
                return null;
            }
            current = current.next;
            k--;
        }
        SinglyLinkedList.Node runner = linkedList.first;
        while(current != null) {
            current = current.next;
            runner = runner.next;
        }
        return runner;
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
        int k = Integer.parseInt(readLineFromConsole("Enter k where k represents the kth last element: "));
        SinglyLinkedList.Node kthLastElement = findKthLastElement(k, linkedList);
        if(kthLastElement != null) {
            System.out.println("The kth last element: " + kthLastElement.data);
        } else {
            System.out.println("The kth last element: " + null);
        }
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
