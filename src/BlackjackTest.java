import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackjackTest {

    @Test
    public void testEvaluateHand() {
        Blackjack game = new Blackjack();

        int[] winningHand = new int[2];
        winningHand[0] = 21;
        winningHand[1] = 9;
        assertEquals(0, game.evaluateHand(winningHand));

        int[] secondWinningHand = new int[2];
        secondWinningHand[0] = 7;
        secondWinningHand[1] = 21;
        assertEquals(0, game.evaluateHand(secondWinningHand));

        int[] bothBustHand = new int[2];
        bothBustHand[0] = 28;
        bothBustHand[1] = 23;
        assertEquals(-1, game.evaluateHand(bothBustHand));

        int[] exampleStarterHand = new int[2];
        exampleStarterHand[0] = 9;   //hand[1] is 0 by default, no change
        assertEquals(1, game.evaluateHand(exampleStarterHand));

        int[] oneBustStillValidHand = new int[2];
        oneBustStillValidHand[0] = 23;  //bust
        oneBustStillValidHand[1] = 10;  //still valid
        assertEquals(1, game.evaluateHand(oneBustStillValidHand));

        int[] secondBustStillValidHand = new int[2];
        secondBustStillValidHand[0] = 10;  //still valid
        secondBustStillValidHand[1] = 23;  //bust
        assertEquals(1, game.evaluateHand(secondBustStillValidHand));

        int[] oneBustAndNotValid = new int[2];
        oneBustAndNotValid[0] = 23;  //this calc is bust
        //oneBustAndNotValid[1] is 0 by default. 0 score means there are no aces in the hand so the hand only has 1 possible score.
        assertEquals(-1, game.evaluateHand(oneBustAndNotValid));

        int[] scenarioOneAndThree = new int[2];
        scenarioOneAndThree[0] = 11;
        scenarioOneAndThree[1] = 21;
        assertEquals(0, game.evaluateHand(scenarioOneAndThree));

        int[] scenarioTwo = new int[2];
        scenarioTwo[0] = 21;
        scenarioTwo[1] = 31;
        assertEquals(0, game.evaluateHand(scenarioTwo));
    }

    public void testPlay() {
        //TODO
    }
}