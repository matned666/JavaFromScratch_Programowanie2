package pl.sda.rafal.zientara.cashMachine.deposit;

import pl.sda.rafal.zientara.cashMachine.dashboard.DashboardScreen;
import pl.sda.rafal.zientara.cashMachine.model.Cash;

import java.io.IOException;
import java.util.List;

public class DepositContract {

    public interface View {
        void onDepositConfirm();

        void onCashAdd(int amount, String givenNotesMessage);
    }

    public interface Presenter {
        void deposit() throws Exception;
        void onCashAdd(int value);
    }

}
