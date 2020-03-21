package pl.sda.rafal.zientara.cashMachine.pin;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;
import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.card.CardLoader;


public class PinPresenter implements PinContract.Presenter {
    private PinContract.View view;
    private PinScreen pinScreen;
    private CardLoader cardloader;
    private static final int PIN_LENGTH = StaticData.PIN_LENGTH;

    PinPresenter(PinContract.View view) {
        this.view = view;
        pinScreen = new PinScreen();
    }

    PinPresenter(PinScreen pinScreen) {
        this.view = new PinView(pinScreen, this);
        this.pinScreen = pinScreen;
    }

    @Override
    public void onPinTyping(String pin, char typedChar) throws Exception {
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
            if(typedChar == 10) onPinConfirmed(pin);
            view.hideError();
            view.enableConfirmButton();
        }
    }

    @Override
    public void onPinConfirmed(String pin) throws Exception {

            cardloader = new CardLoader(pinScreen.getCardNumber());
            cardloader.ENTER_PIN(pin.trim());

            if(cardloader.isPassed()) {
                pinScreen.setCard(getCard());
                view.correctPin();

            }
            else view.wrongPin();



    }

    @Override
    public Card getCard() {
        return cardloader.getCard();
    }


}
