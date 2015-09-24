package chapter3;

import java.util.Calendar;

public class Solution06 {
    private SinglyLinkedList<AnimalNode> cats;
    private SinglyLinkedList<AnimalNode> dogs;

    public Solution06() {
        cats = new SinglyLinkedList<>();
        dogs = new SinglyLinkedList<>();
    }

    public void enqueue(String animalName, AnimalType animalType) {
        Animal animal = new Animal(animalName, animalType);
        if(animalType == AnimalType.CAT) {
            cats.addLast(new AnimalNode(animal));
        }
        if(animalType == AnimalType.DOG) {
            dogs.addLast(new AnimalNode(animal));
        }
    }

    public String dequeueAny() {
        if(cats.isEmpty() || dogs.isEmpty()) {
            if(cats.isEmpty() && dogs.isEmpty()) {
                return null;
            } else if(cats.isEmpty()) {
                return dogs.removeFirst().animal.animalName;
            } else {
                return cats.removeFirst().animal.animalName;
            }
        }
        if(cats.first.data.timeStamp <= dogs.first.data.timeStamp) {
            return cats.removeFirst().animal.animalName;
        } else {
            return dogs.removeFirst().animal.animalName;
        }
    }

    public String dequeueCat() {
        return cats.removeFirst().animal.animalName;
    }

    public String dequeueDog() {
        return dogs.removeFirst().animal.animalName;
    }

    class AnimalNode {
        Animal animal;
        long timeStamp;

        AnimalNode(Animal animal) {
            this.animal = animal;
            timeStamp = Calendar.getInstance().getTimeInMillis();
        }
    }

    public static void main(String[] args) {
        Solution06 animalQueue = new Solution06();
        animalQueue.enqueue("Woofer", AnimalType.DOG);
        animalQueue.enqueue("Whiskers", AnimalType.CAT);
        animalQueue.enqueue("Puss", AnimalType.CAT);
        animalQueue.enqueue("Scamper", AnimalType.DOG);
        animalQueue.enqueue("Snowy", AnimalType.DOG);
        System.out.println(animalQueue.dequeueCat());
        System.out.println(animalQueue.dequeueDog());
        System.out.println(animalQueue.dequeueAny());
    }
}

class Animal {
    String animalName;
    AnimalType animalType;

    Animal(String animalName, AnimalType animalType) {
        this.animalName= animalName;
        this.animalType = animalType;
    }
}

enum AnimalType {
    DOG, CAT
}
