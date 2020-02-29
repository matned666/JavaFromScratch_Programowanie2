package pl.sda.rafal.zientara.programowanie2.lesson5;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

public class MainZonesTime {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        for (String zone : availableZoneIds) {
            ZoneId zoneId = ZoneId.of(zone);
            System.out.println(localDateTime.atZone(zoneId));
        }
    }
}
