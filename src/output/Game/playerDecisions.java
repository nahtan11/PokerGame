package output.Game;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class playerDecisions 
{
	static TextField textField;
	static Label prompt;
	static int yourDebt;
	static int yourBetInteger;
	static Button betButton = variables.getBetButton();
	static Button callButton = variables.getCallButton();
	static Button checkButton = variables.getCheckButton();
	static Button raiseButton = variables.getRaiseButton();
	static Button foldButton = variables.getFoldButton();
	static Label yourMoney;
	static Label potLabel;
	static int potMoney;
	static int money;
	static String lastBet;
	
	public static TextField createTextField()
	{
		textField = new TextField();
		return textField;
	}
	
	public static void betEvent(String whichOne)
	{
		String yourBet = textField.getText();
		int yourBetInt = Integer.parseInt(yourBet);
		String yourDecision = "\nYou bet $";
		if (whichOne.matches("raise"))
		{
			yourDecision = "\nYou raise $";
			yourDebt = variables.getYourDebt();
			int yourRaise = yourBetInt + yourDebt;
			yourDebt = 0;
			variables.setYourDebt(yourDebt);
			potMoney = variables.getPotMoney();
			potMoney = potMoney + yourRaise;
			variables.setPotMoney(potMoney);
			money = variables.getMoney();
			money = money - yourRaise;
			variables.setMoney(money);
			if (yourRaise > 0 && yourRaise < 50)
				lastBet = "low";
			else if (yourRaise > 49 && yourRaise < 100)
				lastBet = "medium";
			else if (yourRaise > 99 && yourRaise < 200)
				lastBet = "high";
			else
				lastBet = "very high";
			
			variables.setLastBet(lastBet);
		}
		else
		{
			potMoney = variables.getPotMoney();
			potMoney = potMoney + yourBetInt;
			variables.setPotMoney(potMoney);
			money = variables.getMoney();
			money = money - yourBetInt;
			variables.setMoney(money);
			if (yourBetInt > -1 && yourBetInt < 50)
				lastBet = "low";
			else if (yourBetInt > 49 && yourBetInt < 100)
				lastBet = "medium";
			else if (yourBetInt > 99 && yourBetInt < 200)
				lastBet = "high";
			else
				lastBet = "very high";
			
			variables.setLastBet(lastBet);
		}
		textField.setVisible(false);
		potLabel = variables.getPotLabel();
		yourMoney = variables.getYourMoney();
		potLabel.setText("Pot:\n$" + potMoney);
		yourMoney.setText("Money = $" + money);
		prompt = variables.getPrompt();
		prompt.setText(prompt.getText() + yourDecision + yourBetInt);
		setYourBetInteger(yourBetInt);
	}
	
	public static void setYourBetInteger(int betInt)
	{
		yourBetInteger = betInt;
	}
	
	public static int getYourBetInteger()
	{
		return yourBetInteger;
	}
	
	public static void checkEvent()
	{
		prompt = variables.getPrompt();
		prompt.setText(prompt.getText() + "\nYou check.");
	}
	
	public static void callEvent()
	{
		prompt = variables.getPrompt();
		prompt.setText(prompt.getText() + "\nYou call.");
		yourDebt = variables.getYourDebt();
		money = variables.getMoney();
		money = money - yourDebt;
		variables.setMoney(money);
		potMoney = variables.getPotMoney();
		potMoney = potMoney + yourDebt;
		variables.setPotMoney(potMoney);
		potLabel = variables.getPotLabel();
		yourMoney = variables.getYourMoney();
		potLabel.setText("Pot:\n$" + potMoney);
		yourMoney.setText("Money = $" + money);
		yourDebt = 0;
		variables.setYourDebt(yourDebt);
	}
}