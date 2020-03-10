package pl.sda.rafal.zientara.programowanie2.lesson6.homework.thanks;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.BaseSwingScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ThanksScreen extends BaseSwingScreen implements ThanksContract.View {
    private final JLabel message;
    private final JLabel moneyInfo;
    private final JButton confirm;
    private final ScreenListener listener;
    private final ThanksContract.Presenter presenter;

    public ThanksScreen(ScreenListener listener, List<Cash> money) {
        presenter = new ThanksPresenter(this, money);
        this.listener = listener;
        //frame ma dostep protected
        frame = new JFrame("Money money money!");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        message = new JLabel("There is Your cash!" + System.lineSeparator() + //todo aktualizacja tekstu w showMeTheMoney()
                "Don't spend it too fast! Bzzzt!");
        frame.add(message);

        moneyInfo = new JLabel();
        frame.add(moneyInfo);

        confirm = new JButton("Ok Thanks! :D");
        confirm.addActionListener(e -> {
            presenter.okClicked();
        });
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

        //todo pokaz wynik w formie:
        //10x2
        //200x1
        //500x3
        for (Cash cash : withdrawal) {
            builder.append(cash.getWorth()).append(", ");
        }
        //todo jesli nic nie wyplacono pokaz odpowiedni komunikat
        moneyInfo.setText(builder.toString());
    }

    public interface ScreenListener {
        void onWithdrawalConfirm();
    }

}
