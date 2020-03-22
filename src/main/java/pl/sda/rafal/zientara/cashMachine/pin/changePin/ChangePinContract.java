package pl.sda.rafal.zientara.cashMachine.pin.changePin;

public class ChangePinContract {

    public interface View{
        void onCorrectPin();
        void onWrongOldPin();

        void onNotEqualPinConfirm();
        void emptyOldPinError(String textField);
        void emptyNewPinError(String textField);

        void disableConfirmButton();
        void enableConfirmButton();

        //errors
        void showTooShortPinError(String textField);
        void showTooLongPinError(String textField);
        void showOnlyDigitsError(String textField);
        void hideError();
        void hideDifferentPinsError();
    }

    public  interface Presenter{
        void onPinTyping(String TextField, char typedChar) throws Exception;

        void onConfirm() throws Exception;

    }
}
