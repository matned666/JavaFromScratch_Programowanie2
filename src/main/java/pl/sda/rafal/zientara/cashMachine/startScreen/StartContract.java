package pl.sda.rafal.zientara.cashMachine.startScreen;

public class StartContract {
    public interface View {

        void onCardNumberConfirmation();

        void wrongCardNumberError();
        void numberFormatError();
        void cardDataError();
        void correctCardNumberLength();
        void onTooLongCardNumberError();
        void onTooShortCardNumberError();
        void hideError();

        void disableConfirmButton();
        void enableConfirmButton();


    }

    public interface Presenter {
        void onType(String cardNumber) throws ClassNotFoundException;
        void onConfirm(String cardNumber) throws Exception;
    }
}
