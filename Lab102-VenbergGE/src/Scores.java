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
 * @version 01/29/2021
 * @author toric
 */
public class Scores{
    private int[] list;
    private int count=0;
    
    /**
     * default constructor
     */
    public Scores(){
        list = new int[50];
    }
    
    /**
     * constructs starting array based off of passed size.
     * @param initSize size of inital array
     */
    public Scores(int initSize){
        list = new int[initSize];
    }
    
    /**
     * returns number of values of scores.
     * @return size of list
     */
    public int size(){return count;}
    
    /**
     * returns true if empty
     * @return boolean
     */
    public boolean isEmpty(){
        if(count==0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * clears contents of array
     */
    public void clear(){count = 0;} //we dont need to delete the actual contents, just mark them as garbage.
    
    /**
     * 
     * @param object number you want to know frequency of
     * @return number of times object occurs
     */
    public int getFrequencyOf(int object){
        int frequency=0;
        for(int i=0; i<count; i++){
            if(list[i]==object){frequency++;}
        }
        return frequency;
    }
    
    /**
     * 
     * @param object number you want to know whether the list contains. 
     * @return boolean true if object is in the list.
     */
    public boolean contains(int object){
        for(int i=0; i<count; i++){
            if(list[i]==object){return true;}
        }
        return false;
    }
    
    /**
     * adds object to the end of the list.
     * @param object the number to add to the end.
     */
    public void add(int object){
        if(count==list.length){
            int tmp[] = new int[count*2];
            for(int i=0; i<count; i++){
                tmp[i]=list[i];
            }
            list=tmp;
        }
        list[count]=object; //since java has 0-based array, count is also the next free index.
        count++;
    }
    
    /**
     * removes item at index, shifting remaining items to the left. returns the item removed
     * @param int index index of item to remove
     */
    private int removeIndex(int index){ //not sure if we are suppoded to do this, but it deduplicates code.
        int tmp = list[index];
        if(index<count){
            for(int i=index; i<count; i++){
            list[i]=list[i+1]; //shift everything to the left.
            }
            count--;
            return tmp;
        }
        else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    /**
     * removes the first occurrence of object. shifts the remaining values to cover the gap.
     * @param object to remove the first occurrence of.
     */
    public void remove(int object){
        int index=-1;
        for(int i=0; i<count; i++){
            if(list[i]==object){
                index = i;
                break;
            }
        }
        if(index==-1){ //return null if there is no match
            return;
        }
        removeIndex(index);
    }
    
    /**
     * removes a random value from the list. shifts remaining values to cover the gap
     * returns null if list is empty
     */
    public void remove(){
        if(count==0){
            return;
        }
        java.util.Random rand = new java.util.Random();
        
        removeIndex(rand.nextInt(count)); // the upper bound of the nextInt function is exclusive, but since java has 0-based arrays, the max index is 1 less than count.
    }
    
    /**
     * gets value at i
     * @param i index of value to get
     * @return value at index i
     */
    public int get(int i){
        if(i<count){
            return list[i];
        }
        else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    /**
     * 
     * @param o object to compare to
     * @return true of objects are equal
     */
    public boolean equals(Object o){
        if (!(o instanceof Scores)){
            return false;
        }
        Scores s = (Scores)o;
        //easy to check, makes sure we dont waste time doing more in depth check if we dont need to. also saves us from an indexoutofbounds.
        if(!(s.count==count)){
            return false;
        }
        
        for(int i=0; i<count; i++){
            if(!(list[i]==s.list[i])){
                return false; //this has the happy accident of only iterating the list untill we find one inequality.
            }
        }
        //if we get all the way through without discovering an inequality, they must be equal.
        return true;
    }
    
    /**
     * 
     * @return string representing contents of object.
     */
    public String toString(){
        String contents = "";
        for(int i=0; i<count; i++){
            contents =contents+":"+list[i];
        }
        
        return getClass().getName()+'@'+count+':'+contents;
    }
}