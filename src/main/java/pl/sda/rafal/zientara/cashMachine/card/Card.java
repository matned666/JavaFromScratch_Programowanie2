package pl.sda.rafal.zientara.cashMachine.card;

public class Card {

    private String cardNumber;
    private PinCondition pinCondition;
    private String ownerName;
    private String ownerSurname;
    private String balance;

    private String pin;

    private Card(Builder builder) {
        this.cardNumber = builder.cardNumber;
        this.pinCondition = builder.pinCondition;
        this.ownerName = builder.ownerName;
        this.ownerSurname = builder.ownerSurname;
        this.balance = builder.balance;
        this.pin = builder.pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public PinCondition getPinCondition() {
        return pinCondition;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }
    public String getBalance() {
        return balance;
    }

    public void setPinCondition(PinCondition pinCondition) {
        this.pinCondition = pinCondition;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    public static class Builder {

        private String cardNumber;

        private PinCondition pinCondition;

        private String ownerName;
        private String ownerSurname;
        private String balance;

        private String pin;

        public Builder(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public Builder pinCondition(PinCondition pinCondition) {
            this.pinCondition = pinCondition;
            return this;
        }

        public Builder ownerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        public Builder ownerSurname(String ownerSurname) {
            this.ownerSurname = ownerSurname;
            return this;
        }

        public Builder balance(String balance) {
            this.balance = balance;
            return this;
        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public Card build(){
            return new Card(this);
        }
    }
}