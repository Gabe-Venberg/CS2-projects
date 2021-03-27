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
import java.util.Scanner;
import java.lang.StringBuilder;

public class Recursion {
    
    public static int sum(File file) throws FileNotFoundException{
        Scanner input = new Scanner(file);
        
        int fileLength = 0;
        //wont be using the singly linked list class from last time, as it was really incomplete. Ill hack this together with a proper array.
        while(input.hasNext()==true){
            if(input.hasNextInt()){
                fileLength ++;
                input.nextInt();
            }
            else{
                input.next();
            }
        }
        input.close();
        
        input = new Scanner(file); //resetting the scanner.
        
        int[] product = new int[fileLength];
        
        for(int i=0; i<product.length; i++){
            product[i]=input.nextInt();
        }
        
        return sumCalc(product);
    }
    
    public static double harmonic(double n){
        if(n<=0){
            throw new IllegalArgumentException("input must be > 1");
        }
        else if(n==1){
            return 1;
        }
        else{
            return (1.0/n)+harmonic(n-1.0);
        }
    }
    
    //splitting up the code here, was to implement the sum by itself.
    
    private static int sumCalc(int a[]){ 
        /* It can actually be realy easy to check if a number is a power of 2 thanks to binary.
        all powers of 2 take the form of 1 followed by a n 0's. If we sub 1 from this number,
        it becomes 0 followed by n 0's. Something like this (for the number 16)
        10000 (16)
        01111 (15)
        So taking a bitwise and will give us 0. (note that this only works with counting numbers, so no 0!)
        
        Note that I dont have a proof that only powers of 2 do this, just a strong
        suspicion and a brute force search of the first million integers in python.
        I may have gotten a bit distracted... 
        */
        if((a.length&(a.length-1))==0){
            if(a.length==1){return a[0];}
            else{
                int[] tmp = new int[a.length/2];
                for(int i=0; i<tmp.length; i++){
                    tmp[i] = a[2*i]+a[2*i+1];
                }
                return sumCalc(tmp);
            }
        }
        else{
            throw new IllegalArgumentException("input must be a power of 2.");
        }
    }
    
    public static void find(String startPath, String filename) throws IOException{
        /*so, I spent an hour trying to write a (O(n!)(I think)) recursive substring search function,
        rather than a find [startPath] -name 'filename' ... I can *totaly* read...
        */
        
        File startFile = new File(startPath);
        
        if(!startFile.isDirectory()){ //base case is a non-directory
            if(startFile.getName().equals(filename)){
                System.out.println(startFile.getCanonicalPath());
            }
        }
        else{
            String[] nextFiles = startFile.list(); //array of files to try next
            
            for(int i=0; i<nextFiles.length; i++){
                find(nextFiles[i], filename);
            }
        }
    }
}
