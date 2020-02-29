package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DateListener implements KeyListener {
    private static final List<DateTimeFormatter> FORMATS =
            Arrays.asList(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"),
                    DateTimeFormatter.ofPattern("dd MM yyyy"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                    DateTimeFormatter.ofPattern("yyyy MM dd"),
                    DateTimeFormatter.ofPattern("yyyy.MM.dd")
            );

    private final JTextField field;

    public DateListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private LocalDate parseDate(String input) {
        System.out.println("Try formatting : " + input);
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception ignored) {
//                System.out.println(ignored.getMessage());
            }
        }
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        LocalDate date = parseDate(input);
        System.out.println(date);
        onDateUpdate(date);
    }

    public abstract void  onDateUpdate(LocalDate newDate);
}
