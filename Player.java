package Poker;

public class Player 
{
	//attributes: name, initial amount
	private String name;
	private int money;
	private int bet;
	private boolean isPlaying = true;
	
	public Player(String name, int money) 
	{
		this.name = name;
		this.money = money;
	}
	
	public void isPlaying () 
	{
		if (money <= 0)
		{
			isPlaying = false;
		}
	}
	
	public boolean getIsPlaying()
	{
		return isPlaying;
	}
	
	public void bet()
	{
		money -= bet;
	}
	
	public String getName() 
	{
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getMoney() 
    {
        return money;
    }

    public void setMoney(int money) 
    {
        this.money = money;
    }

    public int getBet() 
    {
        return bet;
    }

    public void setBet(int bet) 
    {
        this.bet = bet;
    }
}
