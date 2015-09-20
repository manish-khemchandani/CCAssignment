package solution07;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Given 2 linked lists, determine whether or not they intersect, and if they do, find the node where they do.
public class Solution07 {
    // In this approach, we go to the tails of both linked lists to find if it is the same. If so, the 2 linked lists
    // intersect. We also find the lengths of the 2 linked lists. Then we again start from the beginning of the 2 linked
    // lists and increment the current reference. If the current node for both linked lists is the same then that is the
    // intersection point.
    public static SinglyLinkedList.Node getIntersectingNode(SinglyLinkedList list1, SinglyLinkedList list2) {
        if(list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) {
            return null;
        }
        SinglyLinkedList.Node current1 = list1.first;
        SinglyLinkedList.Node current2 = list2.first;
        int list1Length = 0;
        int list2Length = 0;
        while(current1.next != null) {
            current1 = current1.next;
            list1Length++;
        }
        while(current2.next != null) {
            current2 = current2.next;
            list2Length++;
        }
        if(current1 != current2) {
            return null;
        } else {
            current1 = list1.first;
            current2 = list2.first;
            if(list1Length > list2Length) {
                while(list1Length != list2Length) {
                    current1 = current1.next;
                    list1Length--;
                }
            } else if(list1Length < list2Length) {
                while(list1Length != list2Length) {
                    current2 = current2.next;
                    list2Length--;
                }
            }
            while(true) {
                if(current1 == current2) {
                    return current1;
                }
                current1 = current1.next;
                current2 = current2.next;
            }
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list1.addLast(10);
        list1.addLast(3);
        list1.addLast(5);
        list1.addLast(8);
        list1.addLast(15);
        list2.addLast(7);
        list2.addLast(13);
        list2.addLast(list1.last);
        list1.addLast(21);
        list1.addLast(33);
        System.out.println(list1);
        System.out.println(list2);
        SinglyLinkedList.Node node = getIntersectingNode(list1, list2);
        if(node == null) {
            System.out.println("The Linked Lists do not intersect.");
        } else {
            System.out.println("The Linked Lists intersect at: " + node.data);
        }
        System.out.println("");

        list1.clear();
        list2.clear();
        list1.addLast(10);
        list1.addLast(3);
        list1.addLast(5);
        list1.addLast(8);
        list1.addLast(15);
        list2.addLast(7);
        list2.addLast(13);
        list2.addLast(15);
        System.out.println(list1);
        System.out.println(list2);
        node = getIntersectingNode(list1, list2);
        if(node == null) {
            System.out.println("The Linked Lists do not intersect.");
        } else {
            System.out.println("The Linked Lists intersect at: " + node.data);
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

    public void addLast(Node node) {
        if(last == null) {
            last = node;
            first = last;
        } else {
            last.next = node;
            last = node;
        }
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
