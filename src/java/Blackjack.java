import java.util.Scanner;

public class Blackjack {

    private final Deck deck = new Deck();
    private final Hand mainHand = new Hand();
    private boolean gameOver = false;

    public Blackjack() {
        //empty constructor
    }

    public void play() {
        System.out.println("Let's start the game! DEAL!");
        deal(mainHand);

        if(!gameOver) {
            printHand();

            Scanner scan = new Scanner(System.in);
            while(!gameOver) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit(mainHand);
                } else {
                    stand(mainHand);
                }
                printHand();
            }
        }
    }

    /**
     * Players are initially dealt two cards.
     * 'Deal' is used to initiate the game.
     * After the 'deal', when a player wants a card they must 'hit'.
     */
    private void deal(Hand hand) {
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        hand.updateScore();

        //Evaluate hand here in case 21 has been dealt
        evaluateHand(hand.getScore());
    }

    private void hit(Hand hand) {
        hand.addCard(deck.draw());
        hand.updateScore();
        evaluateHand(hand.getScore());
    }

    private void stand(Hand hand) {
        evaluateHand(hand.getScore());
        System.out.print("STAND! Game finished. ");
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
        } else if (scoreArray[0] > 0 && scoreArray[0] < 21 ||
                scoreArray[1] > 0 && scoreArray[1] < 21) {
            //Hand is valid
            return 1;
        } else {
            //Both scores are > 21, or one is >21 and the other is 0
            System.out.println("BUST! Game over");
            gameOver = true;
            return -1;
        }
    }

    public void printHand() {
        mainHand.printHand();
    }
}
