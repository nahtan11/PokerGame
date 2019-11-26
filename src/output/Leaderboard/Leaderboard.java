package output.Leaderboard;

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

public class Leaderboard {
    public List<String> getLeaderboard(String username){
        List<String> lbList = new ArrayList<>();
        try {
            BufferedReader file
                    = new BufferedReader(new FileReader("output\\Database\\Leaderboard.txt"));

            Scanner in = new Scanner(file);

            while (in.hasNextLine())
            {
                String s = in.nextLine();
                String[] sArray = s.split(",");



                /*if (username.equals(sArray[0]))
                {
                    lbList.add(s);
                    System.out.println(sArray[1]+" "+sArray[2]);
                }*/

            }

            in.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database file Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return lbList;
    }
}
