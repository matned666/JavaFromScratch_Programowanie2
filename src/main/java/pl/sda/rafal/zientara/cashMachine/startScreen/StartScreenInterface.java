package pl.sda.rafal.zientara.cashMachine.startScreen;

import javax.swing.*;

public interface StartScreenInterface {

    String getCardNumber();

    void correctCardNum();

    void wrongCardNum();

    JLabel getMessage();

    JButton getConfirm();

    JTextField getTextField();

    interface ScreenListener {
        //info
        void onCorrectCardNum();

    }


}
