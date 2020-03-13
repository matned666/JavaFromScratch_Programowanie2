package pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.Check;


public class PinPresenter implements PinContract.Presenter {
    private PinContract.View view;
    private PinScreen pinScreen;

    private static final int PIN_LENGTH = 4;
    private static final String ACTUAL_PIN = "1234";

    PinPresenter(PinContract.View view) {
        this.view = view;
        pinScreen = new PinScreen();
    }

    PinPresenter(PinScreen pinScreen) {
        this.view = new PinView(pinScreen, this);
        this.pinScreen = pinScreen;
    }

    @Override
    public void onPinTyping(String pin) {
        if (pin.trim().length() < PIN_LENGTH && Check.isNumeric(pin)) {
            view.showTooShortPinError();
            view.disableConfirmButton();
        } else if (pin.trim().length() > PIN_LENGTH  && Check.isNumeric(pin)) {
            view.showTooLongPinError();
            view.disableConfirmButton();
        } else if (!Check.isNumeric(pin)){
            view.showOnlyDigitsError();
            view.disableConfirmButton();
        } else{
            view.hideError();
            view.enableConfirmButton();
        }
    }

    @Override
    public void onPinConfirmed(String pin) {

        if (pin.trim().equals(ACTUAL_PIN)) view.correctPin();
        else view.wrongPin();

    }




}
