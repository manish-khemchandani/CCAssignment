package solution08;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Given a circular linked list, implement an algorithm which returns the node at
// the beginning of the loop.
// DEFINITION
// Circular linked list: A (corrupt) linked list in which a node's next pointer points
// to an earlier node, so as to make a loop in the linked list.
// EXAMPLE
// Input: A - > B - > C - > D - > E - > C [the same C as earlier]
// Output: C
public class Solution08 {
    public static int loopStartNodePosition = -1;

    // We advance a fast pointer that travels 2 nodes at a time, and a slow one
    // which travel one node. If the fast pointer reaches the end of the list,
    // we know there is no loop. Otherwise, they will collide at some point.
    // The distance between the collision node and the start of the loop will be
    // the same as distance between the start of the list and the start of the loop.
    // So, we now advance 2 pointers, one from the collision point and one from the
    // start of the linked list. The node where they meet is the start of the loop.
    public static SinglyLinkedList.Node detectLoop(SinglyLinkedList linkedList) {
        SinglyLinkedList.Node slow = linkedList.first;
        SinglyLinkedList.Node fast = linkedList.first;
        SinglyLinkedList.Node loopStartNode;
        boolean haveCollided = false;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                haveCollided = true;
                break;
            }
        }

        if(haveCollided) {
            int nodeCounter = 0;

            slow = linkedList.first;
            while(slow != fast) {
                slow = slow.next;
                fast = fast.next;
                nodeCounter++;
            }
            loopStartNode = slow;
            loopStartNodePosition = nodeCounter;
        } else {
            loopStartNode = null;
        }
        return loopStartNode;
    }

    public static SinglyLinkedList<Integer> generateLinkedListWithLoop(int listLength) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        int loopStartNodePosition = (int) (Math.random() * listLength - 1);
        SinglyLinkedList.Node loopStartNode = null;
        for(int elementCounter = 0; elementCounter < listLength; elementCounter++) {
            linkedList.addLast((int) (Math.random() * listLength));
            if(elementCounter == loopStartNodePosition) {
                loopStartNode = linkedList.last;
            }
        }
        linkedList.last.next = loopStartNode;
        return linkedList;
    }

    public static SinglyLinkedList<Integer> generateRandomIntLinkedList(int listLength) {
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
        int listLength = Integer.parseInt(readLineFromConsole("Enter the length of the linked list: "));
        SinglyLinkedList<Integer> loopedLinkedList = generateLinkedListWithLoop(listLength);
        System.out.print(loopedLinkedList.toString());
        System.out.println(" -> " + loopedLinkedList.last.next.data);
        SinglyLinkedList.Node startOfLoop = detectLoop(loopedLinkedList);
        if(startOfLoop != null) {
            System.out.println("The loop is at node " + startOfLoop.data + " with position at " + loopStartNodePosition + ".");
        } else {
            System.out.println("There is no loop in the linked list.");
        }
        System.out.println("");
        SinglyLinkedList<Integer> linkedList = generateRandomIntLinkedList(listLength);
        System.out.println(linkedList.toString());
        startOfLoop = detectLoop(linkedList);
        if(startOfLoop != null) {
            System.out.println("The loop is at node " + startOfLoop.data + " with position at " + loopStartNodePosition + ".");
        } else {
            System.out.println("There is no loop in the linked list.");
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

