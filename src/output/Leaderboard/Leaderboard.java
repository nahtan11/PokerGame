ackage output.Leaderboard;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Leaderboard implements Cloneable{
	private List<String> lbList;
	
	public Leaderboard(){
		lbList = new ArrayList<String>();
	}
	
	public Leaderboard(List<String> list){
		this.lbList = list;
	}
	
    public void setLeaderboard(String username){
        try {
            BufferedReader file
                    = new BufferedReader(new FileReader("..\\PokerGame\\src\\output\\Database\\Leaderboard.txt"));

            Scanner in = new Scanner(file);

            while (in.hasNextLine())
            {
                String s = in.nextLine();
                String[] sArray = s.split(",");



                if (username.equals(sArray[0]))
                {
                    lbList.add(s);
                    System.out.println(sArray[1]+" "+sArray[2]);
                }

            }

            in.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database file Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public List<String> getLeaderboard(){
        return lbList;
    }
	
	@Override
	public Object clone() throws CloneNotSupportedException{
			List<String> temp = new ArrayList<String>();
			for(String s : this.getLeaderboard()){
				temp.add(s);
			}
			return new Leaderboard(temp);
	}
}
