package pl.sda.rafal.zientara.programowanie2.lesson5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MainComputerHearthBeating {
    public static void main(String[] argumenty) {
        ActionListener listener = new Spammer();

        //stwórz stoper wywo³uj¹cy s³uchacza
        //co ka¿de 10 sekund
        Timer t = new Timer(2000, listener);
        t.start();

        JOptionPane.showMessageDialog(null, "Zakoñczyæ program?");
        System.exit(0);
    }
}

class Spammer implements ActionListener {
    public void actionPerformed(ActionEvent zdarzenie) {
        Date now = new Date();
        System.out.println("Gdy s³yszysz ten dŸwiêk, jest dok³adnie " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}