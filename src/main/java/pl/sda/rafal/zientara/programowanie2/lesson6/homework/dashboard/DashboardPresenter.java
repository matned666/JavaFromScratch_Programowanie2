package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.Check;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;
import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.CashMachineStorage;

import java.util.*;

public class DashboardPresenter implements DashboardContract.Presenter {



    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;

    private List<Cash> notesToWithdraw;

    DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    @Override
    public void getCash(String value) {

        if(Check.isNumeric(value)) {
            int amount = Integer.parseInt(value);
            if (isPossibleWithdraw(amount) == WithdrawPossibility.POSSIBLE) {
                notesToWithdraw = new LinkedList<>();
                cashWithdraw(amount);
                view.onWithdrawalConfirm(notesToWithdraw);

            } else if (isPossibleWithdraw(amount) == WithdrawPossibility.IMPOSSIBLE) {
                view.notDivisibleByNotesError(amount);
            } else {
                view.notEnoughNotesError(amount);
            }
        }else view.notNumericError(value);
    }

    @Override
    public void onTyping(String value) {
        if(!Check.isNumeric(value)){
            view.notNumericError(value);
        }else{
            view.hideError();
            int amount = Integer.parseInt(value);
            if (amount < getLowestPossibleValue(amount)) {
                view.onPossibleLowestWithdraw(value);
            }
        }
    }

    private int getLowestPossibleValue(int amount) {
        return Arrays.stream(Cash.values()).mapToInt(Cash::getWorth).min().orElseThrow(NoSuchElementException::new);
    }

    private WithdrawPossibility isPossibleWithdraw(int totalValue){
        Cash[] values = Cash.values();
        for (Cash value : values) {
            if (totalValue >= value.getWorth() && machineStorage.availableMoney().contains(value)) {
                totalValue -= value.getWorth();
                return isPossibleWithdraw(totalValue);
            }
        }
        return isPossibleWithdrawInner(totalValue);
    }

    private WithdrawPossibility isPossibleWithdrawInner(int totalValue) {
        if(totalValue > 0) return WithdrawPossibility.IMPOSSIBLE;
        else if(totalValue == 0) return WithdrawPossibility.POSSIBLE;
        else return WithdrawPossibility.NOT_ENOUGH_AVAILABLE_NOTES;
    }


    private boolean cashWithdraw(int totalValue) {
        Cash[] values = Cash.values();
        for (int i = values.length-1; i >= 0; i--) {
            if (totalValue >= values[i].getWorth() && machineStorage.availableMoney().contains(values[i])) {
                totalValue -= values[i].getWorth();
                machineStorage.availableMoney().remove(values[i]);
                notesToWithdraw.add(values[i]);
                return cashWithdraw(totalValue);
            }
        }
        return totalValue == 0;
    }

    public List<Cash> getNotesToWithdraw() {
        return notesToWithdraw;
    }


}
