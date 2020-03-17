package pl.sda.rafal.zientara.cashMachine.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CardLoaderTest {

    private static final String TEST_CARD_NAME = "testcard";

    private FileOperations fileOperations;
    private Cryptology cryptology;



    //TODO tests for Card Loader
    //Pin is '1234'
    @BeforeEach
    void setup() throws IOException {
        fileOperations = new FileOperations(TEST_CARD_NAME);
        fileOperations.writeDataToFile("lnswntenAPUs7Cj96dIEncYow7PET9LoGJUEO1hxGqzQZ4BF9AC+DUepnp6SNOemUgAoJeOlgmif9AtdVSRVRys6sZhGKeFfG7TPsCLhBRsyHcxORGNVoIAQZqpiwWCe");
    }

    @Test
    void correctPinEnteringLoadsCorrectData(){

    }

    @Test
    void incorrectPinEnteringSavesDataWithNextPinAttempt(){

    }

    @Test
    void thirdIncorrectPinEnteringAttemptBlocksCardPermanently(){

    }

    @Test
    void afterEnteringPinCardIfWellSetToUse(){

    }

}