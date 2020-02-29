package pl.sda.rafal.zientara.programowanie2.lesson5;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.sda.rafal.zientara.tdd.rps.GameAction;
import pl.sda.rafal.zientara.tdd.rps.players.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SpammerTest {

    Player player = Mockito.mock(Player.class);

    @Test
    public void test() {
        when(player.getAction()).thenReturn(GameAction.PAPER);

        GameAction action = player.getAction();
        assertEquals(GameAction.PAPER, action);
    }

}