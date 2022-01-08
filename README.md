# Assumptions

## General

From test brief:

+ Number cards are worth their face value (2-10) 
+ Jacks, queens, and kings are worth 10 each
+ Aces are worth either 1 or 11 (player chooses) *(Note: whilst player's choice, the game is designed to calculate the scores of either the ace as 1 or 11 on the player's behalf. It's then the player's choice to hit or stand.)*
+ The suit of the card does not matter. *(Note: this is interpreted to mean that a Deck of cards is for the purpose of the game, suitless, and that it is not necessary to display the suit of a card).*

Other: 

+ All modes of Blackjack only use one Deck of 52 cards. (A 'mode' being either: single player or player vs dealer.)
+ That when a card is drawn from the Deck, the card is removed from the Deck and added to a Hand.
+ No 2 A's can ever both be 11 because that will bust the hand. So if there is > 1 A, then only 1 A may be 11, and the rest must be 1's.
+ Once the choice to stand is made, that player ends their turn and does not get another turn for the rest of the game.  
+ In a single player game, there's no winner if the player stands and they are not on 21.


## Player vs AI Dealer Mode

Source: https://bicyclecards.com/how-to-play/blackjack/

+ Both player and dealer are dealt two cards at the start of the game.
+ The player's hand is always 'face up' ie always visible.
+ The dealer's hand at the start of the game is only half visible, ie one card in their hand is 'face up' (visible), the other is 'face down' (hidden).
+ Only when the player ends their turn (after deciding to stand) or when the game ends by other means (player reaching 21 or going bust), does the dealer's face down card become visible.
+ The order of play is that the player must end their turn before the dealer acts. 
+ The player wins if they reach 21 exactly, regardless of the dealer's hand.  
+ The player loses if they go bust (ie have a hand that scores above 21 in all possibilities), regardless of the dealer's hand.
+ If the dealer's score is 17 or above, including if they have an ace that could be scored 11, they must stand.
+ If the dealer's score is 16 or below, they must 'hit' until their score is above 17 (incl. ace as 11).
+ If the dealer has an ace, it must be scored as 11 if this brings the total score of the hand to and including 17. 

# Unit Testing Notes

In the unit test comments, I sometimes refer to 'Scenario 1' or 2 or 3. These are taken from the list of scenarios given in the test brief. For ease:

//Scenario 1
Given I have a king and an ace
When my score is evaluated
Then my score is 21

//Scenario 2
Given I have a king, a queen, and an ace
When my score is evaluated
Then my score is 21

//Scenario 3
Given that I have a nine, an ace, and another ace
When my score is evaluated
Then my score is 21	
