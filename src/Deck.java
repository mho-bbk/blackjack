import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Deck {

    private List<String> cards = new ArrayList<>();
//    private HashMap<Character, Integer> card_count = new HashMap<>();

    public Deck() {
        //Every deck should have 4 x cards 2-10, scored as numbered
        //Every deck should have 4 x aces, scored as either 1 or 11 (player's choice)
        //Every deck should have 4 x K, Q, J, scored as 10 each
        for (int i = 0; i < 4; i++) {
            cards.add("K");
            cards.add("Q");
            cards.add("J");
            cards.add("A");
            for (int j = 2; j < 11; j++) {
                cards.add(String.valueOf(j));
            }
        }

    }

    /**
     *
     * @return
     */
    public String draw() {
        return "stub";
    }
}
