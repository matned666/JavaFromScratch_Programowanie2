package pl.sda.rafal.zientara.programowanie2.lesson4.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class DatesMain {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();//dzien
        LocalDateTime dateTime = LocalDateTime.now();//dzien godzina
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//dzien godzina strefe

        System.out.println(localDate);
        System.out.println(dateTime);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("Canada/Atlantic")));

        Set<String> allZones = ZoneId.getAvailableZoneIds();
        for (String zone : allZones) {
            System.out.println(zone);
        }
    }
}
