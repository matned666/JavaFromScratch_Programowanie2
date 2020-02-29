package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DateListener extends GenericListener<LocalDate> {
    private static final List<DateTimeFormatter> FORMATS =
            Arrays.asList(
                    DateTimeFormatter.ofPattern("d-M-yyyy"),
                    DateTimeFormatter.ofPattern("dd-M-yyyy"),
                    DateTimeFormatter.ofPattern("d-MM-yyyy"),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"),
                    DateTimeFormatter.ofPattern("dd MM yyyy"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                    DateTimeFormatter.ofPattern("yyyy MM dd"),
                    DateTimeFormatter.ofPattern("yyyy.MM.dd")
            );

    public DateListener(JTextField field) {
        super(field);
    }

    @Override
    protected LocalDate parseValue(String input) {
        System.out.println("Try formatting : " + input);
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception ignored) {
//                System.out.println(ignored.getMessage());
            }
        }
        showError();
        return null;
    }

}
