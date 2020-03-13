package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import javax.swing.*;
import java.util.List;

public class DashboardView implements DashboardContract.View {

    private DashboardScreen dashboardScreen;

    public DashboardView(DashboardScreen dashboardScreen) {
        this.dashboardScreen = dashboardScreen;

    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        dashboardScreen.onWithdrawalConfirm(dashboardScreen.getNotesToWithdraw());
    }

    @Override
    public void onPossibleLowestWithdraw(String value) {
        dashboardScreen.getMessage().setText("Still too small");
    }

    @Override
    public void notDivisibleByNotesError(int value) {
        dashboardScreen.getMessage().setText("Not divisible by available notes, try in Afghanistan.");
    }

    @Override
    public void notEnoughNotesError(int value) {
        dashboardScreen.getMessage().setText("Sorry, but there are no available notes, Try in another cash machine");
    }

    @Override
    public void notNumericError(String value) {
        dashboardScreen.getMessage().setText("Not numeric");
        disableConfirmButton();

    }


    @Override
    public void hideError() {
        dashboardScreen.getMessage().setText("");
        enableConfirmButton();
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
