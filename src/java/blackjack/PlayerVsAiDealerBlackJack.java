package blackjack;

import blackjack.util.HandUtil;

import java.util.List;
import java.util.Scanner;

public class PlayerVsAiDealerBlackJack extends Blackjack {

    private final Hand player = new Hand();
    private final Hand dealer = new Hand();

    public PlayerVsAiDealerBlackJack() {
        dealer.addName("Dealer");
    }

    @Override
    public void play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Let's start the game! First, what's your name? ");
        String playerName = scan.nextLine();
        if(!playerName.equals("")) {
            player.addName(playerName);
        }

        System.out.println("Alright, let's DEAL!");
        deal(player);
        player.printHand();

        deal(dealer);
        printOneCard(dealer);

        //Evaluate the player's dealt hand in case they've already won
        evaluate(player);

        if(!gameOver) {
            System.out.println(player.getName() + " goes first.");
            boolean playersTurn = true;

            while(!gameOver && playersTurn) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit(player);
                    evaluate(player);
                } else {
                    stand(player);
                    playersTurn = false;
                }
                player.printHand();
            }

            //AI Dealer's turn begins
            System.out.println("LET'S REVEAL THE DEALER'S HAND...!");
            dealer.printHand();

            while(!gameOver) {
                dealerPlay();
            }
        }
        printGame();
    }

    /**
     * Helper method. Builds play logic for the dealer (a special sort of player).
     */
    private void dealerPlay() {
        //Dealer makes automated decisions:
        //If >16, stand. Ace must be 11 if this makes the score >16. Else, hit.
        if(dealer.getScores()[0] > 16 && dealer.getScores()[0] < 22 ||
                dealer.getScores()[1] > 16 && dealer.getScores()[1] < 22) {
            stand(dealer);
            //Game is over when the dealer stands
            gameOver = true;
        } else {
            System.out.println("Dealer has chosen to hit!");
            hit(dealer);
            dealer.printHand();
            evaluate(dealer);
        }
    }

    /**
     * Helper method. Prints only the first card in a hand, hiding the rest.
     * @param hand that will have only one card printed.
     */
    private void printOneCard(Hand hand) {
        List<String> cards = hand.getCards();
        System.out.println("Dealer's Hand: " + "[" + cards.get(0) + ", ???]");
    }

    @Override
    public void printGame() {
        printWinner();
        player.printHand();
        dealer.printHand();
    }

    /**
     * Helper method. Prints the winner of this game.
     */
    private void printWinner() {
        if(player.getStatus() == 0) {
            //Player has 21, and they have won (regardless of if Dealer also has 21)
            //This manually overrides the behaviour of HandUtil.getWinner()
            System.out.println(player.getName() + " wins!");
        } else {
            Hand winner = HandUtil.getWinner(player, dealer);

            if (winner != null) {
                if (winner.equals(dealer)) {
                    System.out.println("Dealer wins!");
                } else if (winner.equals(player)) {
                    System.out.println(player.getName() + " wins!");
                }
            } else {
                System.out.println(player.getName() + " and the Dealer drew.");
            }
        }
    }
}
