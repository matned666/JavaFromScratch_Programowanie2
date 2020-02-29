package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class DoubleListener extends GenericListener<Double> {

    public DoubleListener(JTextField field) {
        super(field);
    }

    @Override
    public Double parseValue(String input) {
        System.out.println("Try parsing : " + input);
        try {
            return Double.parseDouble(input);
        } catch (Exception ignored) {
//                System.out.println(ignored.getMessage());
        }
        showError();
        return null;
    }

}
