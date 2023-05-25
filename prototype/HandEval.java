package Poker;

import java.util.Arrays;

//import Poker.Card.rankComparator;
//import Poker.Card.suitComparator;

	//This class will evaluate the players hand and give it a numerical values from 1 - 10, with 1 being the weakest.
		/*Order of hand strength goes as follows
		1 - High Card ==> Unrelated cards, ranked by highest card
		2 - One Pair ==> Two cards of the same rank (9 and 9)
		3 - Two Pair ==> Two pairs of cards (9 pair and 10 pair)
		4 - Three of a Kind ==> Three cards of same rank (9, 9, 9)
		5 - Straight ==> Five cards in sequence, different suites (1, 2, 3, 4, 5)
		6 - Flush ==> Five cards of the same suite, not in sequence
		7 - Full House ==> Three of a Kind and Pair
		8 - Four of a Kind ==> 4 cards of the same rank
		9 - Straight Flush ==> Five cards of same suite in sequence
		10 - Royal Flush ==> A, K, Q, J, 10 in the same suite
		
		Each player will first receive 2 cards face down. 
		HandEval will run and check each players hand, and then provide them with information on what hand they have.
		Then several rounds of betting begins, where 5 more cards are dealt after each round of betting. These cards are dealt face up in the middle
			All players can use these cards to build their hands
		The FIRST round of betting ends with the Flop, where 3 cards are placed face up in the center. Hand Eval will immediately update the players hand strength. The second round of betting begins afterwards
		The SECOND round of betting ends with the Turn, where one more card is placed face up. Hand Eval will update again.
		The FINAL round of betting ends with the River, where the final card is placed face up. Hand Eval will update for the final time.
		
		A comparison will be done at the end of all betting of all players hands. The player with the highest value hand will win the round and receive all bets placed
		If two players have the same amount then the bets placed are shared between the two
		//////////////////////////////////
		 * 
		Hand Eval will update after each new card is placed on the table, and will notify the player of their hand they hold
		
	[  [1, 1], [1, 2], [1, 3], [1, 4],    // Aces
	  [2, 1], [2, 2], [2, 3], [2, 4],    // Twos 
	  [3, 1], [3, 2], [3, 3], [3, 4],    // Threes
	  [4, 1], [4, 2], [4, 3], [4, 4],    // Fours
	  [5, 1], [5, 2], [5, 3], [5, 4],    // Fives
	  [6, 1], [6, 2], [6, 3], [6, 4],    // Sixes
	  [7, 1], [7, 2], [7, 3], [7, 4],    // Sevens
	  [8, 1], [8, 2], [8, 3], [8, 4],    // Eights
	  [9, 1], [9, 2], [9, 3], [9, 4],    // Nines
	  [10, 1], [10, 2], [10, 3], [10, 4], // Tens
	  [11, 1], [11, 2], [11, 3], [11, 4], // Jacks
	  [12, 1], [12, 2], [12, 3], [12, 4], // Queens
	  [13, 1], [13, 2], [13, 3], [13, 4]  // Kings
	]
		*/

public class HandEval { 
    public Card[] availCards = new Card[7];
    public int count = 0;

    private final static int ONE = 1;
    private final static int TWO = 2;
    private final static int THREE = 3;
    private final static int FOUR = 4;

    // Constructor
    public HandEval(){
    }

    //methods
    protected void addCard(Card card, int i){
        availCards[i] = card;
        count = count + 1;
    }

    protected Card getCard(int i){
        return availCards[i];
    }

    protected int numCards(){
        return availCards.length;
    }
/*
    protected void sortByRank(){
        Arrays.sort(availCards, new rankComparator());
    }

    protected void sortBySuit(){
        Arrays.sort(availCards, new suitComparator());
    }

    protected void sortBySuitThenRank(){
        Arrays.sort(availCards, new suitComparator());
        Arrays.sort(availCards, new rankComparator());
    }

    protected void sortByRankThenSuit(){
        Arrays.sort(availCards, new rankComparator());
        Arrays.sort(availCards, new suitComparator());
    }
*/
    protected String evaluateHand(){
        String handResult = new String();
        int[] rankCounter = new int[13];
        int[] suitCounter = new int[4];

        // initializations
        for (int i = 0; i < rankCounter.length; i++){
            rankCounter[i] =0;
        }

        for (int i = 4; i < suitCounter.length; i++){
            suitCounter[i] = 0;
        }

        // Loop through sorted cards and total ranks
        for(int i = 0; i < count; i++) {
            rankCounter[ availCards[i].getRank() ]++;
            suitCounter[ availCards[i].getSuit() ]++;
        }

        //sort cards for evaluation
        //this.sortByRankThenSuit();

        // hands are already sorted by rank and suit for royal and straight flush checks.       
        // check for royal flush
        handResult = evaluateRoyal(rankCounter, suitCounter);

        // check for straight flush
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateStraightFlush(rankCounter, suitCounter);
        }

