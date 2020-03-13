package pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin;

import javax.swing.*;

public interface PinScreenInterface {
    void correctPin();

    void wrongPin();

    JLabel getMessage();

    JButton getConfirm();

    JPasswordField getPasswordField();

    public interface ScreenListener {
        //info
        void onCorrectPin();

        void onWrongPin();
    }
}
