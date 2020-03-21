package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;
import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.card.CardLoader;
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



      ChangePinPresenter(ChangePinContract.View view, ChangePinScreen changePinScreen, Card card) {
        this.view = view;
        this.changePinScreen = changePinScreen;
        this.card = card;
        code = new Cryptology();


    }


    @Override
    public void onPinTyping(String textField, char typedChar) throws Exception {

          if(!finishStatement(changePinScreen.getNewPinConfirmation()) || !finishStatement(changePinScreen.getNewPin()) || !finishStatement(changePinScreen.getOldPin()) && !finishStatment()) {
              if (ifStatements(1, changePinScreen.getOldPin()) || ifStatements(1, changePinScreen.getNewPin()) || ifStatements(1, changePinScreen.getNewPinConfirmation())) {
                  view.showTooShortPinError(textField);
                  view.disableConfirmButton();
              } else if (ifStatements(2, changePinScreen.getOldPin()) || ifStatements(2, changePinScreen.getNewPin()) || ifStatements(2, changePinScreen.getNewPinConfirmation())) {
                  view.showTooLongPinError(textField);
                  view.disableConfirmButton();
              } else if (ifStatements(3, changePinScreen.getOldPin()) || ifStatements(3, changePinScreen.getNewPin()) || ifStatements(3, changePinScreen.getNewPinConfirmation())) {
                  view.showOnlyDigitsError(textField);
                  view.disableConfirmButton();
              }

              if (!changePinScreen.getNewPin().getText().equals(changePinScreen.getNewPinConfirmation().getText())) {
                  view.onNotEqualPinConfirm();
                  view.disableConfirmButton();
              } else if (changePinScreen.getNewPin().getText() == null || changePinScreen.getNewPinConfirmation().getText() == null) {
                  view.disableConfirmButton();
              } else if (changePinScreen.getOldPin().getText() == null) {
                  view.disableConfirmButton();
              }

          }else{
              if(typedChar == 10) onConfirm();
              view.hideDifferentPinsError();
              view.enableConfirmButton();
              view.hideError();

          }

    }

    @Override
    public void onConfirm() throws Exception {
        FO = new FileOperations((card.getCardNumber()));
          if(pinIsCorrect()) {
              FO.writeDataToFile("1"+code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE + changePinScreen.getNewPin().getText()));
              view.onCorrectPin();
          }else{
              view.onWrongOldPin();
          }
    }

    private boolean pinIsCorrect() throws Exception {
          return code.checkKey(FO.readDataFromFile().substring(1),StaticData.BANK_ENCRYPT_CODE+changePinScreen.getOldPin().getText());
    }

    private boolean ifStatements(int id, JPasswordField pas){
          if(id == 1) return pas.getText().trim().length() < PIN_LENGTH && Check.isNumeric(pas.getText().trim());
          else if(id == 2) return pas.getText().trim().length() > PIN_LENGTH  && Check.isNumeric(pas.getText());
          else if(id == 3) return !Check.isNumeric(pas.getText());
          else return false;
    }

    private boolean finishStatement(JPasswordField pas){
          return (pas.getPassword().length == StaticData.PIN_LENGTH)&&(Check.isNumeric(pas.getText()));
    }

    private boolean finishStatment(){
          return changePinScreen.getNewPin().getText().equals(changePinScreen.getNewPinConfirmation().getText());
    }


}
