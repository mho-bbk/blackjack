package blackjack;

import java.util.List;
import java.util.Scanner;

public class PlayerVsAiDealerBlackJack extends Blackjack {

    private final Hand player = new Hand();
    private final Hand ai = new Hand();

    public PlayerVsAiDealerBlackJack() {
        ai.addName("Dealer");
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

        deal(ai);
        printOneCard(ai);

        //Evaluate the player's dealt hand in case they've already won
        checkHandStatus(player);

        if(!gameOver) {
            System.out.println(player.getName() + " goes first.");
            boolean playersTurn = true;

            while(!gameOver && playersTurn) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit(player);
                    player.printHand();
                    checkHandStatus(player);
                } else {
                    stand(player);
                    player.printHand();
                    playersTurn = false;
                }
            }

            //AI Dealer's turn begins
            System.out.println("LET'S REVEAL THE DEALER'S HAND...!");
            ai.printHand();

            while(!gameOver) {
                dealerPlay();
            }
        }

        printGame();
    }

    private void dealerPlay() {
        //Dealer makes automated decisions:
        //If >16, stand. Ace must be 11 if this makes the score >16. Else, hit.
        if(ai.getScores()[0] > 16 && ai.getScores()[0] < 22 ||
                ai.getScores()[1] > 16 && ai.getScores()[1] < 22) {
            stand(ai);
            //Game is over when the dealer stands
            gameOver = true;
        } else {
            hit(ai);
            ai.printHand();
            checkHandStatus(ai);
        }
    }

    private void printOneCard(Hand hand) {
        List<String> cards = hand.getCards();
        System.out.println("Dealer's Hand: " + "[" + cards.get(0) + ", ???]");
    }

    @Override
    public void printGame() {
        printWinner();
        player.printHand();
        ai.printHand();
    }


    private void printWinner() {
//        int[] playerScores = player.getScores();
//        int[] dealerScores = ai.getScores();
//        int bestValidPlayerScore;
//        int bestValidDealerScore;
//
//        //TODO - this needs unit tests (manual tests = inconsistent coverage)
//        if(playerScores[0] < 22 && playerScores[0] > playerScores[1] ) {
//            bestValidPlayerScore = playerScores[0];
//        } else if(playerScores[1] < 22 && playerScores[1] > playerScores[0]) {
//            bestValidPlayerScore = playerScores[1];
//        } else {
//            bestValidPlayerScore = 0;
//        }
//
//        if(dealerScores[0] < 22 && dealerScores[0] > dealerScores[1]) {
//            bestValidDealerScore = dealerScores[0];
//        } else if (dealerScores[1] < 22 && dealerScores[1] > dealerScores[0]) {
//            bestValidDealerScore = dealerScores[1];
//        } else {
//            bestValidDealerScore = 0;
//        }

        Hand winner = player.compareWinning(ai);

        if (winner!= null) {
            if (winner.equals(ai)) {
                System.out.println("Dealer wins!");
            } else if (winner.equals(player)) {
                System.out.println(player.getName() + " wins!");
            }
        } else {
            System.out.println(player.getName() + " and the Dealer drew.");
        }
    }
}
