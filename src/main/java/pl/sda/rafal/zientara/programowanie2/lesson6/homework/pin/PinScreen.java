package pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;

public class PinScreen extends BaseSwingScreen {
    private final JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    //todo zamiast null przekaz this - co powinna implementowac ta klasa?
    private final PinContract.Presenter presenter = new PinPresenter(null);
    private final ScreenListener listener;//

    public PinScreen(ScreenListener listener) {
        this.listener = listener;
        //frame ma dostêp protected
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Pin:"));
        passwordField = new JPasswordField();
        //todo nas³uchuj wpisywania hasla i daj znac presenterowi
        frame.add(passwordField);

        message = new JLabel();//todo kontrolka do komunikatów o bledzie
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            //todo daj znac presenter o kliknieciu zamiast prosto do listener
            listener.onCorrectPin();
//                listener.onWrongPin();
        });
        frame.add(confirm);
    }

    //    @Override//todo odkomentowac gdy bêd¹ przechodziæ testy z PinPresenterTest
    public void correctPin() {
        listener.onCorrectPin();
    }

    //    @Override//todo odkomentowac gdy bêd¹ przechodziæ testy z PinPresenterTest
    public void wrongPin() {
        listener.onWrongPin();
    }

    public interface ScreenListener {
        //info
        void onCorrectPin();

        void onWrongPin();
    }

}
