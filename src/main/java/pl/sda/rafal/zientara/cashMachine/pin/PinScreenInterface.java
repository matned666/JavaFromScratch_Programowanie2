package pl.sda.rafal.zientara.cashMachine.pin;

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
        void onCorrectPin();

        void onWrongPin();
    }
}
