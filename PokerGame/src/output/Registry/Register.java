package output.Registry;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Register implements ICheckUser {
    public Boolean checkUser(String username, byte [] pass){
        try {
            BufferedReader file
                = new BufferedReader(new FileReader("output\\Database\\UserData.txt"));

            Scanner in = new Scanner(file);
            boolean userFound = false;
            while (in.hasNextLine())
            {
                String s = in.nextLine();
                //prevFileContents=prevFileContents.concat(s + "\n");
                String[] sArray = s.split(",",2);
                System.out.println(sArray[0]);
                if (username.equals(sArray[0]))
                {
                    userFound=true;
                }
            }
            if(!userFound){
                in.close();
                try {
                    System.out.println("hi");
                    writeToFile("UserData.txt",username,pass);
                }catch (IOException e){

                }

                JOptionPane.showMessageDialog(null,
                        "Registration Successful", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else
            {
                in.close();
                JOptionPane.showMessageDialog(null,
                        "Username already taken", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "User Database file Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void writeToFile(String fileName,String usr, byte[] pass)throws IOException {
        String text = usr+","+ Arrays.toString(pass);
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("output\\Database\\UserData.txt", true)  //Set true for append mode
        );
        writer.newLine();   //Add new line
        writer.write(text);
        writer.flush();
        writer.close();

        BufferedReader file
                = new BufferedReader(new FileReader("output\\Database\\UserData.txt"));

        Scanner in = new Scanner(file);

        while (in.hasNextLine())
        {
            String s = in.nextLine();
            //prevFileContents=prevFileContents.concat(s + "\n");

            System.out.println(s);
        }
    }
}
