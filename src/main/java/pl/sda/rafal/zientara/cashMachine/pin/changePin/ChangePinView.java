package pl.sda.rafal.zientara.cashMachine.pin.changePin;

public class ChangePinView implements ChangePinContract.View {

    private ChangePinScreen changePinScreen;


    public ChangePinView(ChangePinScreen changePinScreen) {
        this.changePinScreen = changePinScreen;
    }


    @Override
    public void onNotEqualPinConfirm() {
        changePinScreen.getMessage2().setText("Pin and confirmation are not equal !!");
    }

    @Override
    public void emptyOldPinError(String textField) {
        changePinScreen.getMessage2().setText("Write your old pin to continue");
    }

    @Override
    public void emptyNewPinError(String textField) {
        changePinScreen.getMessage2().setText("Write your new pin");
    }

    @Override
    public void onCorrectPin() {
        changePinScreen.confirmButtonPress();
    }

    @Override
    public void onWrongOldPin() {
        changePinScreen.wrongPasswordPress();
    }

    @Override
    public void disableConfirmButton() {
        changePinScreen.getAcceptButton().setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        changePinScreen.getAcceptButton().setVisible(true);
    }

    @Override
    public void showTooShortPinError(String textField) {
        changePinScreen.getMessage1().setText("ERROR - Too short pin on " + textField);
    }

    @Override
    public void showTooLongPinError(String textField) {
        changePinScreen.getMessage1().setText("ERROR - Too long pin on " + textField);
    }

    @Override
    public void showOnlyDigitsError(String textField) {
        changePinScreen.getMessage1().setText("ERROR - Write only numeric on " + textField);
    }

    @Override
    public void hideError() {
        changePinScreen.getMessage1().setText("OK");
        changePinScreen.getMessage2().setText("OK");
    }

    @Override
    public void hideDifferentPinsError() {
        changePinScreen.getMessage2().setText("Equal pin");
    }
}

