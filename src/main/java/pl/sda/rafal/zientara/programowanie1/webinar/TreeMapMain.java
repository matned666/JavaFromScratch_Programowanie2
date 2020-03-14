package pl.sda.rafal.zientara.programowanie1.webinar;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapMain {

    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(5,"5");
        map.put(4,"4");

        Collection<String> values = map.values();
        for (String s :
                values) {
            System.out.println(s);
        }

    }

}
