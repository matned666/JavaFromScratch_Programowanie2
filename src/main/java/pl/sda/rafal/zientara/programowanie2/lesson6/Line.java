package pl.sda.rafal.zientara.programowanie2.lesson6;


public class Line {
    final Point start;
    final Point end;
    final LineType type;

    public Line(Point start,
                Point end,
                LineType type) {
        int xDiff = Math.abs(start.x - end.x);
        int yDiff = Math.abs(start.y - end.y);
        if (xDiff > 1 || yDiff > 1) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public Line(int x1, int y1,
                int x2, int y2,
                LineType type) {
        int xDiff = Math.abs(x1 - x2);
        int yDiff = Math.abs(y1 - y2);
        if (xDiff > 1 || yDiff > 1) {
            throw new IllegalArgumentException();
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.type = type;
    }

}
