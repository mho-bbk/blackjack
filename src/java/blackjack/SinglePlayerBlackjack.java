package blackjack;

import java.util.Scanner;

public class SinglePlayerBlackjack extends Blackjack {

    private final Hand mainHand = new Hand();

    public SinglePlayerBlackjack() {
        //empty constructor
    }

    public void play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Let's start the game! First, what's your name? ");
        String playerName = scan.nextLine();
        if(!playerName.equals("")) {
            mainHand.addName(playerName);
        }

        System.out.println("Alright! Let's DEAL.");
        deal(mainHand);
        mainHand.printHand();

        //Evaluate hand here in case 21 has been dealt and game can end
        evaluate(mainHand);

        if(!gameOver) {
            while(!gameOver) {
                System.out.println("Hit or Stand? Enter 'H' to hit and any other letter to Stand.");
                String userReply = scan.nextLine();
                if (userReply.equals("H") || userReply.equals("h")) {
                    hit(mainHand);
                    evaluate(mainHand);
                } else {
                    stand(mainHand);
                    gameOver = true;
                    System.out.println("Game finished.");
                }
                mainHand.printHand();
            }
        }
    }

    public void printGame() {
        mainHand.printHand();
    }
}
