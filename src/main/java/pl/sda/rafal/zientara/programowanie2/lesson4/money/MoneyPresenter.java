package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import pl.sda.rafal.zientara.programowanie2.lesson4.money.model.DataCostsProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyPresenter implements
        MoneyContract.Presenter {
    private final MoneyContract.View view;
    private final DataCostsProvider dataCostsProvider;

    private List<Cost> costs = new ArrayList<>();
    private List<Cost> lastResult = new ArrayList<>();
    private double fromPrice = Double.MIN_VALUE;
    private String word;
    private double toPrice;
    private LocalDate fromDate;
    private LocalDate toDate;

    public MoneyPresenter(MoneyContract.View view,
                          DataCostsProvider dataCostsProvider) {
        this.view = view;
        this.dataCostsProvider = dataCostsProvider;
    }

    @Override
    public void prepareData() {
        costs = dataCostsProvider.readCosts();
    }

    @Override
    public void initData() {
        refreshAndShow();
    }

    @Override
    public void onWordChange(String word) {
        this.word = word;
        refreshAndShow();
    }

    @Override
    public void onPriceFromChange(double fromPrice) {
        this.fromPrice = fromPrice;
        refreshAndShow();
    }

    @Override
    public void onPriceToChange(double toPrice) {
        this.toPrice = toPrice;
        refreshAndShow();
    }

    @Override
    public void onFromDateChange(LocalDate fromDate) {
        this.fromDate = fromDate;
        refreshAndShow();
    }

    @Override
    public void onToDateChange(LocalDate toDate) {
        this.toDate = toDate;
        refreshAndShow();
    }

    private void refreshAndShow() {
        refreshResults();
        refreshUi();
    }

    private void refreshUi() {
        view.refreshList(lastResult);
        view.refreshSum(countSum());
    }

    private double countSum() {
        Optional<Double> reduce = lastResult.stream()
                .map(cost -> cost.price)
                .reduce((acc, value) -> acc + value);
//        return reduce.orElse(0D);

//        reduce.orElseThrow(NoChoclateBarsException());
        return reduce.orElse(1231D);
//        return reduce.orElseGet(() -> 0D);//dlugie procesy
        /*if (reduce.isPresent()) {
            return reduce.get();
        } else {
            return 0;
        }*/
    }

    private void refreshResults() {
        Stream<Cost> stream = costs.stream();
        if (word != null) {
            stream = stream.filter(cost -> cost.shopName.contains(word));
        }
        if (fromPrice > 0) {
            stream = stream.filter(cost -> cost.price >= fromPrice);
        }
        if (toPrice > 0) {
            stream = stream.filter(cost -> cost.price <= toPrice);
        }
        if (fromDate != null) {
            stream = stream.filter(cost -> !cost.date.isBefore(fromDate));
        }
        if (toDate != null) {
            stream = stream.filter(cost -> !cost.date.isAfter(toDate));
        }
        lastResult = stream.collect(Collectors.toList());
    }

    @Override
    public List<Cost> getLastResult() {
        return lastResult;
    }
}
