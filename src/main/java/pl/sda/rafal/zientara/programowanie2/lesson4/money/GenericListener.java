package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public abstract class GenericListener<Value> implements KeyListener {

    private final JTextField field;
    private Value previousValue;

    public GenericListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    abstract protected Value parseValue(String input);

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        showNormal();
        Value value = parseValue(input);
//        if (!Objects.equals(value, previousValue)) {
        System.out.println("Nowa wartosc!:" + value);
        onValueUpdate(value);
//        }
    }

    public abstract void onValueUpdate(Value newValue);

    @Override
    public int hashCode() {
        return Objects.hash(field, previousValue);
    }

    protected void showNormal() {
        field.setForeground(Color.BLACK);
    }

    protected void showError() {
        field.setForeground(Color.RED);
    }
}
