package person;

import locations.Phone;

public class TestPerson {
    public static void main(String[] args) {
        Phone phone1 = new Phone("0756548535");
        System.out.println(phone1.toString());
        Adult adult1 = new Adult("Ana", "Popescu", phone1);
        System.out.println(adult1.toString());
        Child child1 = new Child("Andrei", "Popescu");
        Child child2 = new Child("Andreea", "Popescu");
        Child child3 = new Child("Andreea", "Popescu");
        System.out.println(child1.equals(child2));
        System.out.println(child3.equals(child2));
    }
}
