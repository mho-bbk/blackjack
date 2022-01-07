import blackjack.Blackjack;
import blackjack.PlayerVsAiDealerBlackJack;
import blackjack.SinglePlayerBlackjack;

public class Main {

    public static void main(String[] args) {
        Blackjack game = new SinglePlayerBlackjack();
//        game.play();

        game = new PlayerVsAiDealerBlackJack();
        game.play();

        System.out.println("Below is the print of the game from Main: ");
        game.printGame();
    }
}
