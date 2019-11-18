package output.Registry;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Login implements ICheckUser {
    public Boolean checkUser(String username, String pass){
        boolean userFound = false;
        try {
            URL url = getClass().getResource("UserData.txt");
            Scanner in = new Scanner(new File(url.getPath()));

            while (in.hasNextLine()&&!userFound)
            {
                String s = in.nextLine();
                String[] sArray = s.split(",");
                System.out.println(sArray[0]+" "+sArray[1]);

                if (username.equals(sArray[0]) && pass.equals(sArray[1]))
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
