package pl.sda.rafal.zientara.cashMachine.pin.changePin;

public class ChangePinView implements ChangePinContract.View {

    private ChangePinContract.Presenter presenter;
    private ChangePinScreen changePinScreen;


    public ChangePinView(ChangePinScreen pinScreen, ChangePinContract.Presenter presenter) {
        this.changePinScreen = changePinScreen;
        this.presenter = presenter;
    }

    @Override
    public void onIllegalTypeFormat() {

    }

    @Override
    public void onNotEqualPinConfirm() {

    }

    @Override
    public void onWrongOldPin() {

    }

    @Override
    public void back() {

    }

    @Override
    public void confirm() {

    }
}
