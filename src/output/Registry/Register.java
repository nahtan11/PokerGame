package output.Registry;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;



public class Register implements ICheckUser {
    public Boolean checkUser(String username, String pass){
        //String prevFileContents = "";
        try {
            URL url = getClass().getResource("UserData.txt");
            File file = new File(url.getPath());
            System.out.println(url.getPath());
            Scanner in = new Scanner(file);
            boolean userFound = false;
            while (in.hasNextLine())
            {
                String s = in.nextLine();
                //prevFileContents=prevFileContents.concat(s + "\n");
                String[] sArray = s.split(",");
                System.out.println(sArray[0]);
                if (username.equals(sArray[0]))
                {
                    userFound=true;
                }
            }
            if(!userFound){
                in.close();
                try {
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

    public void writeToFile(String fileName,String usr, String pass)throws IOException {
        String text = "\n"+usr+","+pass;
        //FileWriter fileWriter = new FileWriter("src//output//"+fileName, true);
        //File file = new File("src//output//"+fileName);
        FileWriter fileWriterTemp = new FileWriter("C:\\Users\\nathan\\Documents\\College\\PokerGame\\src\\output\\UserData.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriterTemp);
        printWriter.print("\n"+usr+","+pass);
        printWriter.close();

        URL url = getClass().getResource("UserData.txt");
        File file = new File(url.getPath());
        System.out.println(url.getPath());
        Scanner in = new Scanner(file);

        while (in.hasNextLine())
        {
            String s = in.nextLine();
            //prevFileContents=prevFileContents.concat(s + "\n");

            System.out.println(s);
        }
    }
}
