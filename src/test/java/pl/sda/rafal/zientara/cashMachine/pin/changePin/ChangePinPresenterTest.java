package pl.sda.rafal.zientara.cashMachine.pin.changePin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.cashMachine.Check;
import pl.sda.rafal.zientara.cashMachine.StaticData;
import pl.sda.rafal.zientara.cashMachine.card.Card;

import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ChangePinPresenterTest {

    private final String PATH = "src\\main\\resources\\0000000000000000.card";
    private final String PIN = "1234";

    private ChangePinScreen pinscr;
    private ChangePinContract.View view;
    private ChangePinContract.Presenter presenter;

    @BeforeEach
    void setup() throws Exception {

        FileOperations fo = new FileOperations(PATH);
        Cryptology code = new Cryptology();
        Card card = new Card.Builder(PATH)
                .ownerName("Roman")
                .ownerSurname("Pawlak")
                .balance("2000")
                .build();
        fo.writeDataToFile(code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE+PIN));
        view = mock(ChangePinContract.View.class);
        pinscr = new ChangePinScreen(card);
        presenter = new ChangePinPresenter(view, pinscr, card);
    }

// TODO ALL TESTS

    @Test
    void changePinCorrectly() throws Exception {
        pinscr.setNewPassword("1234");
        pinscr.setNewPassword("4444");
        pinscr.setNewPasswordConfirm("4444");

        presenter.onPinTyping((char) 10);

        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

     @Test
    void notCorrectOldPin(){

    }

     @Test
    void notEqualNewPinAndConfirmation(){

    }

     @Test
    void onTypeNonNumericTypeOnOldPinField(){

    }

     @Test
    void onTypeNonNumericTypeOnNewPinField() throws Exception {
         pinscr.setNewPasswordConfirm("44oi");

         presenter.onPinTyping((char) 10);

         verify(view).showOnlyDigitsError();
         verify(view).disableConfirmButton();
    }

     @Test
    void onTypeNonNumericTypeOnConfirmPinField(){

    }

     @Test
    void onTypeDifferentAmountOfTypesThanCorrectLengthOfPin() throws Exception {
         pinscr.setNewPassword("1234");
         pinscr.setNewPassword("4444");
         pinscr.setNewPasswordConfirm("44");

         presenter.onPinTyping((char) 10);

         verify(view).onNotEqualPinConfirm();
    }

}