package pl.sda.rafal.zientara.cashMachine.dashboard;

import pl.sda.rafal.zientara.cashMachine.model.Cash;

import java.util.List;

public class DashboardView implements DashboardContract.View {

    private DashboardScreen dashboardScreen;

    public DashboardView(DashboardScreen dashboardScreen) {
        this.dashboardScreen = dashboardScreen;

    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        dashboardScreen.onWithdrawalConfirm(money);
        System.out.println("onWithdrawalConfirm");
    }

    @Override
    public void onPossibleLowestWithdraw() {
        dashboardScreen.getMessage().setText("Still too small");
        System.out.println("onPossibleLowestWithdraw");
    }

    @Override
    public void notDivisibleByNotesError() {
        dashboardScreen.getMessage().setText("Not divisible by available notes, try in Afghanistan.");
        System.out.println("notDivisibleByNotesError");
    }

    @Override
    public void notEnoughNotesError() {
        dashboardScreen.getMessage().setText("Sorry, but there are no available notes, Try in another cash machine");
        System.out.println("notEnoughNotesError");
    }

    @Override
    public void notNumericError() {
        dashboardScreen.getMessage().setText("Not numeric");
        disableConfirmButton();
        System.out.println("notNumericError");
    }


    @Override
    public void hideError() {
        dashboardScreen.getMessage().setText("");
        enableConfirmButton();
        System.out.println("hideError");
    }

    @Override
    public void enableConfirmButton() {
        dashboardScreen.getConfirm().setVisible(true);
    }

    @Override
    public void disableConfirmButton() {
        dashboardScreen.getConfirm().setVisible(false);
    }
}
