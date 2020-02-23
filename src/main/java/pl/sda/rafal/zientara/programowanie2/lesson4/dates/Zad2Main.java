package pl.sda.rafal.zientara.programowanie2.lesson4.dates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Zad2Main {

    public static void main(String[] args) {
        String input = "07-06-2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate output = LocalDate.parse(input, formatter);
        System.out.println(output);
        System.out.println(LocalDate.now().format(formatter));
        System.out.println(LocalDate.now().plusDays(1).format(formatter));

        //todo 5 najblizszych piatkow z LocalDate
        LocalDate currentDate = LocalDate.now();
        System.out.println("Piateczki nadchodze. Juz niedlugo:");
        int fridaysCount = 0;
        while (fridaysCount < 5) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY) {
                currentDate = currentDate.plusDays(1);
            } else {
                System.out.println(currentDate);
                currentDate = currentDate.plusWeeks(1);
                fridaysCount++;
            }
        }
    }
}
