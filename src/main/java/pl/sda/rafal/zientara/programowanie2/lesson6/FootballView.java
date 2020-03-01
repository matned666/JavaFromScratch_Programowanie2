package pl.sda.rafal.zientara.programowanie2.lesson6;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FootballView extends JTextField
        implements FootballContract.View {
    private static final int CELL_PADDING = 1;
    private FootballBoard board;
    private Point position;
    private LineType player = LineType.PLAYER_TOP;
    private FootballContract.Presenter presenter;

    public FootballView() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD8:
                        presenter.moveTop();
                        break;
                    case KeyEvent.VK_NUMPAD9:
                        presenter.moveTopRight();
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        presenter.moveRight();
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        presenter.moveBottomRight();
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        presenter.moveBottom();
                        break;
                    case KeyEvent.VK_NUMPAD1:
                        presenter.moveBottomLeft();
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        presenter.moveLeft();
                        break;
                    case KeyEvent.VK_NUMPAD7:
                        presenter.moveTopLeft();
                        break;
                    default:
                        System.out.println("olewam " + e);
                }

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));

        g.setColor(Color.LIGHT_GRAY);
        int cellW = getWidth() / (board.width + 2 * CELL_PADDING);
        int cellH = getHeight() / (board.height + 2 * CELL_PADDING);

        for (int i = 0; i <= board.height; i++) {
            int y = (i + CELL_PADDING) * cellH;
            int x1 = CELL_PADDING;
            int x2 = getWidth() - +CELL_PADDING;
            g.drawLine(x1, y, x2, y);
        }
        for (int i = 0; i <= board.width; i++) {
            int y1 = CELL_PADDING;
            int y2 = getHeight() - CELL_PADDING;
            int x = (i + CELL_PADDING) * cellW;
            g.drawLine(x, y1, x, y2);
        }

        //skos
        g.drawLine(0, 0, getWidth(), getHeight());

        g2.setStroke(new BasicStroke(10));
        List<Line> lines = board.getLines();
        for (Line line : lines) {
            g.setColor(getLineColor(line.type));
            g.drawLine((line.start.x + CELL_PADDING) * cellW,
                    (line.start.y + CELL_PADDING) * cellH,
                    (line.end.x + CELL_PADDING) * cellW,
                    (line.end.y + CELL_PADDING) * cellH);
        }

        if (position != null) {
            int r = 5;
            int x = (position.x + CELL_PADDING) * cellW - r;
            int y = (position.y + CELL_PADDING) * cellH - r;
            g.setColor(getLineColor(player));
            g.drawOval(x, y, r * 2, r * 2);
        }

    }

    private Color getLineColor(LineType type) {
        switch (type) {
            case SIDE:
                return Color.BLACK;
            case PLAYER_TOP:
                return Color.MAGENTA;
            case PLAYER_BOTTOM:
                return Color.BLUE;
        }
        return Color.RED;
    }

    public void setBoard(FootballBoard board) {
        this.board = board;
    }

    @Override
    public void updatePosition(Point position) {
        this.position = position;
        repaint();
    }

    @Override
    public void updateCurrentPlayer(LineType player) {
        this.player = player;
    }

    public void setPresenter(FootballContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
