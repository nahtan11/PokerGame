package output.Registry;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Login implements ICheckUser {
    public Boolean checkUser(String username, byte [] pass){
        boolean userFound = false;
        try {
            BufferedReader file
                    = new BufferedReader(new FileReader("..\\PokerGame\\src\\output\\Database\\UserData.txt"));

            Scanner in = new Scanner(file);

            while (in.hasNextLine()&&!userFound)
            {
                String s = in.nextLine();
                String[] sArray = s.split(",",2);
                System.out.println(sArray[0]+" "+sArray[1]);

                if (username.equals(sArray[0]) && Arrays.toString(pass).equals(sArray[1]))
                {
                    userFound=true;
                    JOptionPane.showMessageDialog(null,
                            "Login Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }
            if (!userFound) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Username / Password", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            in.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database file Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return userFound;
    }
}
