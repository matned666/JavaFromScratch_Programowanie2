package pl.sda.rafal.zientara.cashMachine.startScreen;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;

import java.io.File;

public class StartPresenter implements StartContract.Presenter {

    private StartContract.View view;
    private StartScreen startScreen;
    protected static final int CARD_NUMBER_LENGTH = StaticData.CARD_NUMBER_LENGTH;

    public StartPresenter(StartScreen startScreen, StartContract.View view) {
        this.view = view;
        this.startScreen = startScreen;
    }

    @Override
    public void onType(String cardNumber) throws ClassNotFoundException{

        if (cardNumber.trim().length() < CARD_NUMBER_LENGTH && Check.isNumeric(cardNumber)) {
            this.view.onTooShortCardNumberError();
            view.disableConfirmButton();
        } else if (cardNumber.trim().length() > CARD_NUMBER_LENGTH  && Check.isNumeric(cardNumber)) {
            view.onTooLongCardNumberError();
            view.disableConfirmButton();
        } else if (!Check.isNumeric(cardNumber)){
            view.numberFormatError();
            view.disableConfirmButton();
        } else{
            view.hideError();
            view.enableConfirmButton();
        }
    }

    @Override
    public void onConfirm(String cardNumber) {
            File file = new File(StaticData.PATH_TO_RESOURCES + cardNumber + StaticData.CARD_FILE_EXTENSION);
            if(file.exists()) startScreen.correctCardNum();
            else view.wrongCardNumberError();
    }

}
