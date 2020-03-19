package pl.sda.rafal.zientara.cashMachine.startScreen;

import pl.sda.rafal.zientara.cashMachine.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartScreen extends BaseSwingScreen implements StartScreenInterface {

    private final ScreenListener listener;

    private final StartContract.View view = new StartView(this);
    private final StartContract.Presenter presenter = new StartPresenter(this, view);
    private final JButton confirm;
    private final JLabel message;
    private final JTextField textField;
    private String cardNumber;

    public StartScreen(ScreenListener listener) {
        this.listener = listener;
        textField = new JTextField();
        message = new JLabel("Welcome");
        confirm = new JButton("Accept");
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cash machine");
        frame.setSize(400, 300);

        frame.setLayout(new GridLayout(5, 1));

        JLabel image = new JLabel(new ImageIcon("src\\main\\resources\\D6uioQEX4AIRiu3.jpg"));
        frame.add(image);
        frame.add(new Label("Enter your card number:"));

        frame.add(textField);

        frame.add(message);

        confirm.addActionListener(e -> {
            try {
                presenter.onConfirm(textField.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        confirm.setVisible(false);
        frame.add(confirm);
        textField.addKeyListener(new KeyListener() {
                                     @Override
                                     public void keyTyped(KeyEvent e) {
                                     }

                                     @Override
                                     public void keyPressed(KeyEvent e) {

                                     }

                                     @Override
                                     public void keyReleased(KeyEvent e) {
                                         presenter.onType(textField.getText());


                                     }
                                 }

        );


    }

    @Override
    public void correctCardNum() {
        setCardNumber();
        listener.onCorrectCardNum();
    }

    @Override
    public void wrongCardNum() {

    }

    @Override
    public JLabel getMessage() {
        return message;
    }

    @Override
    public JButton getConfirm() {
        return confirm;
    }

    @Override
    public JTextField getTextField() {
        return textField;
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }

    private void setCardNumber() {
        this.cardNumber = textField.getText();
    }


}
