package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class DoubleListener implements KeyListener {

    private final JTextField field;

    public DoubleListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private Double parseDouble(String input) {
        System.out.println("Try parsing : " + input);
        try {
            return Double.parseDouble(input);
        } catch (Exception ignored) {
//                System.out.println(ignored.getMessage());
        }
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        Double value = parseDouble(input);
        System.out.println(value);
        onValueUpdate(value);
    }

    public abstract void onValueUpdate(Double newValue);
}
