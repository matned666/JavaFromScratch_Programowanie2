package pl.sda.rafal.zientara.programowanie1.webinar;

import java.util.*;

public class LottoMain {

    public static void main(String[] args) {
        List<Integer> balls = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Integer value = i + 1;
            balls.add(value);
        }


        for (int i = 0; i < 5; i++) {

            int randomIndex = new Random().nextInt(balls.size());
            TimeMeasure timeMeasure = new TimeMeasure();

            timeMeasure.startMeasure();
            Integer ball = balls.get(randomIndex);
            timeMeasure.endMeasure();

            balls.remove(ball);

            System.out.println("Wylosowana: " + ball);
            System.out.println("Zostalo:" + balls);
        }


    }


}
