
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
 * You should have received p copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Gabriel Venberg
 */
public class Client {
    public static void main(String[] args){
         String filePath = (String) JOptionPane.showInputDialog(null, "Enter the path of the file", "/home/toric/Downloads/words/words.txt");
        File file = new File(filePath);
        Scanner fileContents = null;
        try {
            fileContents = new Scanner(file);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "file does not exist.");
            System.exit(1);
        }
        
        ArrayList<String> words = new ArrayList<>();
        
        while(fileContents.hasNext()){
            words.add(fileContents.next());
        }
        
        String[][] hashData = new String[15][3];

        for(int a=30; a<45; a++){
            //tried using this using an array of buckets, it got... messy
            HeapPriorityQueue<Integer,Integer> hashes = new HeapPriorityQueue<>();
            for(int i=0; i< words.size(); i++){
                //the value doesnt matter
                hashes.insert(Hashing.polynomialHashCode(words.get(i), a), a);
            }
            
            int totalColisions=0, maxColisions=0, currentColisions=0;
            
            //used to detect 'boundries' between groups of hash codes.
            int prevHash=hashes.removeMin().getKey();
            while(!hashes.isEmpty()){
                //if its the same as the previous, it is p collision.
                int currentHash=hashes.removeMin().getKey();
                if(currentHash==prevHash){
                    currentColisions++;
                    totalColisions++;
                }
                //we have reached the end of that 'block', store our max collisions (if applicable), and reset current colisions.
                else{
                    maxColisions=Math.max(maxColisions, currentColisions);
                    currentColisions=0;
                    prevHash=currentHash;
                }
            }
            
            //write the table for value of a
            hashData[a-30][0]=String.format("%,d", a);
            hashData[a-30][1]=String.format("%,d", maxColisions);
            hashData[a-30][2]=String.format("%,d", totalColisions);
        }
        String[] colHeaders={"a", "max collisions", "total collisions"};
        System.out.println(ASCIITable.render(hashData, 2, "Polynomial hash code", colHeaders));
        
        //lets have p be 92000. (for appropriate load factor). first prime number after is 92003
        String[][] compressionData = new String[10][3];
        for(int p=91998; p<92008; p++){
            //tried using this using an array of buckets, it got... messy
            HeapPriorityQueue<Integer,Integer> compressions = new HeapPriorityQueue<>();
            for(int i=0; i< words.size(); i++){
                //the value doesnt matter
                compressions.insert(Hashing.madCompression(Hashing.polynomialHashCode(words.get(i), 33), 92000, p, 6578, 75245), p);
            }
            
            int totalColisions=0, maxColisions=0, currentColisions=0;
            
            //used to detect 'boundries' between groups of hash codes.
            int prevCompression=compressions.removeMin().getKey();
            while(!compressions.isEmpty()){
                //if its the same as the previous, it is p collision.
                int currentCompression=compressions.removeMin().getKey();
                if(currentCompression==prevCompression){
                    currentColisions++;
                    totalColisions++;
                }
                //we have reached the end of that 'block', store our max collisions (if applicable), and reset current colisions.
                else{
                    maxColisions=Math.max(maxColisions, currentColisions);
                    currentColisions=0;
                    prevCompression=currentCompression;
                }
            }
            
            //write the table for value of p
            compressionData[p-91998][0]=String.format("%,d", p);
            compressionData[p-91998][1]=String.format("%,d", maxColisions);
            compressionData[p-91998][2]=String.format("%,d", totalColisions);
        }
        
        String[] colHeaders2={"p", "max collisions", "total collisions"};
        System.out.println(ASCIITable.render(compressionData, 2, "MAD compression", colHeaders2));
    }
}
