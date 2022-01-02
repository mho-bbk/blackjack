import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackjackTest {

    @Test
    public void testCalculateScoresWithAs() {
        List<String> oneAHand = new ArrayList<>(List.of("A", "K"));    //11 or 21
        List<String> twoAHand = new ArrayList<>(List.of("A", "A", "2"));    //4 or 14
        List<String> threeAHand = new ArrayList<>(List.of("A", "A", "A"));  //3 or 13
        List<String> noAHand = new ArrayList<>(List.of("2", "K", "Q"));

        Blackjack blackjack = new Blackjack();
        assertEquals(11, blackjack.calculateScores(oneAHand)[0]);
        assertEquals(21, blackjack.calculateScores(oneAHand)[1]);

        assertEquals(4, blackjack.calculateScores(twoAHand)[0]);
        assertEquals(14, blackjack.calculateScores(twoAHand)[1]);

        assertEquals(3, blackjack.calculateScores(threeAHand)[0]);
        assertEquals(13, blackjack.calculateScores(threeAHand)[1]);

        assertEquals(22, blackjack.calculateScores(noAHand)[0]);
        assertEquals(0, blackjack.calculateScores(noAHand)[1]);
    }

    @Test
    public void testEvaluateHand() {
        Blackjack game = new Blackjack();

        int[] winningHand = new int[2];
        winningHand[0] = 21;
        winningHand[1] = 9;
        assertEquals(0, game.evaluateHand(winningHand));

        int[] winningHandAgain = new int[2];
        winningHandAgain[0] = 7;
        winningHandAgain[1] = 21;
        assertEquals(0, game.evaluateHand(winningHandAgain));

        int[] bustHand = new int[2];
        bustHand[0] = 28;
        bustHand[1] = 23;
        assertEquals(-1, game.evaluateHand(bustHand));

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
    }
}