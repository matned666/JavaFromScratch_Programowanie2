package pl.sda.rafal.zientara.cashMachine.pin;

public class PinContract {

    public interface View {
        void disableConfirmButton();
        void enableConfirmButton();

        //errors
        void showTooShortPinError();
        void showTooLongPinError();
        void showOnlyDigitsError();
        void hideError();

        //navigation
        void correctPin();
        void wrongPin();
    }

    public interface Presenter {
        void onPinTyping(String pin);
        void onPinConfirmed(String pin);
    }
}