        // check for four of a kind
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateFourOfAKind(rankCounter);
        }

        // check for full house
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateFullHouse(rankCounter);
        }

        // check for flush
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateFlush(rankCounter, suitCounter);
        }

        // check for straight
        if (handResult == null || handResult.length() == 0){
            // re-sort by rank, up to this point we had sorted by rank and suit
            // but a straight is suit independent.
           // this.sortByRank();
            handResult = evaluateStraight(rankCounter);
        }

        // check for three of a kind
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateThreeOfAKind(rankCounter);
        }

        // check for two pair
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateTwoPair(rankCounter);
        }

        // check for one pair
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateOnePair(rankCounter);
        }

        // check for highCard
        if (handResult == null || handResult.length() == 0){
            handResult = evaluateHighCard(rankCounter);
        }


        return handResult;
    }

    private String evaluateRoyal(int[] rankCounter, int[] suitCounter){
        String result = "";

        // Check for Royal Flush (10 - Ace of the same suit).
        // check if there are 5 of one suit, if not royal is impossible     
        if ((rankCounter[9] >= 1 &&       /* 10 */
                rankCounter[10] >= 1 &&   /* Jack */
                rankCounter[11] >= 1 &&  /* Queen */
                rankCounter[12] >= 1 &&  /* King */
                rankCounter[0] >= 1)    /* Ace */
                && (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                        suitCounter[2] > 4 || suitCounter[3] > 4)){

            // min. requirements for a royal flush have been met, 
            // now loop through records for an ace and check subsequent cards. 
            // Loop through the aces first since they are the first card to 
            // appear in the sorted array of 7 cards. 
            royalSearch:
                for (int i=0;i<3;i++){
                    // Check if first card is the ace.
                    // Ace must be in position 0, 1 or 2
                    if (availCards[i].getRank() == 0){
                        // because the ace could be the first card in the array
                        // but the remaining 4 cards could start at position 1,
                        // 2 or 3 loop through checking each possibility.
                        for (int j=1;j<4-i;j++){
                            if ((availCards[i+j].getRank() == 9 && 
                            		availCards[i+j+1].getRank() == 10 &&
                            		availCards[i+j+2].getRank() == 11 &&
                               		availCards[i+j+3].getRank() == 12) 
                                    &&
                                    (availCards[i].getSuit() == availCards[i+j].getSuit() &&
                                    availCards[i].getSuit() == availCards[i+j+1].getSuit() &&
                                    availCards[i].getSuit() == availCards[i+j+2].getSuit() &&
                                    availCards[i].getSuit() == availCards[i+j+3].getSuit())){
                                        // Found royal flush, break and return.
                                        result = "Royal Flush";
                                        break royalSearch;              
                            }
                        }
                    }               
                }
        }       
        return result;
    }

    // Straight flush is 5 consecutive cards of the same suit.
    private String evaluateStraightFlush(int[] rankCounter, int[] suitCounter){
        String result = "";
 
        if (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                suitCounter[2] > 4 || suitCounter[3] > 4){

            // min. requirements for a straight flush have been met.
            // Loop through available cards looking for 5 consecutive cards of the same suit,
            // start in reverse to get the highest value straight flush
            for (int i=availCards.length-1;i>3;i--){
                if ((availCards[i].getRank()-ONE == availCards[i-ONE].getRank() && 
                	availCards[i].getRank()-TWO == availCards[i-TWO].getRank() &&
                	availCards[i].getRank()-THREE == availCards[i-THREE].getRank() &&
                	availCards[i].getRank()-FOUR == availCards[i-FOUR].getRank()) 
                        &&
                        (availCards[i].getSuit() == availCards[i-ONE].getSuit() &&
                      		availCards[i].getSuit() == availCards[i-TWO].getSuit() &&
                     		availCards[i].getSuit() == availCards[i-THREE].getSuit() &&
                       		availCards[i].getSuit() == availCards[i-FOUR].getSuit())){
                            // Found royal flush, break and return.
                            result = "Straight Flush";
                            break;
                }
            }
        }
        return result;
    }

    // Four of a kind is 4 cards with the same rank: 2-2-2-2, 3-3-3-3, etc...
    private String evaluateFourOfAKind(int[] rankCounter){
        String result = "";

        for (int i=0;i<rankCounter.length;i++){
            if (rankCounter[i] == FOUR){
                result = "Four of a Kind";
                break;
            }
        }   
        return result;
    }

    // Full house is having 3 of a kind of one rank, and two of a kind of 
    // a second rank.  EX: J-J-J-3-3
    private String evaluateFullHouse(int[] rankCounter){
        String result = "";
        int threeOfKindRank = -1;
        int twoOfKindRank = -1;

        for (int i=rankCounter.length;i>0;i--){
            if ((threeOfKindRank < (int)0) || (twoOfKindRank < (int)0)){
                if ((rankCounter[i-ONE]) > 2){
                    threeOfKindRank = (int) (i-ONE);                  
                }
                else if ((rankCounter[i-ONE]) > 1){
                    twoOfKindRank = (int)(i-ONE);
                }
            }
            else
            {
                break;
            }
        }

        if ((threeOfKindRank >= (int)0) && (twoOfKindRank >= (int)0)){
            result = "Full House";
        }

        return result;
    }

    // Flush is 5 cards of the same suit.
    private String evaluateFlush(int[] rankCounter, int[] suitCounter){
        String result = "";

        // verify at least 1 suit has 5 cards or more.
        if (suitCounter[0] > 4 || suitCounter[1] > 4 ||
                suitCounter[2] > 4 || suitCounter[3] > 4){

            for (int i = availCards.length - 1; i > 3; i--){
                if (availCards[i].getSuit() == availCards[i-ONE].getSuit() &&
                	availCards[i].getSuit() == availCards[i-TWO].getSuit() &&
                	availCards[i].getSuit() == availCards[i-THREE].getSuit() &&
                	availCards[i].getSuit() == availCards[i-FOUR].getSuit()){
                            // Found royal flush, break and return.
                            result = "Flush";
                            break;
                }
            }           
        }


        return result;
    }

    // Straight is 5 consecutive cards, regardless of suit.
    private String evaluateStraight(int[] rankCounter){
        String result = "";

        // loop through rank array to check for 5 consecutive
        // index with a value greater than zero
        for (int i=rankCounter.length;i>4;i--){
            if ((rankCounter[i-1] > 0) &&
                    (rankCounter[i-2] > 0) &&
                    (rankCounter[i-3] > 0) &&
                    (rankCounter[i-4] > 0) &&
                    (rankCounter[i-5] > 0)){
                        result = "Straight";
                        break;
            }
        }
        return result;
    }

    // Three of a kind is 3 cards of the same rank.
    private String evaluateThreeOfAKind(int[] rankCounter){
        String result = "";

        // loop through rank array to check for 5 consecutive
        // index with a value greater than zero
        for (int i=rankCounter.length;i>0;i--){
            if (rankCounter[i-1] > 2){
                        result = "Three of a Kind";
                        break;
            }
        }
        return result;
    }

    // Two pair is having 2 cards of the same rank, and two
    // different cards of the same rank.  EX: 3-3-7-7-A
    private String evaluateTwoPair(int[] rankCounter){
        String result = "";
        int firstPairRank = -1;
        int secondPairRank = -1;

        for (int i=rankCounter.length;i>0;i--){
            if ((firstPairRank < (int)0) || (secondPairRank < (int)0)){             
                if (((rankCounter[i-ONE]) > 1) && (firstPairRank < (int)0)){
                    firstPairRank = (int) (i-ONE);                    
                }
                else if ((rankCounter[i-ONE]) > 1){
                    secondPairRank = (int)(i-ONE);
                }
            }
            else
            {
                // two pair found, break loop.
                break;
            }
        }

        // populate output
        if ((firstPairRank >= (int)0) && (secondPairRank >= (int)0)){
            if (secondPairRank == (int)0){
                // Aces serve as top rank but are at the bottom of the rank array
                // swap places so aces show first as highest pair
                result = "Two Pair";
            }
            else
            {
                result = "Two Pair";
            }           
        }

        return result;
    }

    // One is is two cards of the same rank.
    private String evaluateOnePair(int[] rankCounter){
        String result = "";

        for (int i=rankCounter.length;i>0;i--){
            if((rankCounter[i-ONE]) > 1){
                result = "One Pair";    
                break;
            }
        }
        return result;
    }

    // high card is the highest card out of the 7 possible cards to be used.
    private String evaluateHighCard(int[] rankCounter){
        String result = "";

        for (int i=rankCounter.length;i>0;i--){
            if((rankCounter[i-ONE]) > 0){
                result = "High Card";
                break;
            }
        }
        return result;
    }

}
