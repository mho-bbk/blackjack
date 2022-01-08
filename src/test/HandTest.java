import blackjack.Hand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void addCardAndUpdateScoreOneAceTest() {
        Hand hand = new Hand();

        //Scenario 1 in the brief
        hand.addCard("A");
        hand.addCard("K");
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(11, scores[0]);
        assertEquals(21, scores[1]);
    }

    @Test
    public void addCardAndUpdateScoreTwoAcesTest() {
        Hand hand = new Hand();

        //Scenario 3 in the brief
        hand.addCard("9");
        hand.addCard("A");
        hand.addCard("A");
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(11, scores[0]);
        assertEquals(21, scores[1]);
    }

    @Test
    public void addCardAndUpdateScoreThreePlusAcesTest() {
        Hand hand = new Hand();

        hand.addCard("A");
        hand.addCard("A");
        hand.addCard("A");
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(3, scores[0]);
        assertEquals(13, scores[1]);

        //Four aces and the result should only be +1 (not +11)
        hand.addCard("A");
        hand.update();

        scores = hand.getScores();
        assertEquals(4, scores[0]);
        assertEquals(14, scores[1]);
    }

    @Test
    public void addCardAndUpdateScoreNoAceTest() {
        Hand hand = new Hand();

        hand.addCard("2");
        hand.addCard("10");
        hand.addCard("4");
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(16, scores[0]);
        assertEquals(0, scores[1]);
    }

    @Test
    public void addCardAndUpdateScoreAllLettersTest() {
        Hand hand = new Hand();

        //Scenario 2 in the brief
        hand.addCard("K");
        hand.addCard("Q");
        hand.addCard("A");
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(21, scores[0]);
        assertEquals(31, scores[1]);
    }

    @Test
    public void evaluateAllLettersHandTest() {
        Hand hand = new Hand();

        //Scenario 1 in the brief
        hand.addCard("K");
        hand.addCard("A");
        hand.update();
        Assert.assertEquals(0, hand.getStatus());

        //Scenario 1 in the brief - but with A and K in different orders
        hand = new Hand();
        hand.addCard("A");
        hand.addCard("K");
        hand.update();
        Assert.assertEquals(0, hand.getStatus());

        //Scenario 2 in the brief
        hand = new Hand();
        hand.addCard("K");
        hand.addCard("A");
        hand.addCard("Q");
        hand.update();
        Assert.assertEquals(0, hand.getStatus());
    }

    @Test
    public void evaluateBustHandTest() {
        //All letters
        Hand hand = new Hand();
        hand.addCard("K");
        hand.addCard("K");
        hand.addCard("K");
        hand.update();
        Assert.assertEquals(-1, hand.getStatus());

        //No letters
        hand = new Hand();
        hand.addCard("10");
        hand.addCard("4");
        hand.addCard("8");
        hand.update();
        Assert.assertEquals(-1, hand.getStatus());

        //Bust even with ace as 1 or 11
        hand = new Hand();
        hand.addCard("A");
        hand.addCard("10");
        hand.addCard("8");
        hand.addCard("3");
        hand.update();
        Assert.assertEquals(-1, hand.getStatus());
    }

    @Test
    public void evaluateLowScoreHandTest() {
        //Scenario: stand on a starter hand
        Hand hand = new Hand();
        hand.addCard("5");
        hand.addCard("3");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());
    }

    @Test
    public void evaluateOneValidScoreOneBustScoreHandTest() {
        //Ace as 1 = valid, as 11 = bust
        Hand hand = new Hand();
        hand.addCard("A");
        hand.addCard("A");
        hand.addCard("2");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());

        //As above but in different order
        hand = new Hand();
        hand.addCard("2");
        hand.addCard("A");
        hand.addCard("A");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());
    }

    @Test
    public void printHand() {
        Hand hand = new Hand();

        //Example starting hand
        hand.addCard("9");
        hand.addCard("6");
        hand.update();

        hand.printHand();
    }
}