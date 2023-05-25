package Poker;
import java.math.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private Card[] deck = new Card[52];
	
	//Constructor
	public Deck() {
		int i = 0;
		for(int j = 0; j < 4; j++) {
			for(int k = 0; k < 13; k++) {
				deck[i++] = new Card(k, j);
			}
		}
	}
	
	//Find a card if shuffled
	protected int findCard(Card card) {
		for(int i = 0; i < 52; i++) {
			if(Card.sameCardCheck(deck[i], card)) {
				return i;
			}
		}
		return -1;
	}
	
	//return specified card from deck
	protected Card getCard(int cardNum) {
		return deck[cardNum];
	}
	
	protected void swapCards(int i, int Change) {
		Card temp = deck[i];
		deck[i] = deck[Change];
		deck[Change] = temp;
	}
	
	//Shuffles the deck by swapping cards
	protected void shuffle() {
		/*int length = deck.length;
		Random rand = new Random();
		 
		for(int i = 0; i < length; i++) {
			int chng = i + rand.nextInt(length-i);
			swapCards(i, chng);
		}*/
		Collections.shuffle(Arrays.asList(deck));
	}	
}
