package pl.sda.rafal.zientara.programowanie2.lesson6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootballBoardTest {

    private FootballBoard board;

    @BeforeEach
    public void setup() {
        board = new FootballBoard(6, 8);
    }

    @Test
    public void insertedLineCanBeChecked() {
        Line line = new Line(new Point(1, 1),
                new Point(2, 2),
                LineType.SIDE);

        board.addLine(line);

        boolean exists = board.lineExists(line.start, line.end);
        assertTrue(exists);
    }

    @Test
    public void insertedLineCanBeCheckedNewInstance() {
        Line line = new Line(new Point(1, 1),
                new Point(2, 2),
                LineType.SIDE);

        board.addLine(line);

        boolean exists = board.lineExists(new Point(1, 1),
                new Point(2, 2));
        assertTrue(exists);
    }


    @Test
    public void insertedLineCanBeCheckedNewInstanceReverted() {
        Line line = new Line(new Point(1, 1),
                new Point(2, 2),
                LineType.SIDE);

        board.addLine(line);

        boolean exists = board.lineExists(new Point(2, 2),
                new Point(1, 1));
        assertTrue(exists);
    }

    @Test
    public void cantAddTheSameLine() {
        Line line = new Line(new Point(1, 1),
                new Point(2, 2),
                LineType.SIDE);

        board.addLine(line);

        assertThrows(IllegalStateException.class,
                () -> board.addLine(line));
    }

    @Test
    public void lineDoesNotExist() {
        //given
        Line line = new Line(new Point(1, 1),
                new Point(2, 2),
                LineType.SIDE);

        //when
        boolean exists = board.lineExists(line);

        //then
        assertFalse(exists);
    }

    @Test
    public void prepareSides() {
        board.initSides();
        List<Line> lines = board.getLines();
        assertFalse(lines.isEmpty());
    }

}