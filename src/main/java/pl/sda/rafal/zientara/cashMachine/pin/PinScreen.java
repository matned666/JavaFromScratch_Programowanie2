package pl.sda.rafal.zientara.cashMachine.pin;

import pl.sda.rafal.zientara.cashMachine.BaseSwingScreen;
import pl.sda.rafal.zientara.cashMachine.ScreensManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PinScreen extends BaseSwingScreen implements PinScreenInterface {
    private final JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    private final String cardNumber;

    private final PinContract.Presenter presenter = new PinPresenter(this);
    private final PinContract.View view = new PinView(this,presenter);

    private final ScreenListener listener;//

    PinScreen() {
        cardNumber = null;
        this.listener = new ScreenListener() {
            @Override
            public void onCorrectPin() {

            }

            @Override
            public void onWrongPin() {

            }
        };
        passwordField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton();
        initialize();
    }

    public PinScreen(ScreensManager listener, String cardNumber) {
        this.cardNumber = cardNumber;
        this.listener = listener;
        passwordField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton("Accept");
        initialize();
    }

    private void initialize() {

        //frame ma dostêp protected
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Pin:"));

        frame.add(passwordField);

        frame.add(message);

        confirm.addActionListener(e -> {
            try {
                presenter.onPinConfirmed(passwordField.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        confirm.setVisible(false);
        frame.add(confirm);

        passwordField.addKeyListener(new KeyListener() {
                                         @Override
                                         public void keyTyped(KeyEvent e) {
                                         }

                                         @Override
                                         public void keyPressed(KeyEvent e) {

                                         }

                                         @Override
                                         public void keyReleased(KeyEvent e) {
                                             presenter.onPinTyping(passwordField.getText());


                                         }
                                     }

        );
    }

        @Override
    public void correctPin() {
        listener.onCorrectPin();
    }

        @Override
    public void wrongPin() {
        listener.onWrongPin();
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
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }
}
