package solution01a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Given a Linked List, remove all duplicate nodes.
public class Solution01a {
    // We iterate through the Linked List adding its elements to an ArrayList. If the value of a node in the Linked List
    // is present in the ArrayList, we remove that node from the LL.
    public static void removeDuplicates(SinglyLinkedList01<Integer> linkedList) {
        List<Integer> nodeTracker = new ArrayList<>();
        Iterator<Integer> listIterator = linkedList.iterator();
        while(listIterator.hasNext()) {
            Integer data = listIterator.next();
            if(nodeTracker.contains(data)) {
                listIterator.remove();
            } else {
                nodeTracker.add(data);
            }
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList01<Integer> linkedList = new SinglyLinkedList01<>();
        linkedList.addLast(12);
        linkedList.addLast(13);
        linkedList.addLast(7);
        linkedList.addLast(2);
        linkedList.addLast(18);
        linkedList.addLast(14);
        linkedList.addLast(11);
        linkedList.addLast(8);
        linkedList.addLast(18);
        linkedList.addLast(22);
        linkedList.addLast(17);
        linkedList.addLast(10);
        linkedList.addLast(23);
        linkedList.addLast(14);
        linkedList.addLast(22);
        linkedList.addLast(21);
        linkedList.addLast(1);
        linkedList.addLast(13);
        linkedList.addLast(11);
        linkedList.addLast(17);
        linkedList.addLast(19);
        linkedList.addLast(18);
        linkedList.addLast(1);
        linkedList.addLast(18);
        linkedList.addLast(5);
        System.out.println(linkedList.toString());
        removeDuplicates(linkedList);
        System.out.println(linkedList);
    }
}

// Singly Linked List implementation.
class SinglyLinkedList01<T> implements Iterable<T> {
    public Node first;
    public Node last;
    private int length;

    public SinglyLinkedList01() {
        first = null;
        last = null;
        length = 0;
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
        length++;
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
            length--;
        }
    }
}
