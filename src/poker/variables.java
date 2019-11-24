package poker;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class variables 
{
	static Label prompt;
	static Button betButton;
	static Button raiseButton;
	static Button checkButton;
	static Button callButton;
	static Button foldButton;
	static int yourDebt;
	static Label yourMoney;
	static Label potLabel;
	static int money;
	static int potMoney;
	static String lastBet;

	public static void setPrompt(Label p)
	{
		prompt = p;
	}
	
	public static Label getPrompt()
	{
		return prompt;
	}
	
	public static void setBetButton(Button b)
	{
		betButton = b;
	}
	
	public static Button getBetButton()
	{
		return betButton;
	}
	
	public static void setRaiseButton(Button r)
	{
		raiseButton = r;
	}
	
	public static Button getRaiseButton()
	{
		return raiseButton;
	}
	
	public static void setCheckButton(Button ch)
	{
		checkButton = ch;
	}
	
	public static Button getCheckButton()
	{
		return checkButton;
	}
	
	public static void setCallButton(Button ca)
	{
		callButton = ca;
	}
	
	public static Button getCallButton()
	{
		return callButton;
	}
	
	public static void setFoldButton(Button f)
	{
		foldButton = f;
	}
	
	public static Button getFoldButton()
	{
		return foldButton;
	}
	
	public static void setYourDebt(int d)
	{
		yourDebt = d;
	}
	
	public static int getYourDebt()
	{
		return yourDebt;
	}
	
	public static void setYourMoney(Label money)
	{
		yourMoney = money;
	}
	
	public static Label getYourMoney()
	{
		return yourMoney;
	}
	
	public static void setPotLabel(Label pot)
	{
		potLabel = pot;
	}
	
	public static Label getPotLabel()
	{
		return potLabel;
	}
	
	public static void setMoney(int mon)
	{
		money = mon;
	}
	
	public static int getMoney()
	{
		return money;
	}
	
	public static void setPotMoney(int potMon)
	{
		potMoney = potMon;
	}
	
	public static int getPotMoney()
	{
		return potMoney;
	}
	
	public static void setLastBet(String lb)
	{
		lastBet = lb;
	}
	
	public static String getLastBet()
	{
		return lastBet;
	}
}