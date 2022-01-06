import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    @Test
    public void testAddCardAndUpdateScoreOneAce() {
        Hand hand = new Hand();

        //Scenario 1 in the brief
        hand.addCard("A");
        hand.addCard("K");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(11, scores[0]);
        assertEquals(21, scores[1]);
    }

    @Test
    public void testAddCardAndUpdateScoreTwoAces() {
        Hand hand = new Hand();

        //Scenario 3 in the brief
        hand.addCard("9");
        hand.addCard("A");
        hand.addCard("A");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(11, scores[0]);
        assertEquals(21, scores[1]);
    }

    @Test
    public void testAddCardAndUpdateScoreThreePlusAces() {
        Hand hand = new Hand();

        hand.addCard("A");
        hand.addCard("A");
        hand.addCard("A");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(3, scores[0]);
        assertEquals(13, scores[1]);

        //Four aces and the result should only be +1 (not +11)
        hand.addCard("A");
        hand.updateScore();

        scores = hand.getScore();
        assertEquals(4, scores[0]);
        assertEquals(14, scores[1]);
    }

    @Test
    public void testAddCardAndUpdateScoreNoAce() {
        Hand hand = new Hand();

        hand.addCard("2");
        hand.addCard("K");
        hand.addCard("Q");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(22, scores[0]);
        assertEquals(0, scores[1]);
    }

    @Test
    public void testAddCardAndUpdateScoreAllLetters() {
        Hand hand = new Hand();

        //Scenario 2 in the brief
        hand.addCard("K");
        hand.addCard("Q");
        hand.addCard("A");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(21, scores[0]);
        assertEquals(31, scores[1]);
    }

    @Test
    public void testEvaluateHand() {
        Hand hand = new Hand();

        //Scenario 1 in the brief
        hand.addCard("K");
        hand.addCard("A");
        hand.updateScore();
        Assert.assertEquals(0, hand.evaluate());

        //Scenario 1 in the brief - but with A and K in different orders
        hand = new Hand();
        hand.addCard("A");
        hand.addCard("K");
        hand.updateScore();
        Assert.assertEquals(0, hand.evaluate());

        //Scenario 2 in the brief
        hand = new Hand();
        hand.addCard("K");
        hand.addCard("A");
        hand.addCard("Q");
        hand.updateScore();
        Assert.assertEquals(0, hand.evaluate());

        //Example bust hand
        hand = new Hand();
        hand.addCard("K");
        hand.addCard("K");
        hand.addCard("K");
        hand.updateScore();
        Assert.assertEquals(-1, hand.evaluate());

        //Example starter hand (only one low value card)
        hand = new Hand();
        hand.addCard("5");
        hand.updateScore();
        Assert.assertEquals(1, hand.evaluate());

        //Example - one calculation of the score is bust but other still valid
        hand = new Hand();
        hand.addCard("A");
        hand.addCard("A");
        hand.addCard("2");
        hand.updateScore();
        Assert.assertEquals(1, hand.evaluate());

        //Example - one calculation of the score is bust but other still valid (different order)
        hand = new Hand();
        hand.addCard("2");
        hand.addCard("A");
        hand.addCard("A");
        hand.updateScore();
        Assert.assertEquals(1, hand.evaluate());

        //Example - Hand is bust and there are no aces (so one of the Hand's scores must be 0)
        hand = new Hand();
        hand.addCard("10");
        hand.addCard("6");
        hand.addCard("7");
        hand.updateScore();
        Assert.assertEquals(-1, hand.evaluate());
    }

    @Test
    public void printHand() {
        Hand hand = new Hand();

        //Example starting hand
        hand.addCard("9");
        hand.addCard("0");
        hand.updateScore();

        hand.printHand();
    }
}