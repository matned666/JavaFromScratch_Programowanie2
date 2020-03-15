package pl.sda.rafal.zientara.cashMachine.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.cashMachine.model.Cash;
import pl.sda.rafal.zientara.cashMachine.model.CashMachineStorage;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;
    private CashMachineStorage machineStorage;

    @BeforeEach
    void setup() {
        view = mock(DashboardContract.View.class);
        machineStorage = mock(CashMachineStorage.class);
        presenter = new DashboardPresenter(view, machineStorage);
    }

    @Test
    void withdrawCash(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("170");

        verify(view).onWithdrawalConfirm(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20
                        ));
    }

    @Test
    void withdrawButNotEnoughInTheStorage(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("250");

        verify(view).notEnoughNotesError();
    }

    @Test
    void addZeroToWithdraw(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_20));

        presenter.getCash("50");

        verify(view).notEnoughNotesError();
    }

    @Test
    void addNumberNotDivisibleByNotes(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("5");
        verify(view).notDivisibleByNotesError();
    }

    @Test
    void add____NumberWithSpaces___(){
        when(machineStorage.availableMoney()).thenReturn(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));

        presenter.getCash(" 20   ");

        verify(view).onWithdrawalConfirm(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));
    }

    @Test
    void addIllegalNumberFormat(){
        presenter.onTyping("23r");
        verify(view).notNumericError();
    }


    @Test
    void amountBelowZero(){
        presenter.getCash("-300");
        verify(view).notDivisibleByNotesError();
    }



}
