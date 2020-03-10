package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;

    @BeforeEach
    public void setup() {
        view = mock(DashboardContract.View.class);
        presenter = new DashboardPresenter(view);
    }

    //todo Testy do presentera


}
