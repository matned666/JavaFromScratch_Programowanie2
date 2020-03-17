package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import pl.sda.rafal.zientara.cashMachine.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;

public class ChangePinScreen extends BaseSwingScreen implements ChangePinScreenInterface {

    private JButton confirmButton;
    private JButton backButton;

    private JPasswordField oldPassword;
    private JPasswordField newPassword;
    private JPasswordField newPasswordConfirm;
    private JLabel message;
    private final ChangePinScreenInterface.ScreenListener listener;

    public ChangePinScreen(ChangePinScreenInterface.ScreenListener listener) {
        this.listener = listener;
        confirmButton = new JButton("Confirm");
        backButton = new JButton("BACK");
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        newPasswordConfirm = new JPasswordField();
        message = new JLabel("Fill all 3 text fields correctly to change your PIN");

        frame= new JFrame("Change Pin");

        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(9, 1));
        frame.add(new JLabel("Your old password:"));
        frame.add(oldPassword);
        frame.add(new JLabel("Your new password: "));
        frame.add(newPassword);
        frame.add(new JLabel("Confirm your new password: "));
        frame.add(newPasswordConfirm);
        frame.add(new JLabel(""));
        frame.add(confirmButton);
        frame.add(backButton);
        frame.add(message);
        confirmButton.addActionListener(e -> confirmButtonPress() );
        backButton.addActionListener(e -> backButtonPress() );


        frame.setVisible(true);

    }

    @Override
    public void confirmButtonPress() {

    }

    @Override
    public void backButtonPress() {
        listener.onBackButtonPress();
    }
}
