package output;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Register {
    public void registerUser(String username, String pass)throws IOException{
        try {
            URL url = getClass().getResource("UserData.txt");
            File file = new File(url.getPath());
            System.out.println(url.getPath());
            Scanner in = new Scanner(file);
            boolean userFound = false;
            while (in.hasNextLine())
            {
                String s = in.nextLine();
                String[] sArray = s.split(",");
                System.out.println(sArray[0]);
                if (username.equals(sArray[0]))
                {
                    userFound=true;
                }
            }
            in.close();
            if(!userFound){
                writeToFile("UserData.txt",username,pass);
                JOptionPane.showMessageDialog(null,
                        "Registration Successful", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "Username already taken", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }



        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database file Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void writeToFile(String fileName,String usr, String pass)throws IOException {
        FileWriter fileWriter = new FileWriter("src//output//"+fileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.append("\n");
        printWriter.append(usr);
        printWriter.append(",");
        printWriter.append(pass);
        printWriter.close();
    }
}
