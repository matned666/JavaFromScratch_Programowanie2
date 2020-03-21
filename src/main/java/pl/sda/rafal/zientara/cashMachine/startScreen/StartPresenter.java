package pl.sda.rafal.zientara.cashMachine.startScreen;

import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;

import java.io.File;

public class StartPresenter implements StartContract.Presenter {

    private StartContract.View view;
    private StartScreen startScreen;

    public StartPresenter(StartScreen startScreen, StartContract.View view) {
        this.view = view;
        this.startScreen = startScreen;
    }

    @Override
    public void onConfirm(String cardNumber) {
            File file = new File(cardNumber);
            if(file.exists()) startScreen.correctCardNum();
            else view.wrongCardNumberError();
    }

}
