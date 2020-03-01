package pl.sda.rafal.zientara.programowanie2.lesson6;

import javax.swing.*;

public class MainFootball {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Football");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        FootballView footballView = new FootballView();
        footballView.setBounds(50,5,200,300);
        frame.add(footballView);
        frame.setVisible(true);
    }
}
