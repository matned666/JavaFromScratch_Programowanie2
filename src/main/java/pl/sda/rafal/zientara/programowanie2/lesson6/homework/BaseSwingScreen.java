package pl.sda.rafal.zientara.programowanie2.lesson6.homework;

import javax.swing.*;

public class BaseSwingScreen {
    protected JFrame frame;

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
