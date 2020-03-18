package pl.sda.rafal.zientara.cashMachine.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

import static org.junit.jupiter.api.Assertions.*;

class CardLoaderTest {

    private static final String TEST_CARD_NUMBER = "4589619849657531";
    private static final String PATH = "src\\main\\resources\\4589619849657531.card";

    private FileOperations fileOperations;
    private CardLoader loader;



    //Pin is '1234'
    @BeforeEach
    void setup() throws Exception {
        fileOperations = new FileOperations(PATH);
        fileOperations.writeDataToFile("e6tufv0yfmfHy7LXyL18lmmKB3gfN/CqsKskr75HSZKZ8zGHYWhqXudTCndxn7Z3PFQtS+9vezpBuaZHccBeFQ==");
        loader = new CardLoader(TEST_CARD_NUMBER);
    }

    @Test
    void correctPinEnteringLoadsCorrectData() throws Exception {
        loader.ENTER_PIN("1234");

        Card card = loader.getCard();

        assertEquals(card.getPinCondition(),PinCondition.FIRST_ATTEMPT);
        assertEquals(card.getBalance(),"2300");
        assertEquals(card.getOwnerName(),"Mateusz");
        assertEquals(card.getOwnerSurname(),"Niedbal");
    }

    @Test
    void incorrectPinEnteringSavesDataWithNextPinAttempt() throws Exception {
        loader.ENTER_PIN("2356");
        loader = new CardLoader(TEST_CARD_NUMBER);

        assertEquals(loader.getPinCondition(),PinCondition.SECOND_ATTEMPT);

        loader.ENTER_PIN("2056");
        loader = new CardLoader(TEST_CARD_NUMBER);

        assertEquals(loader.getPinCondition(),PinCondition.THIRD_ATTEMPT);


    }

    @Test
    void onSecondAttemptCanStillLoadData() throws Exception {
        loader.ENTER_PIN("2356");
        loader = new CardLoader(TEST_CARD_NUMBER);

        loader.ENTER_PIN("1234");
        Card card = loader.getCard();


        assertEquals(card.getPinCondition(),PinCondition.FIRST_ATTEMPT);
        assertEquals(card.getOwnerName(),"Mateusz");
        assertEquals(card.getOwnerSurname(),"Niedbal");
        assertEquals(card.getBalance(),"2300");

    }

   @Test
    void onThirdAttemptCanStillLoadData() throws Exception {
       loader.ENTER_PIN("2356");
       loader = new CardLoader(TEST_CARD_NUMBER);
       loader.ENTER_PIN("0396");
       loader = new CardLoader(TEST_CARD_NUMBER);
       assertEquals(loader.getPinCondition(),PinCondition.THIRD_ATTEMPT);

       loader.ENTER_PIN("1234");
       Card card = loader.getCard();

       assertEquals(card.getPinCondition(),PinCondition.FIRST_ATTEMPT);
       assertEquals(card.getOwnerName(),"Mateusz");
       assertEquals(card.getOwnerSurname(),"Niedbal");
       assertEquals(card.getBalance(),"2300");
    }

    @Test
    void thirdIncorrectPinEnteringAttemptBlocksCardPermanently() throws Exception {
        fileOperations.writeDataToFile("w2uakdxpFSXHy7LXyL18lmmKB3gfN/CqsKskr75HSZKZ8zGHYWhqXudTCndxn7Z3PFQtS+9vezpBuaZHccBeFQ==");
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN("2356");
        assertThrows(RuntimeException.class, () -> loader = new CardLoader(TEST_CARD_NUMBER));
    }
}