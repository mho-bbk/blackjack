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

        //Scenario 3 in the brief
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

        //Scenario 3 in the brief
        hand.addCard("K");
        hand.addCard("Q");
        hand.addCard("A");
        hand.updateScore();

        int[] scores = hand.getScore();
        assertEquals(21, scores[0]);
        assertEquals(31, scores[1]);
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