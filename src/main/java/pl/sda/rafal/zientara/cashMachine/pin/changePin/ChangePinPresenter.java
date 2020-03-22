package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;
import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

import javax.swing.*;

public class ChangePinPresenter implements ChangePinContract.Presenter {

    private ChangePinContract.View view;
    private ChangePinScreen changePinScreen;
    private final Card card;
    private static final int PIN_LENGTH = StaticData.PIN_LENGTH;
    private FileOperations FO;
    private final Cryptology code;

    private JPasswordField oldPin;
    private JPasswordField newPin;
    private JPasswordField newPin2;

      ChangePinPresenter(ChangePinContract.View view, ChangePinScreen changePinScreen, Card card) {
        this.view = view;
        this.changePinScreen = changePinScreen;
        this.card = card;
        code = new Cryptology();
    }

    @Override
    public void onPinTyping(String pin, char typedChar) throws Exception {
          refreshVariables();
          if(onIncorrectPasswordFieldsFill()) {
              if(areNewPinAndConfirmationNOTEqual()){
                  view.onNotEqualPinConfirm();
                  view.disableConfirmButton();
              }
              if (onAnyFieldTooShort()) {
                  view.showTooShortPinError(pin);
                  view.disableConfirmButton();
              }else if (onAnyFileldTooLong()) {
                  view.showTooLongPinError(pin);
                  view.disableConfirmButton();
              }else view.hideError();
          }else{
              view.hideDifferentPinsError();
              view.enableConfirmButton();
              view.hideError();
              if(typedChar == 10) onConfirm();
          }
    }

    private boolean onAnyFileldTooLong() {
        return isTooLong(oldPin) || isTooLong(newPin) || isTooLong(newPin2);
    }

    private boolean onAnyFieldTooShort() {
        return isTooShort(oldPin) || isTooShort(newPin) || isTooShort(newPin2) ;
    }

    private boolean onIncorrectPasswordFieldsFill() {
        return isNOTCorrect(oldPin) || isNOTCorrect(newPin) || isNOTCorrect(newPin2) || areNewPinAndConfirmationNOTEqual();
    }

    @Override
    public void onConfirm() throws Exception {
        FO = new FileOperations((card.getCardNumber()));
          if(pinIsCorrect()) {
              FO.writeDataToFile("1"+code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE + Check.getPassword(changePinScreen.getNewPin())));
              view.onCorrectPin();
          }else{
              view.onWrongOldPin();
          }
    }

    private boolean isTooShort(JPasswordField pas){
        return Check.getPassword(pas).trim().length() < PIN_LENGTH;
    }

  private boolean isTooLong(JPasswordField pas){
      return Check.getPassword(pas).trim().length() > PIN_LENGTH;
    }

    private boolean isNOTCorrect(JPasswordField pas){
        return (pas.getPassword().length != StaticData.PIN_LENGTH) || (!Check.isNumeric(Check.getPassword(pas)));
    }

    private boolean areNewPinAndConfirmationNOTEqual(){
        return !Check.getPassword(newPin).equals(Check.getPassword(newPin2));
    }

    private void refreshVariables(){
        oldPin = changePinScreen.getOldPin();
        newPin = changePinScreen.getNewPin();
        newPin2 = changePinScreen.getNewPinConfirmation();
    }

    private boolean pinIsCorrect() throws Exception {
          return code.checkKey(FO.readDataFromFile().substring(1),StaticData.BANK_ENCRYPT_CODE+Check.getPassword(changePinScreen.getOldPin()));
    }
}
