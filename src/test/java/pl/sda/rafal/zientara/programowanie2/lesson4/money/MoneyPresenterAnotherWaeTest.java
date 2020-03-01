package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sda.rafal.zientara.programowanie2.lesson4.money.model.DataCostsProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MoneyPresenterAnotherWaeTest {

    private MoneyContract.Presenter presenter;
    private MoneyContract.View view;
    private DataCostsProvider provider;

    @BeforeEach
    public void setup() {
        provider = Mockito.mock(DataCostsProvider.class);
        view = Mockito.mock(MoneyContract.View.class);
        presenter = new MoneyPresenter(view, provider);
    }

    @Test
    public void initPresenter() {
        presenter.prepareData();
        presenter.initData();
        verify(view).refreshList(any());
    }

    @Test
    public void whenQueryChangedRefreshResults() {
        when(provider.readCosts())
                .thenReturn(Arrays.asList(new Cost("test",
                        1,
                        LocalDate.of(2020, 1, 1))));
        presenter.prepareData();
        presenter.onPriceFromChange(1000);

        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(1, lastResult.size());
    }
}