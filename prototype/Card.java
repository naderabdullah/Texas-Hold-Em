package Poker;
import java.util.*;

public class Card {
	private int rank, suit;
	
	private static String[] ranks = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static String[] suits = {"diamond", "heart", "spade", "club"};
	
	
	
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getSuit() {
		return suit;
	}
	
	protected void setRank(int Rank) {
		Rank = rank;
	}
	
	protected void setSuit(int Suit) {
		Suit = suit;
	}
	
	public static String rankAsString(int RANK) {
		return ranks[RANK];
	}
	
	public static String suitAsString(int SUIT) { 
		return suits[SUIT];
	}
	
	//METHODS FOR CARD CLASS
	
	public static boolean sameCardCheck(Card card1, Card card2) {
		return (card1.rank == card2.rank && card1.suit == card2.suit);
	}
	
	/*public static class rankComparator implements Comparator<Object> {
	    public int compare(Object card1, Object card2) throws ClassCastException {
	        // verify two Card objects are passed in
	       // if (!((card1 instanceof Card) && (card2 instanceof Card))){
	         //   throw new ClassCastException("A Card object was expected.  Parameter 1 class: " + card1.getClass() 
	           //         + " Parameter 2 class: " + card2.getClass());
	        //}

	        int rank1 = ((Card)card1).getRank();
	        int rank2 = ((Card)card2).getRank();

	        return rank1 - rank2;
	    }
	    
	    rankComparator() {
	    	
		}
	}
	
	public static class suitComparator implements Comparator<Object> {
	    public int compare(Object card1, Object card2) throws ClassCastException {
	        // verify two Card objects are passed in
	      //  if (!((card1 instanceof Card) && (card2 instanceof Card))){
	        //    throw new ClassCastException("A Card object was expeected.  Parameter 1 class: " + card1.getClass() 
	          //          + " Parameter 2 class: " + card2.getClass());
	        //}

	        int suit1 = ((Card)card1).getSuit();
	        int suit2 = ((Card)card2).getSuit();

	        return suit1 - suit2;
	    }
	    
	    suitComparator() {
	    	
	    }
	    
	}
*/
}
