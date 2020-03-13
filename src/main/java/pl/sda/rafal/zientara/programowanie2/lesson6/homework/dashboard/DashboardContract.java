package pl.sda.rafal.zientara.programowanie2.lesson6.homework.dashboard;

import pl.sda.rafal.zientara.programowanie2.lesson6.homework.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        void onWithdrawalConfirm(List<Cash> money);

        void onPossibleLowestWithdraw(String value);

        void notDivisibleByNotesError(int value);
        void notEnoughNotesError(int value);
        void notNumericError(String value);
        void hideError();

        void enableConfirmButton();
        void disableConfirmButton();
    }

    public interface Presenter {
        void getCash(String value);
        void onTyping(String value);
        List<Cash> getNotesToWithdraw();
        //todo zadbaj o testy!
    }
}
