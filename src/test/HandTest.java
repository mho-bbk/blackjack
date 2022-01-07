import blackjack.Hand;
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
        hand.update();

        int[] scores = hand.getScores();
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
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(11, scores[0]);
        assertEquals(21, scores[1]);
    }

    @Test
    public void testAddCardAndUpdateScoreThreePlusAces() {
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
    public void testAddCardAndUpdateScoreNoAce() {
        Hand hand = new Hand();

        hand.addCard("2");
        hand.addCard("K");
        hand.addCard("Q");
        hand.update();

        int[] scores = hand.getScores();
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
        hand.update();

        int[] scores = hand.getScores();
        assertEquals(21, scores[0]);
        assertEquals(31, scores[1]);
    }

    @Test
    public void testEvaluateHand() {
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

        //Example bust hand
        hand = new Hand();
        hand.addCard("K");
        hand.addCard("K");
        hand.addCard("K");
        hand.update();
        Assert.assertEquals(-1, hand.getStatus());

        //Example starter hand (only one low value card)
        hand = new Hand();
        hand.addCard("5");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());

        //Example - one calculation of the score is bust but other still valid
        hand = new Hand();
        hand.addCard("A");
        hand.addCard("A");
        hand.addCard("2");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());

        //Example - one calculation of the score is bust but other still valid (different order)
        hand = new Hand();
        hand.addCard("2");
        hand.addCard("A");
        hand.addCard("A");
        hand.update();
        Assert.assertEquals(1, hand.getStatus());

        //Example - blackjack.Hand is bust and there are no aces (so one of the blackjack.Hand's scores must be 0)
        hand = new Hand();
        hand.addCard("10");
        hand.addCard("6");
        hand.addCard("7");
        hand.update();
        Assert.assertEquals(-1, hand.getStatus());
    }

    @Test
    public void printHand() {
        Hand hand = new Hand();

        //Example starting hand
        hand.addCard("9");
        hand.addCard("0");
        hand.update();

        hand.printHand();
    }

    @Test
    public void testCompareHands() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        //Scenario 1
        handOne.addCard("K");
        handOne.addCard("A");
        handOne.update();

        handTwo.addCard("3");
        handTwo.addCard("5");
        handTwo.addCard("Q");
        handTwo.update();

        assertEquals(1, handOne.compareTo(handTwo));

        //Bust hand vs non-bust hand
        handOne = new Hand();
        handTwo = new Hand();

        handOne.addCard("K");
        handOne.addCard("K");
        handOne.addCard("2");
        handOne.update();

        handTwo.addCard("10");
        handTwo.addCard("8");
        handTwo.update();

        assertEquals(-1, handOne.compareTo(handTwo));
    }
}