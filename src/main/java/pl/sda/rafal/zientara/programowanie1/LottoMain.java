package pl.sda.rafal.zientara.programowanie1;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LottoMain {
    public static void main(String[] args) {
        Set<Integer> balls = new TreeSet<>();
        Set<Integer> balls2 = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Integer value = i*2+1;
            balls.add(value);
        }

        System.out.println(balls);
    }
}
