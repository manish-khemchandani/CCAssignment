package solution03;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Given only a node in the middle of a linked list, write an algorithm to delete that node.
public class Solution03 {
    // Since we only have access to the node, we cannot point the next reference of
    // the previous node to the next one. So, we copy data from the next node to the current one
    // and have the current node's next reference point to the next to next node
    // (effectively deleting the current node).
    public static void deleteNode(SinglyLinkedList.Node node) {
        if(node != null && node.next != null) {
            SinglyLinkedList.Node nextNode = node.next;
            node.data = nextNode.data;
            node.next = nextNode.next;
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

        int nodePosition;
        if(listLength % 2 == 0) {
            nodePosition = listLength / 2;
        } else {
            nodePosition = listLength / 2 + 1;
        }
        SinglyLinkedList.Node current = linkedList.first;
        while(nodePosition != 0) {
            current = current.next;
            nodePosition--;
        }

        deleteNode(current);
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
