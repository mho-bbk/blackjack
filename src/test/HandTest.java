import blackjack.Hand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class HandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

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
    public void printNumbersHandTest() {
        Hand hand = new Hand();

        hand.addCard("9");
        hand.addCard("6");
        hand.update();
        hand.printHand();

        assertEquals("Player's Hand: [9, 6]. Score: 15", outContent.toString().trim());
    }

    @Test
    public void printBustHandTest() {
        Hand hand = new Hand();
        hand.addCard("K");
        hand.addCard("Q");
        hand.addCard("10");
        hand.update();
        hand.printHand();

        assertEquals("Player's Hand: [K, Q, 10]. Score: 30", outContent.toString().trim());
    }

    @Test
    public void printAceHandTest() {
        Hand hand = new Hand();
        hand.addCard("A");
        hand.addCard("3");
        hand.addCard("9");
        hand.update();
        hand.printHand();

        assertEquals("Player's Hand: [A, 3, 9]. Score: 13 or 23", outContent.toString().trim());
    }
}