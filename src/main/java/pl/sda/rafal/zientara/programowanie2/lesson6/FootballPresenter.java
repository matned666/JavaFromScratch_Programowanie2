package pl.sda.rafal.zientara.programowanie2.lesson6;

public class FootballPresenter implements FootballContract.Presenter {
    private final FootballContract.View view;
    private final FootballBoard board;

    private Point currentPosition;
    private boolean playerTopTurn = true;

    public FootballPresenter(FootballContract.View view, FootballBoard board) {
        this.view = view;
        this.board = board;
    }

    @Override
    public void init() {
        currentPosition = new Point(board.width / 2,
                board.height / 2);
        view.updatePosition(currentPosition);
    }

    @Override
    public void moveTop() {
        move(0, -1);
    }

    @Override
    public void moveTopRight() {
        move(1, -1);
    }

    @Override
    public void moveRight() {
        move(1, 0);
    }

    @Override
    public void moveBottomRight() {
        move(1, 1);
    }

    @Override
    public void moveBottom() {
        move(0, 1);
    }

    @Override
    public void moveBottomLeft() {
        move(-1, 1);
    }

    @Override
    public void moveLeft() {
        move(-1, 0);
    }

    @Override
    public void moveTopLeft() {
        move(-1, -1);
    }

    private void move(int x, int y) {
        //x e <-1,1>
        //y e <-1,1>
//        currentPosition
        System.out.printf("move x%d y%d \n",x,y);
        Point newPosition = new Point(currentPosition.x + x,
                currentPosition.y + y);
        if (!board.lineExists(currentPosition, newPosition)) {
            LineType type = getTypeByTurn();

            if(!board.hasAnyConnection(newPosition)) {
                playerTopTurn = !playerTopTurn;
            }

            board.addLine(new Line(currentPosition, newPosition, type));
            currentPosition = newPosition;
            view.updatePosition(currentPosition);
            view.updateCurrentPlayer(getTypeByTurn());
        }
    }

    private LineType getTypeByTurn() {
        return playerTopTurn ? LineType.PLAYER_TOP : LineType.PLAYER_BOTTOM;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }
}
