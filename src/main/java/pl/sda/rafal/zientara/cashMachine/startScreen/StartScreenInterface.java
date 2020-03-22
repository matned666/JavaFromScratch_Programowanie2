package pl.sda.rafal.zientara.cashMachine.startScreen;

import javax.swing.*;

public interface StartScreenInterface {

    String getCardNumber();

    void correctCardNum();

    JLabel getMessage();

    JButton getConfirm();


    interface ScreenListener {
        //info
        void onCorrectCardNum();

    }


}
