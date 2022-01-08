package blackjack.util;

import blackjack.Hand;

public class HandUtil {

    /**
     * Compares two hands, such that the Hand that has the greater valid score (the 'winning' hand) is returned.
     * @param handOne for comparison
     * @param handTwo for comparison
     * @return hand with the greater valid score, or null if neither hand is greater than the other nor valid
     */
    public static Hand getWinner(Hand handOne, Hand handTwo) {
        int[] handOneScores = handOne.getScores();
        int[] handTwoScores = handTwo.getScores();

        int handOneBestValidScore = 0;
        int handTwoBestValidScore = 0;

        if (handOneScores[0] > 0 && handOneScores[0] < 22) {
            handOneBestValidScore = handOneScores[0];
            if(handOneScores[1] > 0 && handOneScores[1] < 22 && handOneScores[1] > handOneBestValidScore) {
                handOneBestValidScore = handOneScores[1];
            }
        } else if (handOneScores[1] > 0 && handOneScores[1] < 22) {
            handOneBestValidScore = handOneScores[1];
        }

        if (handTwoScores[0] > 0 && handTwoScores[0] < 22) {
            handTwoBestValidScore = handTwoScores[0];
            if (handTwoScores[1] > 0 && handTwoScores[1] < 22 && handTwoScores[1] > handTwoBestValidScore) {
                handTwoBestValidScore = handTwoScores[1];
            }
        } else if (handTwoScores[1] > 0 && handTwoScores[1] < 22) {
            handTwoBestValidScore = handTwoScores[1];
        }

        if(handTwoBestValidScore > handOneBestValidScore) {
            return handTwo;
        } else if (handTwoBestValidScore < handOneBestValidScore){
            return handOne;
        } else {
            return null;
        }
    }
}
