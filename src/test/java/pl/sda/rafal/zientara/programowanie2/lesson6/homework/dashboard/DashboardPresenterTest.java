package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.CashMachineStorage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        when(machineStorage.availableMoney()).thenReturn(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));

        presenter.getCash("50");

        verify(view).onWithdrawalConfirm(
                Collections.singletonList(
                        Cash.BANK_NOTE_50));
    }

    @Test
    public  void withdrawCash(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_20));

        presenter.onTyping("250");


        verify(view).onWithdrawalConfirm(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_50));
    }

    @Test
    public  void withdrawButNotEnoughInTheStorage(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.onTyping("250");

        verify(view).notEnoughNotesError();
    }

    @Test
    public  void addZeroToWithdraw(){
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.onTyping("0");

        verify(view).notEnoughNotesError();
    }

    @Test
    public  void addNumberNotDivisibleByNotes(){
        presenter.onTyping("55");
        verify(view).notDivisibleByNotesError();
    }

    @Test
    public  void add____NumberWithSpaces___(){
        when(machineStorage.availableMoney()).thenReturn(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));

        presenter.onTyping(" 20   ");

        verify(view).onWithdrawalConfirm(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));
    }

    @Test
    public  void addIllegalNumberFormat(){
        presenter.onTyping("23r");
        verify(view).notNumericError();
    }

    @Test
    public  void smallestNote(){
        presenter.onTyping("10");
        verify(view).onPossibleLowestWithdraw();
    }

    @Test
    public void amountBelowZero(){
        presenter.onTyping("-300");
        verify(view).notDivisibleByNotesError();
    }



}
