package pl.sda.rafal.zientara.cashMachine.mainMenu;

public interface MenuInterface {


    void infoButtonPress();
    void withdrawButtonPress();
    void balanceButtonPress();
    void exitButtonPress();
    void changePinButtonPress();



    interface ScreenListener {
        void onBalance();
        void onWithdraw();
        void onExit();
        void onChangePin();
        void onInfo();
    }
}
