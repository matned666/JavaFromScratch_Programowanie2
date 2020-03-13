package pl.sda.rafal.zientara.programowanie2.lesson6.homework.thanks;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.BaseSwingScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ThanksScreen extends BaseSwingScreen implements ThanksContract.View {
    private final JLabel message;
    private final JLabel moneyInfo;
    private final ScreenListener listener;
    private final ThanksContract.Presenter presenter;

    public ThanksScreen(ScreenListener listener, List<Cash> money) {
        presenter = new ThanksPresenter(this, money);
        this.listener = listener;
        //frame ma dostep protected
        frame = new JFrame("Money money money!");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        message = new JLabel();
        frame.add(message);

        moneyInfo = new JLabel();
        frame.add(moneyInfo);

        JButton confirm = new JButton("Ok Thanks! :D");
        confirm.addActionListener(e -> presenter.okClicked());
        frame.add(confirm);
        presenter.init();

    }

    @Override
    public void confirmMessage() {
        listener.onWithdrawalConfirm();
    }

    @Override
    public void showMeTheMoney(List<Cash> withdrawal) {
        StringBuilder builder = new StringBuilder();
        StringBuilder message;



        if(withdrawal.size() > 0) {
            message = new StringBuilder("There is Your cash!" + System.lineSeparator() +
                    "Don't spend it too fast! Bzzzt!");
            for (Cash cash : withdrawal) {
                builder.append(cash.getWorth()).append(", ");
            }
            moneyInfo.setText(builder.toString());
        }else message = new StringBuilder("Nothing to withdraw...");
        this.message.setText(String.valueOf(message));

    }

    public interface ScreenListener {
        void onWithdrawalConfirm();
    }

}
