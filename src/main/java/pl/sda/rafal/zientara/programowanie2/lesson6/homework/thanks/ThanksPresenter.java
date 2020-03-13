package pl.sda.rafal.zientara.programowanie2.lesson6.homework.thanks;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import java.util.List;

public class ThanksPresenter implements ThanksContract.Presenter {
    private final ThanksContract.View view;
    private final List<Cash> money;

    public ThanksPresenter(ThanksContract.View view, List<Cash> money) {
        this.view = view;
        this.money = money;
    }

    @Override
    public void init() {
        view.showMeTheMoney(money);
    }

    @Override
    public void okClicked() {
        view.confirmMessage();
    }
}
