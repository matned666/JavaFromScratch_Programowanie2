package pl.sda.rafal.zientara.programowanie2.lesson6;

public class FootballContract {

    public interface View {

    }

    public interface Presenter {
        void init();
        void moveTop();
        void moveTopRight();
        void moveRight();
        void moveBottomRight();
        void moveBottom();
        void moveBottomLeft();
        void moveLeft();
        void moveTopLeft();
    }
}
