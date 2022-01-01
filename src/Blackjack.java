import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {

    private Deck deck = new Deck();
    private static List<String> hand = new ArrayList<>();
    private int[] scoreArray = new int[2];
    int validHand = 1;
    boolean gameOver = false;

    public Blackjack() {
        //empty constructor
    }

    public void play() {
        System.out.println("Let's start the game! DEAL!");
        deal();
        //Evaluate hand here in case 21 has been dealt
        evaluateHand(scoreArray);

        if(!gameOver) {
            printGame();

            Scanner scan = new Scanner(System.in);
            while(!gameOver) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit();
                    printGame();
                } else {
                    stand();
                    printGame();
                }
            }
        }
    }

    /**
     * Players are initially dealt two cards.
     * 'Deal' is used to initiate the game.
     * After the 'deal', when a player wants a card they must 'hit'.
     */
    private void deal() {
        hand.add(deck.draw());
        hand.add(deck.draw());
        updateScore();
    }

    private void hit() {
        hand.add(deck.draw());
        updateScore();
        evaluateHand(scoreArray);
    }

    private void stand() {
        evaluateHand(scoreArray);
        System.out.println("Game finished with final score " + scoreArray[0] + " or " + scoreArray[1]);
        gameOver = true;
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
    //TODO - temporarily public for testing?
    public int evaluateHand(int[] scoreArray) {
        if(scoreArray[0] == 21 || scoreArray[1] == 21) {
            System.out.println("Congratulations! You've won the game.");
            gameOver = true;
            return 0;
        } else if (scoreArray[0] < 21 || scoreArray[1] < 21) {
            //Hand is valid
            return 1;
        } else {
            //Score must be > 21
            System.out.println("BUST! Game over");
            gameOver = true;
            return -1;
        }
    }

    /**
     * Calculates the score(s) of the current hand.
     * There may be two scores if there is an Ace in the hand. Else, the second score will be 0.
     * @return int[] of size 2 representing score(s) of current hand
     */
    //TODO - temporarily public for testing?
    public int[] calculateScores(List<String> hand) {
        //Make a copy so we don't mutate the original instance by accident...
        List<String> handCopy = new ArrayList<>(hand);

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
                //if the value is "A" do nothing
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

    private void updateScore() {
        scoreArray = calculateScores(hand);
    }

    public void printGame() {
        printCurrentHand();
        printCurrentDeck();
        printScore();
    }

    public void printCurrentHand() {
        System.out.println("Current Hand: " + hand.toString());
    }

    public void printCurrentDeck() {
        System.out.println("Current Deck: " + deck.toString());
    }

    //only prints valid scores
    //TODO - when the game goes bust, it prints 'Current Sco' bc takes off last 4 chars...
    public void printScore() {
        StringBuilder scoreString = new StringBuilder("Current Score: ");

        for (int score: scoreArray) {
            if(score != 0) {
                scoreString.append(score).append(" or ");
            }
        }

        System.out.println(scoreString.substring(0, scoreString.length() - 4));
    }
}
