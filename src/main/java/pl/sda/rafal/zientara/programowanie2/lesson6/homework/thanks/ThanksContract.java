package pl.sda.rafal.zientara.programowanie2.lesson6.homework.thanks;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import java.util.List;

public class ThanksContract {

    public interface View {
        void showMeTheMoney(List<Cash> withdrawal);

        void confirmMessage();
    }

    public interface Presenter {
        void init();
        void okClicked();
    }
}
