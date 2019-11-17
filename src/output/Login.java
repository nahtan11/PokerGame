package output;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    public void checkLogin(String username, String pass){
        try {
            Scanner in = new Scanner(new File("UserData.txt"));
            while (in.hasNextLine())
            {
                String s = in.nextLine();
                String[] sArray = s.split(",");

                System.out.println(sArray[0]); //Just to verify that file is being read
                System.out.println(sArray[1]);


                if (username == sArray[0] && pass == sArray[1])
                {
                    JOptionPane.showMessageDialog(null,
                            "Login Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Username / Password Combo", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            in.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
