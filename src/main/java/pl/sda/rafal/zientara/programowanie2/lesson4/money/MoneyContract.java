package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.time.LocalDate;
import java.util.List;

public class MoneyContract {

    public interface View {
        void refreshList(List<Cost> data);

        void refreshSum(double sum);
    }

    public interface Presenter {
        void prepareData();

        void initData();

        void onWordChange(String word);

        void onPriceFromChange(double fromPrice);

        void onPriceToChange(double toPrice);

        void onFromDateChange(LocalDate fromDate);

        void onToDateChange(LocalDate toDate);

        List<Cost> getLastResult();
    }
}
