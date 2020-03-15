package pl.sda.rafal.zientara.cashMachine.changePin;

public class ChangePinContract {

    public interface View{
        void onIllegalTypeFormat();
        void onNotEqualPinConfirm();
        void onWrongOldPin();

        void back();
        void confirm();
    }

    public  interface Presenter{
        void onPinTyping();

        void onConfirm();

    }
}
