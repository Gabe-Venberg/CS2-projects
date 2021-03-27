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
 * @version 02/02/2021
 * @author toric
 */
public class Client {
    public static void main(String[] args){
        
        Scores scores = new Scores(100);
        
        //dont want to make rand object 100 times...
        java.util.Random rand = new java.util.Random();
        for(int i=0; i<100; i++){
            scores.add(rand.nextInt(201)-100); //the nextint generates between 0 (inclusive) and max(exclusive)
        }
        
        System.out.println(scores.toString());
        
        scores.add(86);
        
        System.out.println(scores.size());
        
        scores.remove();
        
        int pos75 = scores.get(74); //you said to get the 75th number, which is index 74.
        
        System.out.println(scores.getFrequencyOf(pos75));
        
        scores.remove(pos75);
        
        System.out.println(scores.getFrequencyOf(pos75));
        
        System.out.println(scores.getFrequencyOf(86));
        
        System.out.println(scores.contains(86));
    }
}
