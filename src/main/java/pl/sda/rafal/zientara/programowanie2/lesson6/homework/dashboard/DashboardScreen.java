package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.BaseSwingScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.RandomMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class DashboardScreen extends BaseSwingScreen implements DashboardScreenInterface {
    private final JTextField moneyAmount;
    private final JLabel message;
    private final JButton confirm;

    private final DashboardContract.View view = new DashboardView(this);
    private final DashboardContract.Presenter presenter = new DashboardPresenter(view, new RandomMachineStorage());
    private final ScreenListener listener;

    public DashboardScreen(ScreenListener listener) {
        this.listener = listener;

        frame = new JFrame("Insert amount of cash");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Cash:"));
        moneyAmount = new JPasswordField();
        moneyAmount.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.onTyping(moneyAmount.getText());
            }
        });
        frame.add(moneyAmount);

        message = new JLabel();
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.addActionListener(e -> presenter.getCash(moneyAmount.getText()));
        frame.add(confirm);
        confirm.setVisible(true);
    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        listener.onCashWithdrawal(money);
    }

    @Override
    public JLabel getMessage() {
        return message;
    }

    @Override
    public List<Cash> getNotesToWithdraw() {
        return presenter.getNotesToWithdraw();
    }

    @Override
    public JButton getConfirm() {
        return confirm;
    }
}
