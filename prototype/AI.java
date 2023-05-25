package Poker;
public class AI 
{
	/*

	AI Class Idea:


	The AI has a confidence level determined by the value received from HandEval.

	This will be a random number from 0 => 1, where 0 is the AI will absolutely fold, and 1 is the AI bets all in.

	Ranges will be determined, for example, anywhere from 0 - 0.15, the AI folds, and as another example, anywhere between values of 0.95-1 the AI goes all in.

	There will be limiters placed on the AIs confidence level, determined by multiple factors


	Factors Ex...What round of betting / Current Hand Strength / Amount of bets placed


	EX>>> It is the first round of betting, and an AI has a one pair, which is the best possible case for this point in the game

	The AIs confidence level will be limited to an upper value for the first round, say a max of 0.60, and a lower limit of 0.15 to prevent the AI from folding in the first round

	After the second round of betting, say one player goes all in, and now the pot is 1000. This will make the lower limit decrease to simulate a wavering confidence

	Say it goes from 0.15 lower limit to 0.1 from this large pot, however note that the upper limit would NOT change


	EX 2>> Say the second round of betting again, but another AI has only a high card still, which isn't a good position to have.

	The AIs confidence level will be limited to a much lower limit, say a max of 0.40 and the lower limit is 0

	Since the AI has no reason to not fold as its late in the game and there isn't much hope for a good hand at this point

	In this case, say another player bets 1000 again, the lower limit would be lowered into negative numbers, or the upper limit would be lowered instead of the lower limit



	Ranges for AI Choices

	0.00 - 0.++ - AI folds

	0.++ - 0.++ -
	...
	*/
	
	private String name;
	private int money;
	private int bet;
	private double confidence;
	private double conL;
	private double conH;
	private boolean isPlaying = true;
	private boolean check = false;
	private boolean call = false;
	private boolean raise = false;
	private boolean allin = false;
	
	public AI(String name, int money, double confidence)
	{
		this.name = name;
		this.money = money;
		this.confidence = confidence;
	}
	
	public void aiAction(double confidence)
	{
		if (confidence < 0.15) 
		{ 
			isPlaying = false;
		} 
		else
		if (confidence >= 0.15 && confidence < 0.65)
		{ 
			call = true;
		}
		else 
		if (confidence >= 0.65 && confidence < 0.95)
		{ 
			if (confidence < 0.7) { bet = (int)(money * 0.05); }
			if (confidence >= 0.7 && confidence < 0.75) { bet = (int)(money * 0.15); }
			if (confidence >= 0.75 && confidence < 0.8) { bet = (int)(money * 0.25); }
			if (confidence >= 0.8 && confidence < 0.85) { bet = (int)(money * 0.35); }
			if (confidence >= 0.85 && confidence < 0.9) { bet = (int)(money * 0.50); }
			if (confidence >= 0.9 && confidence < 0.95) { bet = (int)(money * 0.60); }
			raise = true; 
		}
		else
		{
			allin = true;
		}
	}
	
	public void reset()
	{
		if (money != 0) { isPlaying = true; }
		check = false;
		call = false;
		raise = false;
		allin = false;
	}
	
	public void setBet(int bet)
	{
		this.bet = bet;
	}
	
	public int getBet()
	{
		return bet;
	}
	
	public boolean getIsPlaying()
	{
		return isPlaying;
	}
	
	public boolean getCheck() 
	{
		return check;
	}
	
	public boolean getCall()
	{
		return call;
	}
	
	public boolean getRaise()
	{
		return raise;
	}
	
	public boolean getAllin()
	{
		return allin;
	}
	
	public double getConfidence()
	{
		return confidence;
	}
	
	public void setConfidence()
	{	
		this.confidence = (Math.random() * (conH - conL) + conL);	
	}
	
	public void setConL(double conL)
	{
		this.conL = conL;
	}
	
	public double getConL()
	{
		return conL;
	}
	
	public void setConH(double conH)
	{
		this.conH = conH;
	}
	
	public double getConH()
	{
		return conH;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public void setMoney(int money)
	{
		this.money = money;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
