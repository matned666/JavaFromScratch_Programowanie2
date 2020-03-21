package pl.sda.rafal.zientara.cashMachine.deposit;

import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.model.Cash;

import javax.swing.*;
import java.util.List;

public interface DepositScreenInterface {

    void onDepositConfirm();

    JLabel getMessage();
    JLabel getMessage2();

    List<Cash> getNotesToDeposit();

    JButton getConfirm();

    Card getCard();

    void setCard(Card card);

    interface ScreenListener {
        void onCashDeposit();
        void onBackFromDeposit();
    }

}
