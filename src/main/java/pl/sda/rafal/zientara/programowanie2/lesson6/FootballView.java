package pl.sda.rafal.zientara.programowanie2.lesson6;

import javax.swing.*;
import java.awt.*;

public class FootballView extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        g.drawLine(0, 0, getWidth(), getHeight());
    }
}
