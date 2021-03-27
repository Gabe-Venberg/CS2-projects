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
        
        final int maxMag = 9;
        //maxMag-1 orders of magnitude (because we start from 10).
        //and 5 methods to try + col for iterations.
        long[][] data = new long[maxMag][6];
        long startTime;
        long endTime;
        
        for(int i=1; i<=maxMag; i++){
            //figure out the number of iterations
            int limit = (int) Math.pow(10, i);
            //put number of iterations in table.
            data[i-1][0]=limit;
            
            //array stack
            System.out.println("mag "+i+" arrayStack");
            ArrayStack arrayStack = new ArrayStack(limit);
            startTime = System.nanoTime();
            for(int j=0; j<limit; j++){
                arrayStack.push(j);
            }
            for(int j=0; j<limit; j++){
                arrayStack.pop();
            }
            endTime = System.nanoTime();
            data[i-1][1]=endTime-startTime;
            arrayStack = null;
            
            //linked stack
            System.out.println("mag "+i+" linkedStack");
            LinkedStack linkedStack = new LinkedStack();
            startTime = System.nanoTime();
            for(int j=0; j<limit; j++){
                linkedStack.push(j);
            }
            for(int j=0; j<limit; j++){
                linkedStack.pop();
            }
            endTime = System.nanoTime();
            data[i-1][2]=endTime-startTime;
            linkedStack = null;
            
            //array queue
            System.out.println("mag "+i+" arrayQueue");
            ArrayQueue arrayQueue = new ArrayQueue(limit);
            startTime = System.nanoTime();
            for(int j=0; j<limit; j++){
                arrayQueue.enqueue(j);
            }
            for(int j=0; j<limit; j++){
                arrayQueue.dequeue();
            }
            endTime = System.nanoTime();
            data[i-1][3]=endTime-startTime;
            arrayQueue = null;
            
            //linked queue
            System.out.println("mag "+i+" linkedQueue");
            LinkedQueue linkedQueue = new LinkedQueue();
            startTime = System.nanoTime();
            for(int j=0; j<limit; j++){
                linkedQueue.enqueue(j);
            }
            for(int j=0; j<limit; j++){
                linkedQueue.dequeue();
            }
            endTime = System.nanoTime();
            data[i-1][4]=endTime-startTime;
            linkedQueue = null;
            
            //array list
            System.out.println("mag "+i+" arrayList");
            ArrayList arrayList = new ArrayList();//testing the auto grow
            startTime = System.nanoTime();
            for(int j=0; j<limit; j++){
                arrayList.add(j, j);
            }
            for(int j=limit-1; j>0; j--){ //this takes AGES if i remove at index 0 with an incrementing loop due to having to shift everything.
                arrayList.remove(j);
            }
            endTime = System.nanoTime();
            data[i-1][5]=endTime-startTime;
            arrayList = null;
        }
        
        //now we make an array of strings and format the numbers nicely...
        String[][] strings = new String[maxMag][6];
        for(int i=0; i<data.length; i++){
            for(int j=0; j<data[i].length; j++){
                strings[i][j]=String.format("%,d", data[i][j]);
            }
        }
        
        //and print out the table.
        System.out.println(ASCIITable.render(strings, 2));
    }
}
