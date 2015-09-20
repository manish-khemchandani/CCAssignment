package solution05b;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// FOLLOW UP
// Suppose the digits are stored in forward order. Repeat the above problem.
// EXAMPLE
// Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
// Output: 9 -> 1 -> 2.That is, 912.
public class Solution05b {
    public static SinglyLinkedList<Integer> addListIntegers(SinglyLinkedList<Integer> number1List, SinglyLinkedList<Integer> number2List) {
        int sum = 0;
        SinglyLinkedList.Node number1Digit = number1List.first;
        SinglyLinkedList.Node number2Digit = number2List.first;

        int number1Length = 0;
        SinglyLinkedList.Node current = number1List.first;
        while(current != null) {
            number1Length++;
            current = current.next;
        }

        int number2Length = 0;
        current = number2List.first;
        while(current != null) {
            number2Length++;
            current = current.next;
        }

        int digitPosition = Math.max(number1Length, number2Length);

        while(digitPosition > 0) {
            digitPosition--;
            if(digitPosition < number1Length) {
                int digit1 = (int) number1Digit.data;
                int subnumber1 = (int) (digit1 * Math.pow(10, digitPosition));
                sum += subnumber1;
                number1Digit = number1Digit.next;
            }
            if(digitPosition < number2Length) {
                int digit2 = (int) number2Digit.data;
                int subnumber2 = (int) (digit2 * Math.pow(10, digitPosition));
                sum += subnumber2;
                number2Digit = number2Digit.next;
            }
        }

        return convertIntegerToList(sum);
    }

    private static SinglyLinkedList<Integer> convertIntegerToList(int number) {
        SinglyLinkedList<Integer> numberList = new SinglyLinkedList<>();

        while(number > 0) {
            numberList.addFirst(number % 10);
            number = number / 10;
        }

        return numberList;
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        int number1 = Integer.parseInt(readLineFromConsole("Enter number 1: "));
        int number2 = Integer.parseInt(readLineFromConsole("Enter number 2: "));
        SinglyLinkedList<Integer> number1List = convertIntegerToList(number1);
        System.out.println(number1List.toString());
        SinglyLinkedList<Integer> number2List = convertIntegerToList(number2);
        System.out.println(number2List.toString());
        SinglyLinkedList<Integer> sum = addListIntegers(number1List, number2List);
        System.out.println("The sum of the 2 numbers is: " + sum);
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

