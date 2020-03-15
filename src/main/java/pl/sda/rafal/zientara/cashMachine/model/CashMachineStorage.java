package pl.sda.rafal.zientara.cashMachine.model;


import java.util.List;

public interface CashMachineStorage {

    List<Cash> availableMoney();
    void remove(Cash cash);

}
