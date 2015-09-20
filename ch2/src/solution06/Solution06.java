package solution06;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

// Implement a function to check if a linked list is a palindrome.
public class Solution06 {
    public static boolean isPalindrome(SinglyLinkedList charList) {
        Stack<SinglyLinkedList.Node> nodes = new Stack<>();
        SinglyLinkedList.Node fast = charList.first;
        SinglyLinkedList.Node slow = charList.first;

        // We advance a fast pointer that travels 2 nodes at a time, and a slow one
        // which travel one node. When the fast pointer reaches the end of the list,
        // the slow pointer points to the middle element.
        while(fast != null && fast.next != null) {
            nodes.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        // The List has an odd number of elements.
        // Therefore, we skip over the middle element.
        if(fast != null) {
            slow = slow.next;
        }

        while(slow != null) {
            if(!slow.data.equals(nodes.pop().data)) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public static SinglyLinkedList<Character> convertStringToList(String string) {
        SinglyLinkedList<Character> charList = new SinglyLinkedList<>();

        char[] stringChars = string.toCharArray();
        for(char stringChar : stringChars) {
            charList.addLast(stringChar);
        }

        return charList;
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String string = readLineFromConsole("Enter a String: ");
        SinglyLinkedList<Character> charList = convertStringToList(string);
        System.out.println("The String is a palindrome: " + isPalindrome(charList));
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
