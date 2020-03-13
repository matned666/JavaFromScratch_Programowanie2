package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import javax.swing.*;
import java.util.List;

public interface DashboardScreenInterface {
    void onWithdrawalConfirm(List<Cash> money);

    JLabel getMessage();

    List<Cash> getNotesToWithdraw();

    JButton getConfirm();

    interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
    }
}
