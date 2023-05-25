package Poker;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Main 
{
	public static void main(String[] args) 
	{	
		Player player = new Player("Player", 1000);
		Deck deck = new Deck();
		HandEval he = new HandEval();
		AI ai1 = new AI ("Nicholas", 1000, 0.5);
		AI ai2 = new AI ("Samir", 1000, 0.5);
		AI ai3 = new AI ("Jack", 1000, 0.5);
		AI ai4 = new AI ("Nader", 1000, 0.5);
		int bet = 20;
		Scanner input = new Scanner(System.in);
		String response;
		int raiseResp;
		ArrayList<AI> ai = new ArrayList<AI>();
		ai.add(ai1);
		ai.add(ai2);
		ai.add(ai3);
		ai.add(ai4);
		int pot = 0;
		boolean raise = false;
		String[] handCon =  {"High Card", "One Pair", "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};
		
		while (player.getIsPlaying() == true)
		{
			player.isPlaying();
			for (int i = 0; i < ai.size(); i++)
			{
				if (ai.get(i).getMoney() <= 0)
				{
					ai.remove(i);
				}
			}
			for(int shuf = 0; shuf < 3; shuf++) {
				deck.shuffle();
			}
			ai1.reset();
			ai2.reset();
			ai3.reset();
			ai4.reset();
	        HandEval player1 = new HandEval();
	        HandEval AI1 = new HandEval();
	        HandEval AI2 = new HandEval();
	        HandEval AI3 = new HandEval();
	        HandEval AI4 = new HandEval();
	        ArrayList<HandEval> handEvals = new ArrayList<HandEval>();
	        handEvals.add(AI1);
	        handEvals.add(AI2);
	        handEvals.add(AI3);
	        handEvals.add(AI4);
	        
	        for(int drawP = 0; drawP < 2; drawP++) {
	        	player1.addCard(deck.getCard(drawP), player1.count);
	        }
	        
	        for(int drawA1 = 2; drawA1 < 4; drawA1++) {
	        	AI1.addCard(deck.getCard(drawA1), AI1.count);
	        }
	        
	        for(int drawA2 = 4; drawA2 < 6; drawA2++) {
	      		AI2.addCard(deck.getCard(drawA2), AI2.count);
	        }
	        
	        for(int drawA3 = 6; drawA3 < 8; drawA3++) {
	        	AI3.addCard(deck.getCard(drawA3), AI3.count);
	        }
	        
	        for(int drawA4 = 8; drawA4 < 10; drawA4++) {
	        	AI4.addCard(deck.getCard(drawA4), AI4.count);
	        }
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	            switch (handEvals.get(i).evaluateHand())
	            {
	                case "High Card":
	                    ai.get(i).setConH(.45);
	                    ai.get(i).setConL(.1);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "One Pair":
	                    ai.get(i).setConH(.7);
	                    ai.get(i).setConL(.15);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Two Pair":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.2);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Three of a Kind":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Flush":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Full House":
	                    ai.get(i).setConH(.85);
	                    ai.get(i).setConL(.3);    
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Four of a Kind":
	                    ai.get(i).setConH(.9);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Royal Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.6);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	            }
	            
	            if (ai.get(i).getIsPlaying() == false)
	            {
	            	ai.remove(i);
	            }
	            else
	            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
	            {
	            	ai.get(i).setBet(bet);
	            }
	            else
	            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getBet();
	            	break;
	            }
	            else 
	            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getMoney();
	            }
	            
	            ai.get(i).setMoney(ai.get(i).getMoney() - bet);
	            pot += bet;
	        }
	        
	        player.setBet(bet);
	        player.bet();

	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        ///////
	        
	        System.out.println("Pot is: " + pot);
	        System.out.println("Bet is: " + bet);
	        System.out.println("Fold,  Call, Raise, or All-in?");
	        response = input.next();
	        if (response.equals("fold"))
	        {
	        	System.exit(0);
	        }
	        else
	        if (response.equals("call") && player.getMoney() >= bet)
	        {
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        }
	        else
	        if (response.equals("raise"))
	        {
	        	System.out.println("How much would you like to raise to?");
	        	raiseResp = input.nextInt();
	        	while (raiseResp < bet || raiseResp > player.getMoney()) 
	        	{
	        		System.out.println("Invalid raise. Try again");
	        		raiseResp = input.nextInt();
	        	}
	        	bet = raiseResp;
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        else
	        if (response.equals("allin"))
	        {
	        	bet = player.getMoney();
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        
	        ///////
	        
	        bet = 20;
	        player1.addCard(deck.getCard(10), 2);
	        player1.addCard(deck.getCard(11), 3);
	        player1.addCard(deck.getCard(12), 4);
	        
	        AI1.addCard(deck.getCard(10), 2);
	        AI1.addCard(deck.getCard(11), 3);
	        AI1.addCard(deck.getCard(12), 4);
	        
	        AI2.addCard(deck.getCard(10), 2);
	        AI2.addCard(deck.getCard(11), 3);
	        AI2.addCard(deck.getCard(12), 4);
	        
	        AI3.addCard(deck.getCard(10), 2);
	        AI3.addCard(deck.getCard(11), 3);
	        AI3.addCard(deck.getCard(12), 4);
	        
	        AI4.addCard(deck.getCard(10), 2);
	        AI4.addCard(deck.getCard(11), 3);
	        AI4.addCard(deck.getCard(12), 4);
	        
	        System.out.print("Flop: ");
	        
	        for (int i = 2; i < 5; i++) 
	        {
	            System.out.print(Card.rankAsString(player1.availCards[i].getRank()) + " " + Card.suitAsString(player1.availCards[i].getSuit())
	            + ", ");
	        }
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	            switch (handEvals.get(i).evaluateHand())
	            {
	                case "High Card":
	                    ai.get(i).setConH(.45);
	                    ai.get(i).setConL(.1);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "One Pair":
	                    ai.get(i).setConH(.7);
	                    ai.get(i).setConL(.15);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Two Pair":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.2);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Three of a Kind":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Flush":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Full House":
	                    ai.get(i).setConH(.85);
	                    ai.get(i).setConL(.3);    
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Four of a Kind":
	                    ai.get(i).setConH(.9);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Royal Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.6);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	            }
	            
	            if (ai.get(i).getIsPlaying() == false)
	            {
	            	ai.remove(i);
	            }
	            else
	            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
	            {
	            	ai.get(i).setBet(bet);
	            }
	            else
	            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getBet();
	            	break;
	            }
	            else 
	            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getMoney();
	            }
	            
	            ai.get(i).setMoney(ai.get(i).getMoney() - bet);
	            pot += bet;
	        }

	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        ///////
	        
	        System.out.println("Pot is: " + pot);
	        System.out.println("Bet is: " + bet);
	        System.out.println("Fold, Call, Raise, or All-in?");
	        response = input.next();
	        if (response.equals("fold"))
	        {
	        	System.exit(0);
	        }
	        else
	        if (response.equals("call") && player.getMoney() >= bet)
	        {
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        }
	        else
	        if (response.equals("raise"))
	        {
	        	System.out.println("How much would you like to raise to?");
	        	raiseResp = input.nextInt();
	        	while (raiseResp < bet || raiseResp > player.getMoney()) 
	        	{
	        		System.out.println("Invalid raise. Try again");
	        		raiseResp = input.nextInt();
	        	}
	        	bet = raiseResp;
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        else
	        if (response.equals("allin"))
	        {
	        	bet = player.getMoney();
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        
	        bet = 20;
	        
	        player1.addCard(deck.getCard(13), 5);
	        
	        AI1.addCard(deck.getCard(13), 5);
	        
	        AI2.addCard(deck.getCard(13), 5);
	        
	        AI3.addCard(deck.getCard(13), 5);
	        
	        AI4.addCard(deck.getCard(13), 5);
	        
	        System.out.print("Flop: ");
	        
	        for (int i = 2; i < 6; i++) 
	        {
	            System.out.print(Card.rankAsString(player1.availCards[i].getRank()) + " " + Card.suitAsString(player1.availCards[i].getSuit())
	            + ", ");
	        }
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	            switch (handEvals.get(i).evaluateHand())
	            {
	                case "High Card":
	                    ai.get(i).setConH(.45);
	                    ai.get(i).setConL(.1);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "One Pair":
	                    ai.get(i).setConH(.7);
	                    ai.get(i).setConL(.15);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Two Pair":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.2);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Three of a Kind":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Flush":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Full House":
	                    ai.get(i).setConH(.85);
	                    ai.get(i).setConL(.3);    
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Four of a Kind":
	                    ai.get(i).setConH(.9);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Royal Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.6);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	            }
	            
	            if (ai.get(i).getIsPlaying() == false)
	            {
	            	ai.remove(i);
	            }
	            else
	            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
	            {
	            	ai.get(i).setBet(bet);
	            }
	            else
	            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getBet();
	            	break;
	            }
	            else 
	            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getMoney();
	            }
	            
	            ai.get(i).setMoney(ai.get(i).getMoney() - bet);
	            pot += bet;
	        }

	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        ///////
	        
	        System.out.println("Pot is: " + pot);
	        System.out.println("Bet is: " + bet);
	        System.out.println("Fold, Call, Raise, or All-in?");
	        response = input.next();
	        if (response.equals("fold"))
	        {
	        	System.exit(0);
	        }
	        else
	        if (response.equals("call") && player.getMoney() >= bet)
	        {
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        }
	        else
	        if (response.equals("raise"))
	        {
	        	System.out.println("How much would you like to raise to?");
	        	raiseResp = input.nextInt();
	        	while (raiseResp < bet || raiseResp > player.getMoney()) 
	        	{
	        		System.out.println("Invalid raise. Try again");
	        		raiseResp = input.nextInt();
	        	}
	        	bet = raiseResp;
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        else
	        if (response.equals("allin"))
	        {
	        	bet = player.getMoney();
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        
	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        bet = 20;
	        
	        player1.addCard(deck.getCard(14), 6);
	        
	        AI1.addCard(deck.getCard(14), 6);
	        
	        AI2.addCard(deck.getCard(14), 6);
	        
	        AI3.addCard(deck.getCard(14), 6);
	        
	        AI4.addCard(deck.getCard(14), 6);
	        
	        System.out.print("Flop: ");
	        
	        for (int i = 2; i < 7; i++) 
	        {
	            System.out.print(Card.rankAsString(player1.availCards[i].getRank()) + " " + Card.suitAsString(player1.availCards[i].getSuit())
	            + ", ");
	        }
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	            switch (handEvals.get(i).evaluateHand())
	            {
	                case "High Card":
	                    ai.get(i).setConH(.45);
	                    ai.get(i).setConL(.1);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "One Pair":
	                    ai.get(i).setConH(.7);
	                    ai.get(i).setConL(.15);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Two Pair":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.2);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Three of a Kind":
	                    ai.get(i).setConH(.75);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.25);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Flush":
	                    ai.get(i).setConH(.8);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Full House":
	                    ai.get(i).setConH(.85);
	                    ai.get(i).setConL(.3);    
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Four of a Kind":
	                    ai.get(i).setConH(.9);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Straight Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.3);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	                case "Royal Flush":
	                    ai.get(i).setConH(1);
	                    ai.get(i).setConL(.6);
	                    ai.get(i).setConfidence();
	                    ai.get(i).aiAction(ai.get(i).getConfidence());
	                    break;
	            }
	            
	            if (ai.get(i).getIsPlaying() == false)
	            {
	            	ai.remove(i);
	            }
	            else
	            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
	            {
	            	ai.get(i).setBet(bet);
	            }
	            else
	            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getBet();
	            	break;
	            }
	            else 
	            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
	            {
	            	bet = ai.get(i).getMoney();
	            }
	            
	            ai.get(i).setMoney(ai.get(i).getMoney() - bet);
	            pot += bet;
	        }

	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        ///////
	        
	        System.out.println("Pot is: " + pot);
	        System.out.println("Bet is: " + bet);
	        System.out.println("Fold, Call, Raise, or All-in?");
	        response = input.next();
	        if (response.equals("fold"))
	        {
	        	System.exit(0);
	        }
	        else
	        if (response.equals("call") && player.getMoney() >= bet)
	        {
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        }
	        else
	        if (response.equals("raise"))
	        {
	        	System.out.println("How much would you like to raise to?");
	        	raiseResp = input.nextInt();
	        	while (raiseResp < bet || raiseResp > player.getMoney()) 
	        	{
	        		System.out.println("Invalid raise. Try again");
	        		raiseResp = input.nextInt();
	        	}
	        	bet = raiseResp;
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        else
	        if (response.equals("allin"))
	        {
	        	bet = player.getMoney();
	        	player.setBet(bet);
	        	player.bet();
	        	pot += player.getBet();
	        	for (int i = 0; i < ai.size(); i++)
	        	{
	        		if (ai.get(i).getIsPlaying() == false)
		            {
		            	ai.remove(i);
		            }
		            else
		            if (ai.get(i).getCall() == true && ai.get(i).getMoney() != 0)
		            {
		            	ai.get(i).setBet(bet);
		            }
		            else
		            if (ai.get(i).getRaise() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getBet();
		            	break;
		            }
		            else 
		            if (ai.get(i).getAllin() == true && ai.get(i).getMoney() != 0)
		            {
		            	bet = ai.get(i).getMoney();
		            }
	        		
	        		ai.get(i).setMoney(ai.get(i).getMoney() - bet);
		            pot += bet;
	        	}
	        }
	        
	        System.out.println("Pot is: " + pot);
	        System.out.print("Flop: ");
	        
	        for (int i = 2; i < 7; i++) 
	        {
	            System.out.print(Card.rankAsString(player1.availCards[i].getRank()) + " " + Card.suitAsString(player1.availCards[i].getSuit())
	            + ", ");
	        }
	        System.out.println("Your cards: " + Card.rankAsString(player1.availCards[0].getRank()) + " " + Card.suitAsString(player1.availCards[0].getSuit()) + ", " 
	        		+ Card.rankAsString(player1.availCards[1].getRank()) + " " + Card.suitAsString(player1.availCards[1].getSuit())
	        		+ ", Your Money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        int playerStr = 0;
	        
	        for (int i = 0; i < handCon.length; i++)
	        {
	        	if (player1.evaluateHand() == handCon[i])
	        	{
	        		playerStr = i;
	        	}
	        }
	        
	        int aiMax = 0;
	        int aiWinnerInd = 0;
	        AI aiWinner = ai.get(aiWinnerInd);
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	String k = handEvals.get(i).evaluateHand();
	        	for (int j = 0; j < handCon.length; j++)
	        	{
	        		if (k.equals(handCon[j]) && j > aiMax)
	        		{
	        			aiMax = j;
	        			aiWinnerInd = i;
	        		}
	        	}
	        }
	        
	        if (playerStr == aiMax)
	        {
	        	aiWinner.setMoney(aiWinner.getMoney() + (pot/2));
	        	player.setMoney(player.getMoney() + (pot/2));
	        	System.out.println("It was a tie between you and " + aiWinner.getName() + " resulting in a split pot. The hand was a " + handCon[playerStr]);
	        }
	        else
	        if (playerStr > aiMax)
	        {
	        	player.setMoney(player.getMoney() + (pot));
	        	System.out.println("You won the pot with a " + handCon[playerStr] + "!");
	        }
	        else
	        {
	        	aiWinner.setMoney(aiWinner.getMoney() + (pot));
	        	System.out.println(aiWinner.getName() + " won the entire pot with a " + handCon[aiWinnerInd]);
	        }
	        
	        System.out.println("Your money: " + player.getMoney());
	        
	        for (int i = 0; i < ai.size(); i++)
	        {
	        	System.out.println(ai.get(i).getName() + ": " + ai.get(i).getMoney());
	        }
	        
	        if (ai.size() == 0) 
	        {
	        	System.out.println("You won the game!");
	        	System.exit(0);
	        }
		}
	}	
	
	
	
	public static void wait(int s)
	{
	    try
	    {
	        Thread.sleep(s * 1000);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}
}
