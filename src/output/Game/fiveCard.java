package output.Game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import java.util.*;
import javafx.scene.control.ScrollPane;
import javafx.animation.*;  
import javafx.util.Duration; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
 
interface Command 
{ 
    public void execute(); 
}

class Colour {
	public void on()
	{
		System.out.println("Colour is red");
	}
	public void off()
	{
		System.out.println("Colour is black");
	}
}

class ColourOnCommand implements Command {
	Colour colour;
	public ColourOnCommand(Colour colour)
	{
		this.colour = colour;
	}
	public void execute()
	{
		colour.on();
	}
}

class ColourOffCommand implements Command {
	Colour colour;
	public ColourOffCommand(Colour colour)
	{
		this.colour = colour;
	}
	public void execute()
	{
		colour.off();
	}
}

class SimpleRemoteControl 
{ 
    Command slot;
  
    public SimpleRemoteControl() 
    { 
    } 
  
    public void setCommand(Command command) 
    { 
        slot = command; 
    } 
  
    public void buttonWasPressed() 
    { 
        slot.execute(); 
    } 
} 

public class fiveCard {
	Scene scene1;
	static Label prompt;
	static Label potLabel;
	static Label yourMoney;
	static int money;
	static int potMoney;
	static TextField textField;
	static Button betButton;
	static Button callButton;
    static Button checkButton;
	static Button raiseButton;
    static Button foldButton;
	static ArrayList<String> deck;
	static int whosTurn;
	static String player2Has;
	static String[] player2Hand;
	static String player3Has;
	static String[] player3Hand;
	static String player4Has;
	static String[] player4Hand;
	static String lastBet;
	static int player2Money;
	static Label player2MoneyLabel;
	static int player3Money;
	static Label player3MoneyLabel;
	static int player4Money;
	static Label player4MoneyLabel;
	static ScrollPane sp;
	static VBox content;
	static int lastBetFigure;
	static int yourDebt;
	static int player2Debt;
	static int player3Debt;
	static int player4Debt;
	static GridPane pane;
	static String[] yourHand;
	static Label card1;
	static Label card2;
	static Label card3;
	static Label card4;
	static Label card5;
	static Label youHaveLabel;
	static boolean youFold;
	static boolean player2Fold;
	static boolean player3Fold;
	static boolean player4Fold;
	static String[] player2Remove;
	static String[] player3Remove;
	static String[] player4Remove;
	static boolean swappedCards;
	static Label player2;
	static Label player3;
	static Label player4;
	static String youHave;
	static boolean loopedOnce;
	static int howManyCards;
	static boolean betting;
	
	public static void colourController() 
    { 
        SimpleRemoteControl remote = 
                  new SimpleRemoteControl(); 
        Colour colour = new Colour();  
  
        remote.setCommand(new ColourOnCommand(colour)); 
        remote.buttonWasPressed();  
    }

