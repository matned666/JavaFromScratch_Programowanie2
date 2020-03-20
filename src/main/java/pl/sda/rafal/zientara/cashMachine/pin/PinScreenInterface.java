package pl.sda.rafal.zientara.cashMachine.pin;

import pl.sda.rafal.zientara.cashMachine.card.Card;

import javax.swing.*;

public interface PinScreenInterface {
    void correctPin();

    void wrongPin();

    JLabel getMessage();

    JButton getConfirm();

    JPasswordField getPasswordField();

    String getCardNumber();

    public interface ScreenListener {
        //info
        void onCorrectPin(Card card);

        void onWrongPin();
    }
}
