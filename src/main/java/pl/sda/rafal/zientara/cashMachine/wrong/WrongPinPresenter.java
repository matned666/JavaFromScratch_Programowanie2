package pl.sda.rafal.zientara.cashMachine.wrong;

public class WrongPinPresenter implements WrongPinContract.Presenter {
    private final WrongPinContract.View view;

    public WrongPinPresenter(WrongPinContract.View view) {
        this.view = view;
    }

    @Override
    public void okClicked() {
        view.confirmMessage();
    }
}
