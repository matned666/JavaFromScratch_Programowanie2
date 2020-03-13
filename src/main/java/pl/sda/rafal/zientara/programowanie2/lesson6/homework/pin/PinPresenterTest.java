package pl.sda.rafal.zientara.programowanie2.lesson6.homework.pin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PinPresenterTest {

    private PinContract.View view;
    private PinContract.Presenter presenter;

    @BeforeEach
    public void setup() {
        view = mock(PinContract.View.class);
        presenter = new PinPresenter(view);
    }

    @Test
    public void tooShortPin() {
        // given when
        presenter.onPinTyping("1");

        // then
        verify(view).disableConfirmButton();
        verify(view).showTooShortPinError();
    }

    @Test
    public void tooLongPin() {
        // given when
        presenter.onPinTyping("12345");

        // then
        verify(view).disableConfirmButton();
        verify(view).showTooLongPinError();
    }

    @Test
    public void onlyDigitsPin() {
        // given when
        presenter.onPinTyping("1abc");

        // then
        verify(view).disableConfirmButton();
        verify(view).showOnlyDigitsError();
    }

    @Test
    public void correctPin_buttonShouldBeEnabled() {
        // given when
        presenter.onPinTyping("1234");

        // then
        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    @Test
    public void correctPinWithSpaces_buttonShouldBeEnabled() {
        // given when
        presenter.onPinTyping("  1234     ");//hint use .trim()

        // then
        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    @Test
    public void correctPinConfirmed() {
        // given when
        presenter.onPinConfirmed("1234");

        // then
        verify(view).correctPin();
    }
    @Test
    public void wrongPinConfirmed() {
        // given when
        presenter.onPinConfirmed("0000");

        // then
        verify(view).wrongPin();
    }

}