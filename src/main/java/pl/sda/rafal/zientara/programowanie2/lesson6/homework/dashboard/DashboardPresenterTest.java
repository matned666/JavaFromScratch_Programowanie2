package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.CashMachineStorage;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;
    private CashMachineStorage machineStorage;

    @BeforeEach
    public void setup() {
        view = mock(DashboardContract.View.class);
        machineStorage = mock(CashMachineStorage.class);
        presenter = new DashboardPresenter(view, machineStorage);
    }

    @Test
    public void getLastCash() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        presenter.getCash("50");

        verify(view).onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50));
    }



}
