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
import java.util.Random;
public class client {
    public static void main(String[] args){
        //initalization stuff
        Employee[] employees =  new Employee[50000];
        long startTime;
        long endTime;
        String[][] data = new String[4][2];
        Random rgen = new Random();
        Comparator compareName=new NameComparator();
        Comparator compareDept=new DeptComparator();
        Comparator compareID=new IDComparator();
        Comparator compareHireDate=new HireDateComparator();
        Employee[] tmp = new Employee[employees.length];
        
        for(int i=0; i<employees.length; i++){
            employees[i]=new Employee(rgen.nextInt(100000000), rgen.nextInt(4)+1, rgen.nextInt(26)+1995, randomPrintableString());
        }
        
        //tried doing this with a method, did NOT want to work with me for some reason. Even tried using the arrayCopyRange method in employee, kept giving me:
        //Exception in thread "main" java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class [LEmployee; ([Ljava.lang.Object; is in module java.base of loader 'bootstrap'; [LEmployee;
        for(int i=0; i<employees.length; i++){
            tmp[i]=employees[i];
        }
        startTime=System.nanoTime();
        Sort.mergeSort(tmp, compareName);
        endTime=System.nanoTime();
        data[0][0]="merge sort by name";
        data[0][1]=String.format("%,d", endTime-startTime);
        
        for(int i=0; i<employees.length; i++){
            tmp[i]=employees[i];
        }        startTime=System.nanoTime();
        Sort.quickSortArray(tmp, compareDept);
        endTime=System.nanoTime();
        data[1][0]="quick sort by dept";
        data[1][1]=String.format("%,d", endTime-startTime);
        
        for(int i=0; i<employees.length; i++){
            tmp[i]=employees[i];
        }
        startTime=System.nanoTime();
        Sort.bubbleSort(tmp, compareID);
        endTime=System.nanoTime();
        data[2][0]="bubble sort by ID";
        data[2][1]=String.format("%,d", endTime-startTime);
        
        for(int i=0; i<employees.length; i++){
            tmp[i]=employees[i];
        }
        Comparator[] radixComparator = {compareDept, compareHireDate, compareName};
        startTime=System.nanoTime();
        Sort.radixSort(tmp, radixComparator);
        endTime=System.nanoTime();
        data[3][0]="radix sort";
        data[3][1]=String.format("%,d", endTime-startTime);
        
        String[] colHeaders = {"sort", "nanoseconds taken"};
        System.out.println(ASCIITable.render(data, 2, "time of sorts", colHeaders));
    }
    
    /**
     * generates a random string made of lowercase letters, 5 to 10 chars long. not general purpouse.
     * @return 
     */
    public static String randomPrintableString(){
        Random rgen = new Random();
        int length = rgen.nextInt(6)+5;
        String output="";
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        for(int i=0; i<length; i++){
            output = output+letters.charAt(rgen.nextInt(letters.length()));
        }
        return output;
    }
    private static <K> K[] arrayCopy(K[] toCopy){
        K[] output = (K[]) new Object[toCopy.length];
        for(int i=0; i<toCopy.length; i++){
            output[i]=toCopy[i];
        }
        return output;
    }
}
