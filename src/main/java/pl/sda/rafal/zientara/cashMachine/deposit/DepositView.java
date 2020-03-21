package pl.sda.rafal.zientara.cashMachine.deposit;

import pl.sda.rafal.zientara.cashMachine.model.Cash;

import java.util.List;

public class DepositView implements DepositContract.View {

    private DepositScreen depositScreen;

    public DepositView(DepositScreen depositScreen) {
        this.depositScreen = depositScreen;
    }

    @Override
    public void onDepositConfirm() {

        depositScreen.onDepositConfirm();
    }

    @Override
    public void onCashAdd(int amount, String givenNotesMessage) {
        depositScreen.getMessage().setText("Actually added money: "+amount);
        depositScreen.getMessage2().setText("Actually added notes: "+givenNotesMessage);
    }
}
