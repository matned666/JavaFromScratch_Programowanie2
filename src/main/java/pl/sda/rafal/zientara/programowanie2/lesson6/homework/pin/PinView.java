package pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin;

public class PinView implements PinContract.View {

    private PinContract.Presenter presenter;
    private PinScreen pinScreen;


    public PinView(PinScreen pinScreen, PinContract.Presenter presenter) {
        this.pinScreen = pinScreen;
        this.presenter = presenter;
    }

    @Override
    public void disableConfirmButton() {
         pinScreen.getConfirm().setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        pinScreen.getConfirm().setVisible(true);
    }

    @Override
    public void showTooShortPinError() {
        pinScreen.getMessage().setText("Too short PIN");
    }

    @Override
    public void showTooLongPinError() {
        pinScreen.getMessage().setText("Too long PIN");
    }

    @Override
    public void showOnlyDigitsError() {
        pinScreen.getMessage().setText("Only digits error, try again");
        disableConfirmButton();
    }

    @Override
    public void hideError() {
        pinScreen.getMessage().setText("");
    }

    @Override
    public void correctPin() {
        pinScreen.correctPin();
    }

    @Override
    public void wrongPin() {
        pinScreen.wrongPin();
    }

    public PinScreen getPinScreen() {
        return pinScreen;
    }
}
