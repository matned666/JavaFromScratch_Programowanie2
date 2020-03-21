package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ChangePinPresenterTest {

    private ChangePinContract.View view;
    private ChangePinContract.Presenter presenter;

    @BeforeEach
    public void setup() {
        view = mock(ChangePinContract.View.class);
//        presenter = new ChangePinPresenter();
    }
// TODO ALL TESTS

    @Test
    public void changePinCorrectly(){


    }

     @Test
    public void notCorrectOldPin(){

    }

     @Test
    public void notEqualNewPinAndConfirmation(){

    }

     @Test
    public void onTypeNonNumericTypeOnOldPinField(){

    }

     @Test
    public void onTypeNonNumericTypeOnNewPinField(){

    }

     @Test
    public void onTypeNonNumericTypeOnConfirmPinField(){

    }

     @Test
    public void onTypeDifferentAmountOfTypesThanCorrectLengthOfPin(){

    }

     @Test
    public void onCorrectPasswordConfirmation(){

    }






}