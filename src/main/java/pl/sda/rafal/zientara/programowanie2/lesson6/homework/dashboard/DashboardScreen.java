package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.BaseSwingScreen;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.RandomMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class DashboardScreen extends BaseSwingScreen {
    private final JTextField moneyAmount;
    private final JLabel message;
    private final JButton confirm;

    //todo zamiast null przekaz this - co powinna implementowac ta klasa?
    private final DashboardContract.Presenter presenter = new DashboardPresenter(null, new RandomMachineStorage());
    private final ScreenListener listener;

    public DashboardScreen(ScreenListener listener) {
        this.listener = listener;
        //frame ma dostêp protected
        frame = new JFrame("Insert amount of cash");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Cash:"));
        moneyAmount = new JPasswordField();
        //todo nas³uchuj wpisywania kwoty i przekaz do presentera
        frame.add(moneyAmount);

        message = new JLabel();//todo kontrolka do komunikatów o bledzie
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            //todo daj znac presenter o kliknieciu zamiast prosto do listener
            listener.onCashWithdrawal(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_500));//sztuczne dane
        });
        frame.add(confirm);
    }

    //    @Override//todo odkomentowac gdy interakcja view-presenter bêdzie gotowa
    public void onWithdrawalConfirm(List<Cash> money) {
        listener.onCashWithdrawal(money);
    }

    public interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
    }

}
