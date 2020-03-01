package pl.sda.rafal.zientara.programowanie2.lesson6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.Scanner;

public class FootballViewTest {

    private FootballContract.View view;
    private FootballContract.Presenter presenter;
    private FootballView footballView = new FootballView();
    private FootballBoard board =
            new FootballBoard(10,12);

    @BeforeEach
    public void setup() {
//        view = Mockito.mock(FootballContract.View.class);
        view = footballView;
        presenter = new FootballPresenter(view, board);
        footballView.setBoard(board);
        footballView.setPresenter(presenter);
        board.initSides();
        presenter.init();
    }

    @Test
    public void yolo() {
        presenter.moveTop();//player t
        presenter.moveRight();//player b
        presenter.moveBottomLeft();//player t
        presenter.moveBottom();//player t
        System.out.println("run");

    }

    @AfterEach
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
