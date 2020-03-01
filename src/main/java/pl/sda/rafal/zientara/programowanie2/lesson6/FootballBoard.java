package pl.sda.rafal.zientara.programowanie2.lesson6;

import java.util.ArrayList;
import java.util.List;

public class FootballBoard {
    public final int width;
    public final int height;
    private List<Line> lines;

    public FootballBoard(int width, int height) {
        this.width = width;
        this.height = height;
        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        if (!lineExists(line)) {
            lines.add(line);
        } else {
            throw new IllegalStateException("Line exists!");
        }
    }

    public boolean lineExists(Line line) {
        return lineExists(line.start, line.end);
    }

    public boolean lineExists(Point start, Point end) {
        for (Line line : lines) {
            if (line.start.equals(start) &&
                    line.end.equals(end)) {
                return true;
            }
            if (line.start.equals(end) &&
                    line.end.equals(start)) {
                return true;
            }
        }
        return false;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void initSides() {
        initLeftSide();
        initRightSide();
        initTopSide();
        initBottomSide();
        initTopGoal();
        initBottomGoal();
    }

    private void initTopSide() {
        int widthCenter = width / 2;
        for (int i = 0; i < width; i++) {
            int x1 = i;
            int x2 = i + 1;
            int y = 1;
            if (x1 < widthCenter - 1 || x1 > widthCenter) {
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end,
                        LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initBottomSide() {
        int widthCenter = width / 2;
        for (int i = 0; i < width; i++) {
            int x1 = i;
            int x2 = i + 1;
            int y = height - 1;
            if (x1 < widthCenter - 1 || x1 > widthCenter) {
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end,
                        LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initTopGoal() {
        int widthCenter = width / 2;
        addLine(new Line(//top left
                widthCenter - 1, 0,
                widthCenter, 0,
                LineType.SIDE));
        addLine(new Line( //top right
                widthCenter, 0,
                widthCenter + 1, 0,
                LineType.SIDE));
        addLine(new Line(//left
                widthCenter - 1, 0,
                widthCenter - 1, 1,
                LineType.SIDE));
        addLine(new Line(//right
                widthCenter + 1, 0,
                widthCenter + 1, 1,
                LineType.SIDE));
    }

    private void initBottomGoal() {
        int widthCenter = width / 2;
        addLine(new Line(//bottom left
                widthCenter - 1, height,
                widthCenter, height,
                LineType.SIDE));
        addLine(new Line( //bottom right
                widthCenter, height,
                widthCenter + 1, height,
                LineType.SIDE));
        addLine(new Line(//left
                widthCenter - 1, height,
                widthCenter - 1, height - 1,
                LineType.SIDE));
        addLine(new Line(//right
                widthCenter + 1, height,
                widthCenter + 1, height - 1,
                LineType.SIDE));
    }

    private void initLeftSide() {
        for (int i = 1; i < height - 1; i++) {
            int x = 0;
            int y1 = i;
            int y2 = i + 1;
            Point start = new Point(x, y1);
            Point end = new Point(x, y2);
            Line line = new Line(start, end,
                    LineType.SIDE);
            addLine(line);
        }
    }

    private void initRightSide() {
        for (int i = 1; i < height - 1; i++) {
            int x = width;
            int y1 = i;
            int y2 = i + 1;
            Point start = new Point(x, y1);
            Point end = new Point(x, y2);
            Line line = new Line(start, end,
                    LineType.SIDE);
            addLine(line);
        }
    }

    public boolean hasAnyConnection(Point position) {
        for (Line line : lines) {
            if (line.start.equals(position) ||
                    line.end.equals(position)) {
                return true;
            }
        }
        return false;
    }
}
