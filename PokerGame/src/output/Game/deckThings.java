package output.Game;

import java.util.*;

public class deckThings 
{
	public static ArrayList<String> makeDeck()
	{
		String[] SUITS = 
		{
            "Clubs", "Diamonds", "Hearts", "Spades"
        };
        String[] RANKS = 
		{
            "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14"
        };
        int n = SUITS.length * RANKS.length;
		ArrayList<String> deck = new ArrayList<>();
        for (int i = 0; i < RANKS.length; i++) 
		{
            for (int j = 0; j < SUITS.length; j++) 
			{
                deck.add(RANKS[i] + " of " + SUITS[j]);
            }
        }
		Collections.shuffle(deck);
		
		return deck;
	}
	
	public static String[] hand(ArrayList<String> deck)
	{
		String[] yourHand = new String[5];
		for (int i = 0;i < 5; i++)
		{
			yourHand[i] = deck.get(0);
			deck.remove(0);
		}
		
		return yourHand;
	}
	
	public static String[] checkHand(String[] hand, int numInHand)
	{
		int clubs=0, hearts=0, spades=0, diamonds=0;
		ArrayList<String> numbers = new ArrayList<String>();
		int[] intArray = new int[numInHand];
		int lastNum = -1;
		ArrayList<String> cardsToKeep = new ArrayList<String>();
		for (int i = 0; i < numInHand; i++) 
		{
			intArray[i] = Integer.parseInt(hand[i].substring(0, 2));
			int howMany = 0;
			if (hand[i].matches(".*\\bClubs\\b.*"))
				clubs++;
			else if (hand[i].matches(".*\\bHearts\\b.*"))
				hearts++;
			else if (hand[i].matches(".*\\bSpades\\b.*"))
				spades++;
			else if (hand[i].matches(".*\\bDiamonds\\b.*"))
				diamonds++;
			
			for (int j = 0; j < numInHand; j++)
			{
				int check1 = Integer.parseInt(hand[i].substring(0, 2));
				int check2 = Integer.parseInt(hand[j].substring(0, 2));
				if (check1 == check2 && i != j && i != lastNum)
				{
					howMany++;
					lastNum = j;
					cardsToKeep.add(hand[i]);
					cardsToKeep.add(hand[j]);
				}
			}
			if (howMany > 0)
				numbers.add(Integer.toString(howMany));
		}
		boolean pair = false, twoPair = false, three = false, four = false, fullHouse = false, flush = false, straight = false, royalFlush = false;
		int hereAgain = 0;
		for (int i = 0; i < numbers.size(); i++)
		{
			if (numbers.get(i).matches("1"))
			{
				pair = true;
				hereAgain++;
			}
			else if (numbers.get(i).matches("2"))
				three = true;	
			else if (numbers.get(i).matches("3"))
				four = true;
		}
		
		ArrayList<String> cardsToRemove = new ArrayList<String>();
		for (int i = 0;i < numInHand;i++)
		{
			boolean temp = false;
			for (int j = 0;j < cardsToKeep.size();j++)
			{
				if (hand[i].matches(cardsToKeep.get(j)))
					temp = true;
			}
			if (temp == false)
				cardsToRemove.add(hand[i]);
		}
		
		if (hereAgain > 1)
			twoPair = true;
		
		if (diamonds == 5 || hearts == 5 || clubs == 5 || spades == 5)
			flush = true;
		
		ArrayList<String> tempCardsToRemove = new ArrayList<String>();
		if (three == false && four == false)
		{
			if (diamonds == 4 || diamonds == 3)
			{
				for (int i = 0;i < numInHand;i++)
				{
					if (!(hand[i].matches(".*\\bDiamonds\\b.*")))
					{
						tempCardsToRemove.add(hand[i]);
					}
				}
			}
			else if (hearts == 4 || hearts == 3)
			{
				for (int i = 0;i < numInHand;i++)
				{
					if (hand[i].matches(".*\\bHearts\\b.*"))
					{
						tempCardsToRemove.add(hand[i]);
					}
				}
			}
			else if (clubs == 4 || clubs == 3)	
			{
				for (int i = 0;i < numInHand;i++)
				{
					if (!(hand[i].matches(".*\\bClubs\\b.*")))
					{
						tempCardsToRemove.add(hand[i]);
					}
				}
			}
			else if (spades == 4 || spades == 3)
			{
				for (int i = 0;i < numInHand;i++)
				{
					if (!(hand[i].matches(".*\\bSpades\\b.*")))
					{
						tempCardsToRemove.add(hand[i]);
					}
				}
			}
		}
		
		if (tempCardsToRemove.size() > 0)
		{
			cardsToRemove.clear();
			for (int i = 0;i < tempCardsToRemove.size();i++)
			{
				cardsToRemove.add(tempCardsToRemove.get(i));
			}
		}
		
		Arrays.sort(intArray);
		int straightNum = 0;
		ArrayList<String> tempCardsToRemove2 = new ArrayList<String>();
		for (int i = 1; i < intArray.length; i++)
		{ 
			if (intArray[i]-1 == intArray[i-1])
			{
				straightNum++;
			}
			else
			{
				if (intArray[i]-2 != intArray[i-1] && i == intArray.length-1)
				{
					if (intArray[i] > 9)
						tempCardsToRemove2.add("" + intArray[i]);
					else
						tempCardsToRemove2.add("0" + intArray[i]);
				}
				else if (intArray[i]-2 != intArray[i-1])
				{
					if (intArray[i-1] > 9)
						tempCardsToRemove2.add("" + intArray[i-1]);
					else
						tempCardsToRemove2.add("0" + intArray[i-1]);
				}
			}
		}
		
		if (intArray[0] == 10)
			royalFlush = true;
		
		if (straightNum == 4)
			straight = true;
		
		if (straightNum == 2 || straightNum == 3)
		{
			if (three == false && four == false && flush == false && royalFlush == false && tempCardsToRemove.size() == 0)
			{
				if (tempCardsToRemove2.size() > 0)
				{
					cardsToRemove.clear();
					for (int i = 0;i < tempCardsToRemove2.size();i++)
					{
						for (int j = 0;j < numInHand;j++)
						{
							if (tempCardsToRemove2.get(i).matches(hand[j].substring(0, 2)))
							{
								cardsToRemove.add(hand[j]);
							}
						}
					}
				}
			}
		}
		
		String youHave = "";
		if (royalFlush == true && flush == true)
		{
			youHave = "Royal Flush";
			cardsToRemove.clear();
		}
		else if (flush == true && straight == true)
		{
			youHave = "Straight Flush";
			cardsToRemove.clear();
		}
		else if (four == true)
		{
			youHave = "Four of a Kind";
		}
		else if (pair == true && three == true)
		{
			youHave = "Full House";
			cardsToRemove.clear();
		}
		else if (flush == true)
		{
			youHave = "Flush";
			cardsToRemove.clear();
		}
		else if (straight == true)
		{
			youHave = "Straight";
			cardsToRemove.clear();
		}
		else if (three == true)
		{
			youHave = "Three of a Kind";
		}
		else if (twoPair == true)
		{
			youHave = "Two Pairs";
		}
		else if (pair == true)
		{
			youHave = "Pair";
		}
		else
		{
			youHave = "High Card";
		}
		
		int index = cardsToRemove.size();
		String[] ar = new String[index+1];
		for (int i = 0;i < index;i++)
		{
			ar[i] = cardsToRemove.get(i);
		}
		ar[index] = youHave;
		return ar;
	}
}