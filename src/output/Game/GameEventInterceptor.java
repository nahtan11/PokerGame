package output.Game;

import java.io.*;
import java.util.Scanner;

//Interceptor- Concrete Interceptor
public class GameEventInterceptor implements  IEventInterceptor {
    @Override
    public void preEvent(EventContextObj context) {
        BufferedReader file
                = null;
        try {
            String text = context.getUser()+" started a game of "+context.getGameName()+ " on "+context.getCurrentDateTime();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("..\\PokerGame\\src\\output\\Database\\Logs.txt", true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write(text);
            writer.flush();
            writer.close();

            file = new BufferedReader(new FileReader("..\\PokerGame\\src\\output\\Database\\Logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(file);
        //log date/time that specific user started a game
        System.out.println(context.getUser()+" started a game of "+context.getGameName()+ " on "+context.getCurrentDateTime());
    }

    @Override
    public void postEvent(EventContextObj context) {
        BufferedReader file
                = null;
        try {
            String text = context.getUser()+" ended a game of "+context.getGameName()+ " on "+context.getCurrentDateTime();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("..\\PokerGame\\src\\output\\Database\\Logs.txt", true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write(text);
            writer.flush();
            writer.close();

            file = new BufferedReader(new FileReader("..\\PokerGame\\src\\output\\Database\\Logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(file);
        //log date/time that specific user ended a game
        System.out.println(context.getUser()+" ended a game of "+context.getGameName()+ " on "+context.getCurrentDateTime());
    }
}
