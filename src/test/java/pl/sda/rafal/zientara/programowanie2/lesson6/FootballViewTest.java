package pl.sda.rafal.zientara.programowanie2.lesson6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Scanner;

public class FootballViewTest {

    private FootballView footballView = new FootballView();
    private FootballBoard board = new FootballBoard(10,12);

    @BeforeEach
    public void setup() {
        footballView.setBoard(board);
        board.initSides();
    }

    @Test
    public void test() {
        showMeBoard();
        waitForUser();
    }

    private void waitForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER");
        scanner.nextLine();
    }

    private void showMeBoard() {
        JFrame frame = new JFrame("Football");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,400);

        frame.add(footballView);
        frame.setVisible(true);
    }
}
