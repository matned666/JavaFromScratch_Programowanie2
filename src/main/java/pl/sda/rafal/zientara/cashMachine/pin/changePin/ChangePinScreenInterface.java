package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import pl.sda.rafal.zientara.cashMachine.card.Card;

import javax.swing.*;

public interface ChangePinScreenInterface {

    void confirmButtonPress();
    void wrongPasswordPress();
    void backButtonPress();
    Card getCard();

    JPasswordField getOldPin();
    JPasswordField getNewPin();
    JPasswordField getNewPinConfirmation();

    JButton getAcceptButton();
    JLabel getMessage1();
    JLabel getMessage2();


    interface ScreenListener {

        void onWrongChangePin();
        void onCorrectChangePin();
        void onBackButtonPress();

    }
}
