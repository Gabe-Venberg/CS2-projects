
import java.util.Random;

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
public class Client {
    public static void main(String args[]){
        
        
        //set up stuff needed for test.
        final int BSTSize = 10;
        long startTime;
        long endTime;
        String[][] data = new String[7][2];
        
        //acending order test
        BinarySearchTree testTree = new BinarySearchTree();
        startTime=System.nanoTime();
        for(int i=0; i<BSTSize; i++){
            System.out.println("test1,"+i);
            testTree.insert(i);
        }
        data[0][1] = String.format("%,d", testTree.hight(testTree.root()));
        System.out.println("test2");
        endTime=System.nanoTime();
        data[0][0]=String.format("%,d", endTime-startTime);
        testTree.inorderTraversal(testTree.root());
        System.out.println();
        
        //decending order test
        testTree = new BinarySearchTree();
        startTime=System.nanoTime();
        for(int i=BSTSize-1; i>=0; i--){
            System.out.println("test3,"+i);
            testTree.insert(i);
        }
        data[1][1] = String.format("%,d", testTree.hight(testTree.root()));
        System.out.println("test4,");
        endTime=System.nanoTime();
        data[1][0]=String.format("%,d", endTime-startTime);
        testTree.inorderTraversal(testTree.root());
        System.out.println();
        
        //generate arrray containing numbers 0 through 999,999, for 1 million unique numbers.
        int[] uniqueNumbers = new int[BSTSize];
        for(int i=0; i<BSTSize; i++){
            uniqueNumbers[i]=i;
        }
        
        //random tests
        for(int i=0; i<5; i++){
            testTree = new BinarySearchTree();
            shuffleArray(uniqueNumbers);
            
            startTime=System.nanoTime();
            for(int j=0; j<uniqueNumbers.length; j++){
                System.out.println("test5,"+i+","+j);
                testTree.insert(uniqueNumbers[j]);
            }
            data[i+2][1]=String.format("%,d", testTree.hight(testTree.root()));
            System.out.println("test6,"+i);
            endTime=System.nanoTime();
            data[i+2][0]=String.format("%,d", endTime-startTime);
            testTree.inorderTraversal(testTree.root());
            System.out.println();
        }
        
        String[] colHeaders = {"Time taken", "Tree hight"};
        System.out.println(ASCIITable.render(data, 2, "Binary search trees", colHeaders));
    }
        
    //quick helper function to shuffle an array in place
    public static void shuffleArray(int[] array){
        Random rgen = new Random();
        for(int i=0; i<array.length; i++){
            int randomPosition = rgen.nextInt(array.length);
            //could do this with a temp array that we later return, but to save a bit of memory, we can do this in place.
            int temp = array[i];
            array[i]=array[randomPosition];
            array[randomPosition]=temp;
        }
    }
    
}
