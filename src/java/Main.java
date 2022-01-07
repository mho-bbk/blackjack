import blackjack.Blackjack;
import blackjack.PlayerVsAiDealerBlackJack;
import blackjack.SinglePlayerBlackjack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 to play by yourself or 2 to play with the dealer: ");
        String choice = scan.nextLine();

        Blackjack game;
        if (choice.equals("1")) {
            game = new SinglePlayerBlackjack();
            game.play();
        } else if (choice.equals("2")) {
            game = new PlayerVsAiDealerBlackJack();
            game.play();
        }
    }
}
