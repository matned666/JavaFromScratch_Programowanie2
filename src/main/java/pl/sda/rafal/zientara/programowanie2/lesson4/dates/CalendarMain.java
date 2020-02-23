package pl.sda.rafal.zientara.programowanie2.lesson4.dates;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarMain {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        System.out.println(firstDay);
        LocalDate currentDate = firstDay;
        System.out.println(firstDay.getMonth());

        DayOfWeek first = firstDay.getDayOfWeek();
        for (int i = 0; i<first.getValue()-1; i++) {
            System.out.print("   ");
        }
        while (firstDay.getMonth() == currentDate.getMonth()) {
            System.out.printf("%2d ",currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentDate = currentDate.plusDays(1);
        }
    }
}
