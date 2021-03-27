/*
 * Copyright (C) 2021 Gabriel Venberg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Gabriel Venberg
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Client {
    public static String fileToString(String filePath) throws FileNotFoundException{//helper function.
        File file = new File(filePath);
        Scanner input = new Scanner(file);
        String output = "";
        while(input.hasNextLine()){
            output = output +'\n'+input.nextLine();
        }
        return output;
    }
    public static void main(String[] args) throws FileNotFoundException{
        String[] functions = {"Harmonic", "sum", "find"};
        
        String input;
        while(true){
        input = (String) JOptionPane.showInputDialog(null, "What function would you like to test?",
                "Testing", JOptionPane.QUESTION_MESSAGE, null, functions, functions[1]);
        
        switch(input){
            case "Harmonic":
                input = (String) JOptionPane.showInputDialog(null, "what harmonic number do you want?", "Harmonic", 0);
                double hResult =Recursion.harmonic(Integer.parseInt(input));
                JOptionPane.showMessageDialog(null, "the result is "+hResult);
                System.out.println("tested harmonic, input was "+input+" and result was "+hResult);
                break;
            case "sum":
                while(true){ //the loop is just to provide the option of continuing if you want
                input = (String) JOptionPane.showInputDialog(null, "enter the file containing the array", "Sum", 0);
                int sResult;
                    try{
                        sResult = Recursion.sum(new File(input));
                        JOptionPane.showMessageDialog(null, sResult);
                        System.out.println("testing sum, user entered "+input+" and file contained "+fileToString(input)+". result was "+sResult);
                    }
                    catch(FileNotFoundException e){
                        int reply = JOptionPane.showConfirmDialog(null, "file does not exit", "continue?", 0);
                        if(reply == 0){
                            continue;
                        }
                        break;
                    }
                    break;
                }
                break;
            case "find": //you didnt say anything about how this loop should behave. Also, you spesifically said the find method has a void return type, so I cant use the dialog box for output.
                input = (String) JOptionPane.showInputDialog(null, "Enter the directory you want to search", "Find", 0);
                String input2 = JOptionPane.showInputDialog(null, "enter your seach term", 0);
                try{
                    Recursion.find(input, input2);
                    System.out.println("tested find, start directory was "+input+" and search term was "+input2);
                }
                catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "cant find that file");
                }
                break;
        }
        int doAgain = JOptionPane.showConfirmDialog(null, null, "continue?", 0);
        if(doAgain==1){break;}
    }
    }
}
