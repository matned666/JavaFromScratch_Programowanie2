package pl.sda.rafal.zientara.cashMachine;

public class Check {

    public static boolean isNumeric(String value) {
        try {
            long isNumeric = Long.parseLong(value.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
