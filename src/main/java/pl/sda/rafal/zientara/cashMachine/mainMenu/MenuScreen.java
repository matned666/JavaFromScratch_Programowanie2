package pl.sda.rafal.zientara.cashMachine.mainMenu;

import pl.sda.rafal.zientara.cashMachine.BaseSwingScreen;
import pl.sda.rafal.zientara.cashMachine.card.Card;
import pl.sda.rafal.zientara.cashMachine.dashboard.DashboardScreen;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends BaseSwingScreen implements MenuInterface{

    private DashboardScreen dashboardScreen;

    private  final JButton info;
    private  final JButton withdraw;
    private  final JButton balance;
    private  final JButton exit;
    private  final JButton changePin;

    private final Card card;

    private final MenuInterface.ScreenListener listener;

    public MenuScreen(MenuInterface.ScreenListener listener, Card card) {
        this.card = card;
        this.listener = listener;
        info = new JButton("Account informations");
        withdraw = new JButton("Withdraw money");
        balance = new JButton("Check your ballance");
        exit = new JButton("EXIT");
        changePin = new JButton("Change PIN");

        frame= new JFrame("MAIN MENU");

        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(6, 1));

        info.addActionListener(e -> infoButtonPress() );
        withdraw.addActionListener(e -> withdrawButtonPress() );
        balance.addActionListener(e -> balanceButtonPress() );
        changePin.addActionListener(e -> changePinButtonPress() );
        exit.addActionListener(e -> exitButtonPress() );

        frame.add(info);
        frame.add(new JLabel(" "));
        frame.add(withdraw);
        frame.add(balance);
        frame.add(changePin);
        frame.add(exit);
        frame.setVisible(true);

    }

    @Override
    public void infoButtonPress() {
        listener.onInfo();
    }

    @Override
    public void withdrawButtonPress() {
        listener.onWithdraw();
    }

    @Override
    public void balanceButtonPress() {
        listener.onBalance();
    }

    @Override
    public void exitButtonPress() {
        listener.onExit();
    }

    @Override
    public void changePinButtonPress() {
        listener.onChangePin();
    }
}


