package pl.sda.rafal.zientara.programowanie1.webinar.recurr;

import java.util.*;

public class ObjectInsertSortMain {

    public static void main(String[] args) {
        Person[] input = new Person[]{
                new Person(12, "Pawel"),
                new Person(31, "Zbigniew"),
                new Person(12, "Pawel"),
                new Person(21, "Zdzichu"),
                new Person(12, "Rysiek"),
                new Person(18, "Ola")
        };

        Map<Integer, List<Person>> map = new TreeMap<>();

        for (int i = 0; i < input.length; i++) {
            Person person = input[i];
            if (map.containsKey(person.age)) {
                List<Person> people = map.get(person.age);
                people.add(person);
            } else {
                List<Person> list = new LinkedList<>();
                list.add(person);
                map.put(person.age, list);
            }
        }

        int index = 0;
        Set<Map.Entry<Integer, List<Person>>> entries = map.entrySet();
        for (Map.Entry<Integer, List<Person>> entry : entries) {
            System.out.printf("%d %s\n", entry.getKey(), entry.getValue());

            List<Person> value = entry.getValue();
            for (Person p : value) {
                input[index] = p;
                index++;
            }
        }

        printArray(input);
    }

    private static void printArray(Person[] input) {
        System.out.println("======================");
        System.out.println("======================");
        System.out.println("======================");
        System.out.print("[");
        for (Person value : input) {
            System.out.print(value + " \n");
        }
        System.out.println("]");
    }
}
