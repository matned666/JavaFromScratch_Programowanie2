package pl.sda.rafal.zientara.programowanie2.lesson6.homework;

public class Check {

    public static boolean isNumeric(String value) {
        try {
            int isNumeric = Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
