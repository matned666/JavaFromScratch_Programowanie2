package pl.sda.rafal.zientara.cashMachine.dashboard;

import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.model.Cash;

import javax.swing.*;
import java.util.List;

public interface DashboardScreenInterface {
    void onWithdrawalConfirm(List<Cash> money);

    JLabel getMessage();

    List<Cash> getNotesToWithdraw();

    JButton getConfirm();

    Card getCard();

    void setCard(Card card);

    interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
    }
}
