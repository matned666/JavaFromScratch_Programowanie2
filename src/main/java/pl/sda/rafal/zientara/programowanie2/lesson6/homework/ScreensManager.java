package pl.sda.rafal.zientara.programowanie2.lesson6.homework;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard.DashboardScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin.PinScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.thanks.ThanksScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.wrong.WrongPinScreen;

import java.util.List;

public class ScreensManager implements
        PinScreen.ScreenListener,
        WrongPinScreen.ScreenListener,
        DashboardScreen.ScreenListener,
        ThanksScreen.ScreenListener {
    private PinScreen pinScreen;
    private WrongPinScreen wrongPinScreen;
    private DashboardScreen dashboardScreen;
    private ThanksScreen thanksScreen;

    public void start() {
        pinScreen = new PinScreen(this);
        pinScreen.show();
    }

    @Override
    public void onCorrectPin() {
        pinScreen.hide();
        showDashboard();
    }

    private void showDashboard() {
        dashboardScreen = new DashboardScreen(this);
        dashboardScreen.show();
    }

    @Override
    public void onWrongPin() {
        pinScreen.hide();
        wrongPinScreen = new WrongPinScreen(this);
        wrongPinScreen.show();
    }

    @Override
    public void onWrongPinConfirm() {
        pinScreen.hide();
        wrongPinScreen.hide();
        System.exit(0);
    }

    @Override
    public void onCashWithdrawal(List<Cash> money) {
        dashboardScreen.hide();
        thanksScreen = new ThanksScreen(this, money);
        thanksScreen.show();
    }

    @Override
    public void onWithdrawalConfirm() {
        thanksScreen.hide();
        System.exit(0);
    }
}
