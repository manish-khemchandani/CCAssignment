package solution01b;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Given a Linked List, remove all duplicate nodes without using additional memory.
public class Solution01b {
    public static void removeDuplicates(SinglyLinkedList<Integer> linkedList) {
        SinglyLinkedList.Node current = linkedList.first;
        while(current != null) {
            SinglyLinkedList.Node runner = current.next;
            SinglyLinkedList.Node follower = current;
            while(runner != null) {
                if(runner.data == current.data) {
                    follower.next = runner.next;
                    runner = follower;
                }
                follower = runner;
                runner = runner.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
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

class SinglyLinkedList<T> implements Iterable<T> {
    public Node first;
    public Node last;
    private int length;

    public SinglyLinkedList() {
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

    public void addFirst(T data) {
        if(first == null) {
            first = new Node(data);
            last = first;
        } else {
            Node node = new Node(data);
            node.next = first;
            first = node;
        }
        length++;
    }

    public void addFirst(Node node) {
        if(first == null) {
            first = node;
            last = first;
        } else {
            node.next = first;
            first = node;
        }
        length++;
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

    public void addLast(Node node) {
        if(last == null) {
            last = node;
            first = last;
        } else {
            last.next = node;
            last = node;
        }
        length++;
    }

    public boolean remove(T data) {
        boolean isNodeFound = false;
        Node current = first;
        Node previous = current;
        while(current != null) {
            if(current.data == data) {
                break;
            }
            previous = current;
            current = current.next;
        }
        if(current != null) {
            isNodeFound = true;
            if(current == first) {
                current.next = first;
            } else if(current == last) {
                last = previous;
            } else {
                previous.next = current.next;
            }
            length--;
        }
        return isNodeFound;
    }

    public T removeFirst() {
        if(first != null) {
            T data = first.data;
            first = first.next;
            length--;
            return data;
        } else {
            return null;
        }
    }

    public boolean search(T data) {
        Node current = first;
        while(current != null) {
            if(current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        first = last = null;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
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