    public void PlayGame(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Five Card Draw");

		howManyCards = 5;
		whosTurn = 1;
		lastBet = "none";
		betting = false;
		variables.setLastBet(lastBet);
		lastBetFigure = 0;
		yourDebt = 0;
		variables.setYourDebt(yourDebt);
		player2Debt = 0;
		player3Debt = 0;
		player4Debt = 0;
		youFold = false;
		player2Fold = false;
		player3Fold = false;
		player4Fold = false;
		swappedCards = false;
		loopedOnce = false;
        betButton = new Button("Bet");
		callButton = new Button("Call");
        checkButton = new Button("Check");
		raiseButton = new Button("Raise");
        foldButton = new Button("Fold");
		variables.setBetButton(betButton);
		variables.setCallButton(callButton);
		variables.setCheckButton(checkButton);
		variables.setRaiseButton(raiseButton);
		variables.setFoldButton(foldButton);
		
        foldButton.setOnAction(e -> Platform.exit());
		betButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (betting == false)
				{
					betting = true;
					textField.setVisible(true);
					prompt = variables.getPrompt();
					prompt.setText(prompt.getText() + "\nHow much would you like to bet?");
					raiseButton.setDisable(true);
					callButton.setDisable(true);
					checkButton.setDisable(true);
					foldButton.setDisable(true);
				}
				else
				{
					betting = false;
					playerDecisions.betEvent("bet");
					int yourBetInt = playerDecisions.getYourBetInteger();
					lastBetFigure += yourBetInt;
					player2Debt += yourBetInt;
					player3Debt += yourBetInt;
					player4Debt += yourBetInt;
					checkForDeal();
				}
			}
		});

		raiseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				if (betting == false)
				{
					betting = true;
					textField.setVisible(true);
					prompt = variables.getPrompt();
					prompt.setText(prompt.getText() + "\nHow much would you like to raise?");
					betButton.setDisable(true);
					callButton.setDisable(true);
					checkButton.setDisable(true);
					foldButton.setDisable(true);
				}
				else
				{
					betting = false;playerDecisions.betEvent("raise");
					int yourBetInt = playerDecisions.getYourBetInteger();
					lastBetFigure += yourBetInt;
					player2Debt += yourBetInt;
					player3Debt += yourBetInt;
					player4Debt += yourBetInt;
					checkForDeal();
				}
			}
		});

		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				playerDecisions.checkEvent();
				checkForDeal();
			}
		});

		callButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				playerDecisions.callEvent();
				checkForDeal();
			}
		});
		
		BorderPane display = new BorderPane();
		BorderPane border1 = new BorderPane();
		display.setBottom(border1);
		
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25));
		border1.setCenter(pane);
		
		betButton.setMinWidth(80);
		callButton.setMinWidth(80);
		checkButton.setMinWidth(80);
		raiseButton.setMinWidth(80);
		foldButton.setMinWidth(80);
		
		callButton.setDisable(true);
		raiseButton.setDisable(true);

		deck = deckThings.makeDeck();
		yourHand = deckThings.hand(deck);
		player2Hand = deckThings.hand(deck);
		player3Hand = deckThings.hand(deck);
		player4Hand = deckThings.hand(deck);
		String[] yourArray = deckThings.checkHand(yourHand, howManyCards);
		youHave = yourArray[yourArray.length-1];
		String[] player2Array = deckThings.checkHand(player2Hand, howManyCards);
		player2Has = player2Array[player2Array.length-1];
		player2Remove = Arrays.copyOf(player2Array, player2Array.length-1);
		String[] player3Array = deckThings.checkHand(player3Hand, howManyCards);
		player3Has = player3Array[player3Array.length-1];
		player3Remove = Arrays.copyOf(player3Array, player3Array.length-1);
		String[] player4Array = deckThings.checkHand(player4Hand, howManyCards);
		player4Has = player4Array[player4Array.length-1];
		player4Remove = Arrays.copyOf(player4Array, player4Array.length-1);
		formatHand(yourHand);
		
		youHaveLabel = new Label(youHave);
		youHaveLabel.setMinWidth(Region.USE_PREF_SIZE);
		
		money = 1000;
		yourMoney = new Label("Money = $" + money);
		yourMoney.setMinWidth(Region.USE_PREF_SIZE);
		variables.setYourMoney(yourMoney);
		variables.setMoney(money);
		
		card1 = new Label(yourHand[0]);
		card1.setMinWidth(Region.USE_PREF_SIZE);
		card2 = new Label(yourHand[1]);
		card2.setMinWidth(Region.USE_PREF_SIZE);
		card3 = new Label(yourHand[2]);
		card3.setMinWidth(Region.USE_PREF_SIZE);
		card4 = new Label(yourHand[3]);
		card4.setMinWidth(Region.USE_PREF_SIZE);
		card5 = new Label(yourHand[4]);
		card5.setMinWidth(Region.USE_PREF_SIZE);
		
		pane.add(youHaveLabel, 3, 0);
		pane.add(yourMoney, 1, 0);
		pane.add(card1, 1, 1);
		pane.add(card2, 1, 2);
		pane.add(card3, 1, 3);
		pane.add(card4, 1, 4);
		pane.add(card5, 1, 5);
		pane.add(betButton, 3, 1);
		pane.add(callButton, 3, 2);
		pane.add(checkButton, 3, 3);
		pane.add(raiseButton, 3, 4);
		pane.add(foldButton, 3, 5);
		
		potMoney = 0;
		potLabel = new Label("Pot:\n$" + potMoney);
		variables.setPotLabel(potLabel);
		variables.setPotMoney(potMoney);
		
		prompt = new Label("What would you like to do?");
		variables.setPrompt(prompt);
		sp = new ScrollPane();
		sp.setPrefSize(70, 70);
		content = new VBox();
		sp.setContent(content);
		sp.setPannable(true);
		content.getChildren().add(prompt);
		
		textField = playerDecisions.createTextField();
		textField.setVisible(false);
		textField.setMaxWidth(100);
		VBox middleScreen = new VBox(potLabel, sp, textField);
		middleScreen.setAlignment(Pos.CENTER);
		middleScreen.setSpacing(15);
		display.setCenter(middleScreen);
		
		player2 = new Label("Player 2:\n     ?\n     ?\n     ?\n     ?\n     ?");		
		player2Money = 1000;
		player2MoneyLabel = new Label("Money = $" + player2Money);
		VBox player2Box = new VBox(player2, player2MoneyLabel); 
		player2Box.setAlignment(Pos.CENTER);
		display.setLeft(player2Box);
		
		player3 = new Label("Player 3:\n?  ?  ?  ?  ?");		
		player3Money = 1000;
		player3MoneyLabel = new Label("Money = $" + player3Money);
		VBox player3Box = new VBox(player3, player3MoneyLabel); 
		player3Box.setAlignment(Pos.CENTER);
		display.setTop(player3Box);
		
		player4 = new Label("Player 4:\n     ?\n     ?\n     ?\n     ?\n     ?");		
		player4Money = 1000;
		player4MoneyLabel = new Label("Money = $" + player4Money);
		VBox player4Box = new VBox(player4, player4MoneyLabel); 
		player4Box.setAlignment(Pos.CENTER);
		display.setRight(player4Box);
		
		
		display.setStyle("-fx-font-weight: bold");
		scene1 = new Scene(display, 500, 500);
		primaryStage.setScene(scene1);
        primaryStage.show();
	}
	
	public static void endGame()
	{	
		player2.setTextFill(Color.web("#000000"));
		player3.setTextFill(Color.web("#000000"));
		player4.setTextFill(Color.web("#000000"));
		
		betButton.setDisable(true);
		checkButton.setDisable(true);
		callButton.setDisable(true);
		raiseButton.setDisable(true);
		foldButton.setDisable(true);
		formatHand(player2Hand);
		formatHand(player3Hand);
		formatHand(player4Hand);
		player2.setText("Player 2: "+player2Has+"\n"+player2Hand[0]+"\n"+player2Hand[1]+"\n"+player2Hand[2]+"\n"+player2Hand[3]+"\n"+player2Hand[4]);		
		player3.setText("Player 3: "+player3Has+"\n"+player3Hand[0]+"\n"+player3Hand[1]+"\n"+player3Hand[2]+"\n"+player3Hand[3]+"\n"+player3Hand[4]);		
		player4.setText("Player 4: "+player4Has+"\n"+player4Hand[0]+"\n"+player4Hand[1]+"\n"+player4Hand[2]+"\n"+player4Hand[3]+"\n"+player4Hand[4]);		
		
		String[] playersHave ={youHave, player2Has, player3Has, player4Has};
		int yourValue = 0, player2Value = 0, player3Value = 0, player4Value = 0, playerValue = 0;
		for (int i=0;i<playersHave.length;i++)
		{
			if (playersHave[i].matches("Royal Flush"))
				playerValue = 10;
			else if (playersHave[i].matches("Straight Flush"))
				playerValue = 9;
			else if (playersHave[i].matches("Four of a Kind"))
				playerValue = 8;
			else if (playersHave[i].matches("Full House"))
				playerValue = 7;
			else if (playersHave[i].matches("Flush"))
				playerValue = 6;
			else if (playersHave[i].matches("Straight"))
				playerValue = 5;
			else if (playersHave[i].matches("Three of a Kind"))
				playerValue = 4;
			else if (playersHave[i].matches("Two Pairs"))
				playerValue = 3;
			else if (playersHave[i].matches("Pair"))
				playerValue = 2;
			else if (playersHave[i].matches("High Card"))
				playerValue = 1;
			
			if (i == 0)
				yourValue = playerValue;
			else if (i == 1)
				player2Value = playerValue;
			else if (i == 2)
				player3Value = playerValue;
			else if (i == 3)
				player4Value = playerValue;
		}
		int[] allValues = {yourValue, player2Value, player3Value, player4Value};
		Arrays.sort(allValues);
		String thisText = "";
		if (allValues[3] == allValues[2])
		{
			thisText = "It's a tie! Players share the pot.";
		}
		else
		{
			if (allValues[3] == yourValue)
			{
				thisText = "You win!";
				money = variables.getMoney();
				potMoney = variables.getPotMoney();
				money += potMoney;
				potLabel.setText("Pot:\n$0");
				yourMoney.setText("Money = $" + money);
			}
			else if (allValues[3] == player2Value)
				thisText = "Player 2 wins!";
			else if (allValues[3] == player3Value)
				thisText = "Player 3 wins!";
			else if (allValues[3] == player4Value)
				thisText = "Player 4 wins!";
		}
		
		prompt.setText(prompt.getText() + "\n" + thisText);
		variables.setPrompt(prompt);
		
	}
	
	public static void scrollToBottom()
	{
		sp.vvalueProperty().bind(content.heightProperty());
	}
	
	public static void pickCardEvent(Button cardButton, int cardNumber, ArrayList<Integer> cardList)
	{
		cardButton.setDisable(true);
		cardList.add(cardNumber);
	}
	
	public static void formatHand(String[] hand)
	{
		for (int i = 0;i < hand.length; i++)
		{
			String[] tempNum = hand[i].split(" ");
			if (tempNum[0].matches("14"))
				hand[i] = hand[i].replaceFirst("14", "Ace");
			else if (tempNum[0].matches("02"))
				hand[i] = hand[i].replaceFirst("02", "Two");
			else if (tempNum[0].matches("03"))
				hand[i] = hand[i].replaceFirst("03", "Three");
			else if (tempNum[0].matches("04"))
				hand[i] = hand[i].replaceFirst("04", "Four");
			else if (tempNum[0].matches("05"))
				hand[i] = hand[i].replaceFirst("05", "Five");
			else if (tempNum[0].matches("06"))
				hand[i] = hand[i].replaceFirst("06", "Six");
			else if (tempNum[0].matches("07"))
				hand[i] = hand[i].replaceFirst("07", "Seven");
			else if (tempNum[0].matches("08"))
				hand[i] = hand[i].replaceFirst("08", "Eight");
			else if (tempNum[0].matches("09"))
				hand[i] = hand[i].replaceFirst("09", "Nine");
			else if (tempNum[0].matches("10"))
				hand[i] = hand[i].replaceFirst("10", "Ten");
			else if (tempNum[0].matches("11"))
				hand[i] = hand[i].replaceFirst("11", "Jack");
			else if (tempNum[0].matches("12"))
				hand[i] = hand[i].replaceFirst("12", "Queen");
			else if (tempNum[0].matches("13"))
				hand[i] = hand[i].replaceFirst("13", "King");
		}
	}
	
	public static void unFormatHand(String[] hand)
	{
		for (int i = 0;i < hand.length; i++)
		{
			String[] tempNum = hand[i].split(" ");
			if (tempNum[0].matches("Ace"))
				hand[i] = hand[i].replaceFirst("Ace", "14");
			else if (tempNum[0].matches("Two"))
				hand[i] = hand[i].replaceFirst("Two", "02");
			else if (tempNum[0].matches("Three"))
				hand[i] = hand[i].replaceFirst("Three", "03");
			else if (tempNum[0].matches("Four"))
				hand[i] = hand[i].replaceFirst("Four", "04");
			else if (tempNum[0].matches("Five"))
				hand[i] = hand[i].replaceFirst("Five", "05");
			else if (tempNum[0].matches("Six"))
				hand[i] = hand[i].replaceFirst("Six", "06");
			else if (tempNum[0].matches("Seven"))
				hand[i] = hand[i].replaceFirst("Seven", "07");
			else if (tempNum[0].matches("Eight"))
				hand[i] = hand[i].replaceFirst("Eight", "08");
			else if (tempNum[0].matches("Nine"))
				hand[i] = hand[i].replaceFirst("Nine", "09");
			else if (tempNum[0].matches("Ten"))
				hand[i] = hand[i].replaceFirst("Ten", "10");
			else if (tempNum[0].matches("Jack"))
				hand[i] = hand[i].replaceFirst("Jack", "11");
			else if (tempNum[0].matches("Queen"))
				hand[i] = hand[i].replaceFirst("Queen", "12");
			else if (tempNum[0].matches("King"))
				hand[i] = hand[i].replaceFirst("King", "13");
		}
	}
	
	public static void cardSwap()
	{
		swappedCards = true;
		if (player2Fold == false)
		{			
			for (int i=0;i<player2Remove.length;i++)
			{
				for (int j=0;j<5;j++)
				{
					if (player2Remove[i].matches(player2Hand[j]))
					{
						player2Hand[j] = deck.get(0);
						deck.remove(0);
					}
				}
			}
			String[] player2Array = deckThings.checkHand(player2Hand, howManyCards);
			player2Has = player2Array[player2Array.length-1];
		}
		if (player3Fold == false)
		{
			for (int i=0;i<player3Remove.length;i++)
			{
				for (int j=0;j<5;j++)
				{
					if (player3Remove[i].matches(player3Hand[j]))
					{
						player3Hand[j] = deck.get(0);
						deck.remove(0);
					}
				}
			}
			String[] player3Array = deckThings.checkHand(player3Hand, howManyCards);
			player3Has = player3Array[player3Array.length-1];
		}
		if (player4Fold == false)
		{
			for (int i=0;i<player4Remove.length;i++)
			{
				for (int j=0;j<5;j++)
				{
					if (player4Remove[i].matches(player4Hand[j]))
					{
						player4Hand[j] = deck.get(0);
						deck.remove(0);
					}
				}
			}
			String[] player4Array = deckThings.checkHand(player4Hand, howManyCards);
			player4Has = player4Array[player4Array.length-1];
		}
		
		int time = 9;
		if (player2Fold)
			time = time - 3;
		
		if (player3Fold)
			time = time - 3;
		
		Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
			if (player2Fold == false)
			{
				prompt.setText(prompt.getText() + "\nPlayer 2 swapped " + player2Remove.length + " card(s).");
				variables.setPrompt(prompt);
			}
		}));
		timeline1.play();
		
		Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(time-3), ev -> {
			if (player3Fold == false)
			{	
				prompt.setText(prompt.getText() + "\nPlayer 3 swapped " + player3Remove.length + " card(s).");
				variables.setPrompt(prompt);
			}
		}));
		timeline2.play();
		
		Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(time), ev -> {
			if (player4Fold == false)
			{
				prompt.setText(prompt.getText() + "\nPlayer 4 swapped " + player4Remove.length + " card(s).");
				variables.setPrompt(prompt);
			}
			
			whosTurn = 1;
			checkButton.setDisable(false);
			betButton.setDisable(false);
			foldButton.setDisable(false);
			callButton.setDisable(true);
			raiseButton.setDisable(true);
			loopedOnce = false;
			lastBet = "none";
			variables.setLastBet(lastBet);
			lastBetFigure = 0;
			prompt.setText(prompt.getText() + "\nWhat would you like to do?");
			variables.setPrompt(prompt);
		}));
		timeline3.play();
	}
	
	public static void finishedCardPick(ArrayList<Integer> cardList, Button button1, Button button2, Button button3, Button button4, Button button5, Button button6)
	{
		pane.getChildren().remove(button1);
		pane.getChildren().remove(button2);
		pane.getChildren().remove(button3);
		pane.getChildren().remove(button4);
		pane.getChildren().remove(button5);
		pane.getChildren().remove(button6);
		prompt.setText(prompt.getText() + "\nYou swapped " + cardList.size() + " card(s).");
		variables.setPrompt(prompt);
		for (int i = 0; i < cardList.size(); i++)
		{
			if (cardList.get(i) == 1)
			{
				yourHand[0] = deck.get(0);
			}
			else if (cardList.get(i) == 2)
			{
				yourHand[1] = deck.get(0);
			}
			else if (cardList.get(i) == 3)
			{
				yourHand[2] = deck.get(0);
			}
			else if (cardList.get(i) == 4)
			{
				yourHand[3] = deck.get(0);
			}
			else if (cardList.get(i) == 5)
			{
				yourHand[4] = deck.get(0);
			}
			deck.remove(0);
		}
		unFormatHand(yourHand);
		String[] yourArray = deckThings.checkHand(yourHand, howManyCards);
		youHave = yourArray[yourArray.length-1];
		youHaveLabel.setText(youHave);
		formatHand(yourHand);
		card1.setText(yourHand[0]);
		card2.setText(yourHand[1]);
		card3.setText(yourHand[2]);
		card4.setText(yourHand[3]);
		card5.setText(yourHand[4]);
		cardSwap();
	}
	
	public static void deal()
	{
		player2.setTextFill(Color.web("#000000"));
		player3.setTextFill(Color.web("#000000"));
		player4.setTextFill(Color.web("#000000"));
				
		raiseButton.setDisable(true);
		betButton.setDisable(true);
		callButton.setDisable(true);
		checkButton.setDisable(true);
		foldButton.setDisable(true);
		
		Button doneButton = new Button("Done");
		Button pickCard1 = new Button();
		Button pickCard2 = new Button();
		Button pickCard3 = new Button();
		Button pickCard4 = new Button();
		Button pickCard5 = new Button();
		
		ArrayList<Integer> arrlist = new ArrayList<Integer>(5);
		
		pickCard1.setOnAction(e -> pickCardEvent(pickCard1, 1, arrlist));
		pickCard2.setOnAction(e -> pickCardEvent(pickCard2, 2, arrlist));
		pickCard3.setOnAction(e -> pickCardEvent(pickCard3, 3, arrlist));
		pickCard4.setOnAction(e -> pickCardEvent(pickCard4, 4, arrlist));
		pickCard5.setOnAction(e -> pickCardEvent(pickCard5, 5, arrlist));
		doneButton.setOnAction(e -> finishedCardPick(arrlist, doneButton, pickCard1, pickCard2, pickCard3, pickCard4, pickCard5));
		
		pane.add(doneButton, 0, 0);
		pane.add(pickCard1, 0, 1);
		pane.add(pickCard2, 0, 2);
		pane.add(pickCard3, 0, 3);
		pane.add(pickCard4, 0, 4);
		pane.add(pickCard5, 0, 5);
		
		prompt.setText(prompt.getText() + "\nDealer will now allow you to swap cards.");
		prompt.setText(prompt.getText() + "\nPick which cards you'd like to replace.");
		variables.setPrompt(prompt);
	}
	
	public static void checkForDeal()
	{
		yourDebt = variables.getYourDebt();
		if (loopedOnce == true && yourDebt == 0 && (player2Debt == 0 || player2Fold == true) && (player3Debt == 0 || player3Fold == true) && (player4Debt == 0 || player4Fold == true))
			if (player2Fold && player3Fold && player4Fold)
			{
				raiseButton.setDisable(true);
				betButton.setDisable(true);
				callButton.setDisable(true);
				checkButton.setDisable(true);
				foldButton.setDisable(true);
				prompt.setText(prompt.getText() + "\nYou Win!");
				variables.setPrompt(prompt);
				money = variables.getMoney();
				potMoney = variables.getPotMoney();
				money += potMoney;
				potLabel.setText("Pot:\n$0");
				yourMoney.setText("Money = $" + money);
			}
			else
			{
				if (swappedCards)
					endGame();
				else
					deal();
			}
		else
			if (whosTurn == 1)
			{
				betButton.setDisable(true);
				checkButton.setDisable(true);
				callButton.setDisable(true);
				raiseButton.setDisable(true);
				foldButton.setDisable(true);
				whosTurn = 2;
				if (player3Fold && player4Fold)
				{
					loopedOnce = true;
				}
				opponentGoes();
			}
			else if (whosTurn == 2)
			{
				whosTurn = 3;
				if (player4Fold)
					loopedOnce = true;
				opponentGoes();
			}
			else if (whosTurn == 3)
			{
				whosTurn = 4;
				loopedOnce = true;
				opponentGoes();
			}
			else if (whosTurn == 4)
			{
				whosTurn = 1;
				yourDebt = variables.getYourDebt();
				prompt.setText(prompt.getText() + "\nIt's $" + yourDebt + " to you.");
				variables.setPrompt(prompt);
				if (player3Fold)
					loopedOnce = true;
				
				player2.setTextFill(Color.web("#000000"));
				player3.setTextFill(Color.web("#000000"));
				player4.setTextFill(Color.web("#000000"));
				money = variables.getMoney();
				if (money >= yourDebt)
				{
					callButton.setDisable(false);
					raiseButton.setDisable(false);
				}
				foldButton.setDisable(false);
			}
	}
	
	public static void opponentBets()
	{
		String playerHas = "";
		int playerMoney = 0;
		Label playerLabel = new Label();
		int playerDebt = 0;
		boolean playerFolds = false;
		if (whosTurn == 2)
		{
			playerHas = player2Has;
			playerMoney = player2Money;
			playerLabel = player2MoneyLabel;
			playerDebt = player2Debt;
			playerFolds = player2Fold;
		}
		else if (whosTurn == 3)
		{
			playerHas = player3Has;
			playerMoney = player3Money;
			playerLabel = player3MoneyLabel;
			playerDebt = player3Debt;
			playerFolds = player3Fold;
		}
		else if (whosTurn == 4)
		{
			playerHas = player4Has;
			playerMoney = player4Money;
			playerLabel = player4MoneyLabel;
			playerDebt = player4Debt;
			playerFolds = player4Fold;
		}
		
		String whatBet = "low";
		int random = (int)(Math.random() * 50 + 1);
		if (playerHas.matches("Royal Flush"))
			if (random > 34 && random < 46)
				whatBet = "medium";
			else if (random > 0 && random < 35)
				whatBet = "high";
		else if (playerHas.matches("Straight Flush"))
			if (random > 29 && random < 46)
				whatBet = "medium";
			else if (random > 0 && random < 30)
				whatBet = "high";
		else if (playerHas.matches("Four of a Kind"))
			if (random > 24 && random < 46)
				whatBet = "medium";
			else if (random > 0 && random < 25)
				whatBet = "high";
		else if (playerHas.matches("Full House"))
			if (random > 34 && random < 41)
				whatBet = "high";
			else if (random > 0 && random < 35)
				whatBet = "medium";
		else if (playerHas.matches("Flush"))
			if (random > 36 && random < 41)
				whatBet = "high";
			else if (random > 0 && random < 37)
				whatBet = "medium";
		else if (playerHas.matches("Straight"))
			if (random > 36 && random < 41)
				whatBet = "high";
			else if (random > 0 && random < 37)
				whatBet = "medium";
		else if (playerHas.matches("Three of a Kind"))
			if (random > 38 && random < 41)
				whatBet = "high";
			else if (random > 0 && random < 39)
				whatBet = "medium";
		else if (playerHas.matches("Two Pairs"))
			if (random > 39 && random < 46)
				whatBet = "high";
			else if (random > 45 && random < 51)
				whatBet = "medium";
		else if (playerHas.matches("Pair"))
			if (random > 39 && random < 43)
				whatBet = "high";
			else if (random > 42 && random < 51)
				whatBet = "medium";
		else
			if (random > 39 && random < 41)
				whatBet = "high";
			else if (random > 40 && random < 51)
				whatBet = "medium";
			
		boolean shouldFold = false;
		boolean shouldCheck = false;
		boolean shouldCall = false;
		boolean shouldBet = false;
		String decision = "\nPlayer raises $";
		lastBet = variables.getLastBet();
		if (lastBetFigure == 0)
			decision = "\nPlayer bets $";
		random = (int)(Math.random() * 10 + 1);
		if (lastBet.matches("none") && random < 8)
			shouldCheck = true;
		else if ((whatBet.matches("low") && lastBet.matches("high")) || (whatBet.matches("low") && lastBet.matches("medium")) || (whatBet.matches("medium") && lastBet.matches("high")))
			shouldFold = true;
		else if (random < 8 && (whatBet.matches("low") && lastBet.matches("low")) || (whatBet.matches("medium") && lastBet.matches("medium")) || (whatBet.matches("high") && lastBet.matches("high")))
			shouldCall = true;
		
		if (shouldCall == true && whatBet.matches("high") && random < 4)
			shouldCall = false;
		
		if (shouldCheck == true && whatBet.matches("high") && random < 4)
			shouldCheck = false;
		
		if (lastBet.matches("very high"))
		{
			prompt.setText(prompt.getText() + "\nPlayer folds.");
			playerFolds = true;
		}
		else if (shouldCheck == true)
		{
			prompt.setText(prompt.getText() + "\nPlayer checks.");
		}
		else if (shouldCall == true && playerMoney >= playerDebt)
		{
			prompt.setText(prompt.getText() + "\nPlayer calls.");
			playerMoney = playerMoney - playerDebt;
			playerDebt = 0;
			playerLabel.setText("Money = $" + playerMoney);
			potMoney = variables.getPotMoney();
			potMoney = potMoney + lastBetFigure;
			variables.setPotMoney(potMoney);
			potLabel.setText("Pot:\n$" + potMoney);
		}
		else if (shouldFold == true && random < 6)
		{
			prompt.setText(prompt.getText() + "\nPlayer folds.");
			playerFolds = true;
		}
		else if (shouldFold == false && whatBet == "low" && random < 3)
		{
			prompt.setText(prompt.getText() + "\nPlayer folds.");
			playerFolds = true;
		}
		else
		{
			if (whatBet.matches("high"))
			{
				shouldBet = true;
				random = (int)(Math.random() * 100 + 1);
				random += 100;
				random = (random + 4) / 5 * 5;
			}
			else if (whatBet.matches("medium"))
			{
				shouldBet = true;
				random = (int)(Math.random() * 50 + 1);
				random += 50;
				random = (random + 4) / 5 * 5;
			}
			else
			{	
				shouldBet = true;
				random = (int)(Math.random() * 50 + 1);
				random = (random + 4) / 5 * 5;
			}	
		}
		
		if (shouldBet)
		{
			if (playerMoney >= random)
			{
				prompt.setText(prompt.getText() + decision + random + ".");
				playerMoney = playerMoney - random - lastBetFigure;
				potMoney = variables.getPotMoney();
				potMoney = potMoney + lastBetFigure + random;
				variables.setPotMoney(potMoney);
				lastBetFigure = lastBetFigure + random;
				potLabel.setText("Pot:\n$" + potMoney);
				playerLabel.setText("Money = $" + playerMoney);
				playerDebt = 0;
				yourDebt = variables.getYourDebt();
				yourDebt += random;
				variables.setYourDebt(yourDebt);
				if (whosTurn == 2)
				{
					player3Debt += random;
					player4Debt += random;
				}
				else if (whosTurn == 3)
				{
					player2Debt += random;
					player4Debt += random;
				}
				else if (whosTurn == 4)
				{
					player2Debt += random;
					player3Debt += random;
				}
				if (lastBetFigure > 0 && lastBetFigure < 50)
					lastBet = "low";
				else if (lastBetFigure > 49 && lastBetFigure < 100)
					lastBet = "medium";
				else if (lastBetFigure > 99 && lastBetFigure < 200)
					lastBet = "high";
				
				variables.setLastBet(lastBet);
			}
			else
			{
				prompt.setText(prompt.getText() + "\nPlayer folds.");
				playerFolds = true;
			}
		}
		
		if (whosTurn == 2)
		{
			player2Money = playerMoney;
			player2Debt = playerDebt;
			player2Fold = playerFolds;
		}
		else if (whosTurn == 3)
		{
			player3Money = playerMoney;
			player3Debt = playerDebt;
			player3Fold = playerFolds;
		}
		else if (whosTurn == 4)
		{
			player4Money = playerMoney;
			player4Debt = playerDebt;
			player4Fold = playerFolds;
		}
		variables.setPrompt(prompt);
		scrollToBottom();
		checkForDeal();
	}
	
	public static void opponentGoes()
	{	
		if (whosTurn == 2 && player2Fold == true)
			whosTurn = 3;
		
		if (whosTurn == 3 && player3Fold == true)
			whosTurn = 4;
		
		if (whosTurn == 4 && player4Fold == true)
			whosTurn = 1;
		
		if(whosTurn == 2)
		{
			player2.setTextFill(Color.web("#ff0000"));
			player3.setTextFill(Color.web("#000000"));
			player4.setTextFill(Color.web("#000000"));
			colourController();
		}
		else if(whosTurn == 3)
		{
			player3.setTextFill(Color.web("#ff0000"));
			player2.setTextFill(Color.web("#000000"));
			player4.setTextFill(Color.web("#000000"));
			colourController();
		}
		else if(whosTurn == 4)
		{
			player4.setTextFill(Color.web("#ff0000"));
			player3.setTextFill(Color.web("#000000"));
			player2.setTextFill(Color.web("#000000"));
			colourController();
		}
		
		if (whosTurn == 1)
		{
			if (loopedOnce == true)
				checkForDeal();
			else
			{
				player2.setTextFill(Color.web("#000000"));
				player3.setTextFill(Color.web("#000000"));
				player4.setTextFill(Color.web("#000000"));
				yourDebt = variables.getYourDebt();
				money = variables.getMoney();
				if (money >= yourDebt)
				{
					callButton.setDisable(false);
					raiseButton.setDisable(false);
				}
				foldButton.setDisable(false);
			}
		}
		
		if (whosTurn == 2)
		{
			prompt.setText(prompt.getText() + "\nPlayer 2's turn.");
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
				opponentBets();
			}));
			timeline.play();
		}
		else if (whosTurn == 3)
		{
			prompt.setText(prompt.getText() + "\nPlayer 3's turn.");
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
				opponentBets();
			}));
			timeline.play();
		}
		else if (whosTurn == 4)
		{
			prompt.setText(prompt.getText() + "\nPlayer 4's turn.");
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
				opponentBets();
			}));
			timeline.play();
		}
		variables.setPrompt(prompt);
	}
	
	public static Label getPrompt()
	{
		return prompt;
	}

}