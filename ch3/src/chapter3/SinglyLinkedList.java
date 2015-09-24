package chapter3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {
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

    public void addFirst(T data) {
        if(first == null) {
            first = new Node(data);
            last = first;
        } else {
            Node node = new Node(data);
            node.next = first;
            first = node;
        }
    }

    public void addFirst(Node node) {
        if(first == null) {
            first = node;
            last = first;
        } else {
            node.next = first;
            first = node;
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

    public void addLast(Node node) {
        if(last == null) {
            last = node;
            first = last;
        } else {
            last.next = node;
            last = node;
        }
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
        }
        return isNodeFound;
    }

    public T removeFirst() {
        if(first != null) {
            T data = first.data;
            first = first.next;
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
