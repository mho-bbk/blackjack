import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<String> cards = new ArrayList<>();
    private int[] scores = new int[2];

    public Hand() {
        //empty constructor
    }

    /**
     * Adds a card to the Hand
     * @param card as a String that will be added to the hand.
     */
    public void addCard(String card) {
        cards.add(card);
    }

    /**
     * Calculates the score(s) of this hand.
     * There may be two scores if there is an Ace in the hand. Else, the second score will be 0.
     * @return int[] of size 2 representing score(s) of this hand
     */
    private int[] calculateScores(List<String> cards) {
        //Make a copy so we don't mutate the original instance by accident...
        List<String> handCopy = new ArrayList<>(cards);

        int[] scoreArray = new int[2];
        int score = 0;

        //First we calculate the score without A
        for (String value: handCopy) {
            try {
                score += Integer.parseInt(value);
            } catch(NumberFormatException nfe) {
                if(value.equals("K") || value.equals("Q") || value.equals("J")) {
                    score += 10;
                }
                //if the value is "A" do nothing in this loop
            }
        }

        //No 2 As can ever both be 11 because that will bust the hand
        //so if there is >1 A, then only 1 A may be 11, and the rest must be 1s.
        int countOfA = (int) handCopy.stream().filter(s -> s.equals("A")).count();

        if (countOfA == 0) {
            scoreArray[0] = score;
        } else if (countOfA == 1) {
            int aIsOne = score + 1;
            int aIsEleven = score + 11;
            scoreArray[0] = aIsOne;
            scoreArray[1] = aIsEleven;
        } else {
            int allAsAreOne = score + countOfA;
            int oneAIsEleven = score + 11 + (countOfA - 1);
            scoreArray[0] = allAsAreOne;
            scoreArray[1] = oneAIsEleven;
        }

        return scoreArray;
    }

    /**
     * Updates the total score of the cards within this Hand.
     */
    public void updateScore() {
        scores = calculateScores(cards);
    }

    /**
     * Gets the two possible scores of the Hand.
     * @return int[] of size 2 representing the score(s) of the Hand
     */
    public int[] getScore() {
        return scores;
    }

    /**
     * Tests whether the hand is valid and returns
     *  0 if 21 has been achieved
     *  1 if 21 has not been achieved but the hand is still valid
     *  -1 if the hand is no longer valid ie is bust.
     *
     * A hand is no longer valid when the score is 22 or more.
     * A game is over when the hand is either not valid OR score of 21 has been reached.
     * @return int representing hand validity
     */
    public int evaluate() {
        if(scores[0] == 21 || scores[1] == 21) {
            //Won the game
            return 0;
        } else if (scores[0] > 0 && scores[0] < 21 ||
                scores[1] > 0 && scores[1] < 21) {
            //Hand is valid
            return 1;
        } else {
            //Hand not valid
            //Both scores are > 21, or one is > 21 and the other is 0 (no ace)
            return -1;
        }
    }

    /**
     * Prints the Hand's cards and the total score(s) of the Hand.
     */
    public void printHand() {
        System.out.println("Hand " + this.hashCode() + ": " + cards);  //temporarily using hashcode as identifier for this hand
        printScore();
    }

    /**
     * Prints the score(s) of the Hand in human-friendly format.
     * Only prints scores that are not 0 (as 0 is only the result of no aces in the Hand)
     */
    private void printScore() {
        StringBuilder scoreString = new StringBuilder("Score: ");

        for (int score: scores) {
            if(score != 0) {
                scoreString.append(score).append(" or ");
            }
        }

        System.out.println(scoreString.substring(0, scoreString.length() - 4));
    }
}
