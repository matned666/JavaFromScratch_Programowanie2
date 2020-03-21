package pl.sda.rafal.zientara.cashMachine.mainMenu;

public interface MenuInterface {


    void infoButtonPress();
    void withdrawButtonPress();
    void depositButtonPress();
    void balanceButtonPress();
    void exitButtonPress();
    void changePinButtonPress();
    void dialog(String message);


    interface ScreenListener {
        void onBalance();
        void onWithdraw();
        void onExit();
        void onChangePin();
        void onInfo();
        void onDeposit();
    }
}
