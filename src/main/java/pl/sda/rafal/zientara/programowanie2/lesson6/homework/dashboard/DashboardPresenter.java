package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.CashMachineStorage;

import java.util.Arrays;

/*
TODO * zadania z gwiazdk¹!
 */
public class DashboardPresenter implements DashboardContract.Presenter {
    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;


    //todo dostepne pieniadze w bankomacie
    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    @Override
    public void getCash(int value) {
        //todo zakladamy ze user ma nielimitowane konto

        //todo wydaj pieniadze
//        view.onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        //todo a co jak nie ma opowiednich banknotów? (user chce 50 a jest 2x20 - za ma³o)
        //todo a co jak nie ma opowiednich banknotów? (user chce 50 a jest 3x20 - kwota niepodzielna)
    }

    //todo DashboardContract

}
