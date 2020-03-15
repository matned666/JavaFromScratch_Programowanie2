package pl.sda.rafal.zientara.cashMachine.changePin;

public interface ChangePinScreenInterface {

    void confirmButtonPress();
    void backButtonPress();


    interface ScreenListener {

        void onBackButtonPress();

    }
}
