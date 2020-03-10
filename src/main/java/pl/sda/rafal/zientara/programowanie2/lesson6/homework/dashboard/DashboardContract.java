package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        void onWithdrawalConfirm(List<Cash> money);
    }

    public interface Presenter {
        void getCash(int value);
        //todo interackja miêdzy o¿yszkodnikiem a presenterem
        //todo zadbaj o testy!
    }
}
