package pl.sda.rafal.zientara.cashMachine.card;

public enum PinCondition {

    FIRST_ATTEMPT,
    SECOND_ATTEMPT,
    THIRD_ATTEMPT;



    public static PinCondition nextTo(PinCondition actual){
        switch (actual){
            case FIRST_ATTEMPT: return SECOND_ATTEMPT;
            case SECOND_ATTEMPT: return THIRD_ATTEMPT;
            default: return null;
        }
    }


}
