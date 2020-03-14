package pl.sda.rafal.zientara.programowanie1.webinar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 1);
        Collections.sort(list);
//        Collections.sort(list, new Comparator<Integer>() {
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(-o1, o2);//sortowanie odwrotne
            }
        });
//        Collections.shuffle();//everyday I'm shufflin

        System.out.println(list);

        Integer[] array = new Integer[]{2, 3, 1};
        Arrays.sort(array, (Comparator<Integer>) (o1, o2) -> Integer.compare(o1, o2));

        for (Integer e : array) {
            System.out.println(e);
        }
    }
}
