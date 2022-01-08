import blackjack.Hand;
import blackjack.util.HandUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HandUtilTest {

    @Test
    public void getWinner21VsOtherValidScoreTest() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        handOne.addCard("K");
        handOne.addCard("A");
        handOne.update();

        handTwo.addCard("3");
        handTwo.addCard("5");
        handTwo.addCard("Q");
        handTwo.update();

        assertEquals(handOne, HandUtil.getWinner(handOne, handTwo));
    }

    @Test
    public void getWinnerNeither21ValidHandsTest() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        handOne.addCard("6");
        handOne.addCard("8");
        handOne.update();

        handTwo.addCard("3");
        handTwo.addCard("5");
        handTwo.addCard("J");
        handTwo.update();

        assertEquals(handTwo, HandUtil.getWinner(handOne, handTwo));
    }

    @Test
    public void getWinnerNeitherValidHandTest() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        //Different bust scores
        handOne.addCard("5");
        handOne.addCard("9");
        handOne.addCard("5");
        handOne.addCard("8");
        handOne.update();

        handTwo.addCard("Q");
        handTwo.addCard("6");
        handTwo.addCard("7");
        handTwo.update();

        assertNull(HandUtil.getWinner(handOne, handTwo));

        //Same bust scores
        handOne.addCard("10");
        handOne.addCard("9");
        handOne.addCard("4");
        handOne.update();

        handTwo.addCard("Q");
        handTwo.addCard("6");
        handTwo.addCard("7");
        handTwo.update();

        assertNull(HandUtil.getWinner(handOne, handTwo));
    }

    @Test
    public void getWinnerValidButDrawHand() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        //Random equal score
        handOne.addCard("7");
        handOne.addCard("4");
        handOne.addCard("8");
        handOne.update();

        handTwo.addCard("10");
        handTwo.addCard("9");
        handTwo.update();

        assertNull(HandUtil.getWinner(handOne,handTwo));

        //Both 21
        handOne = new Hand();
        handTwo = new Hand();

        handOne.addCard("K");
        handOne.addCard("A");
        handOne.update();

        handTwo.addCard("10");
        handTwo.addCard("10");
        handTwo.addCard("A");
        handTwo.update();

        assertNull(HandUtil.getWinner(handOne,handTwo));
    }

    @Test
    public void getWinnerBustVsValidHand() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        //Valid hand not 21
        handOne.addCard("K");
        handOne.addCard("K");
        handOne.addCard("2");
        handOne.update();

        handTwo.addCard("10");
        handTwo.addCard("8");
        handTwo.update();

        assertEquals(handTwo, HandUtil.getWinner(handOne, handTwo));

        //Valid hand is 21
        handOne = new Hand();
        handTwo = new Hand();

        handOne.addCard("K");
        handOne.addCard("K");
        handOne.addCard("2");
        handOne.update();

        handTwo.addCard("10");
        handTwo.addCard("A");
        handTwo.update();

        assertEquals(handTwo, HandUtil.getWinner(handOne, handTwo));
    }

    @Test
    public void getWinnerAceHands() {
        Hand handOne = new Hand();
        Hand handTwo = new Hand();

        //handOne has two possible scores because of ace: one score is valid, one is bust
        handOne.addCard("7");
        handOne.addCard("4");
        handOne.addCard("A");
        handOne.addCard("5");
        handOne.update();

        //Bust hand
        handTwo.addCard("6");
        handTwo.addCard("9");
        handTwo.addCard("K");
        handTwo.update();

        assertEquals(handOne, HandUtil.getWinner(handOne, handTwo));

        //Valid, greater scoring hand
        Hand handThree = new Hand();
        handThree.addCard("3");
        handThree.addCard("7");
        handThree.addCard("8");
        handThree.update();

        assertEquals(handThree, HandUtil.getWinner(handOne, handThree));
    }
}
