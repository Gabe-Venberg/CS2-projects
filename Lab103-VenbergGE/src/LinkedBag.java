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
 *@date 02/10/2021
 * @author Gabriel Venberg
 */
public class LinkedBag<T> implements Bag<T> {
    
    //dont need a count variable, linkedlist already takes care of that.
    private SinglyLinkedList list;
    
    public LinkedBag(){
        list = new SinglyLinkedList<T>();
    }
    
    public LinkedBag(int initSize){
        this();
    }
    /**
     * 
     * @return size of list
     */
    public int size(){return list.size();}
    
    /**
     * 
     * @return true if list is empty
     */
    public boolean isEmpty(){return list.isEmpty();}
    
    /**
     * emptys list of all entries
     */
    public void clear(){
        for(int i=0; i<list.size(); i++){
            list.removeFirst();
        }
    }
    
    /*
    Ok, so this next bit gets MESSY. I feel like we should just add a method or two to the singlyLinkedList class.
    The class that my copy of the book has only contains methods for acessing/adding to the list at the tail or head,
    and only lets me remove from the head. This means that any object not at the head or tail is COMPLETELY INACESSABLE
    to me without modifying the list. So, from here on out, I will be using a second temporary stack and move items from
    one or the other, reading/modifying each item as its tranferred. This is wildly inneffiennt compared
    to if the linkedlist class just let me traverse the list, but the assignment says not to modify the list, sooo...
    */
    
    /**
     * 
     * @param object object to search for
     * @return number of times object appears in list.
     */
    public int getFrequencyOf(T object){
        T entry; //dont want to re-initalize this every loop.
        SinglyLinkedList tmp = new SinglyLinkedList<T>();
        int freqency=0;
        
        int size = list.size(); //If you dont do this, list.size will shrink every loop, and you will only get through half the list as i grows and list.size shrinks.
        for(int i=0; i<size; i++){
            //not sure why I had to cast this, it should return T, but the complier seems to think its returning Object.
            entry = (T) list.removeFirst();
            if(entry.equals(object)){
                freqency++;
            }
            //if we did addFirst, it would result in tmp being an inverted version of list.
            tmp.addLast(entry);
        }
        //we have shifted all of list to tmp.
        list = tmp;
        return freqency;
    }
    
    /**
     * 
     * @param object object to search for
     * @return true if list contains at least one of object.
     */
    public boolean contains(T object){
        /*
        so in arrayBag I used different methods for contains and frequency to save time after finding a match in contains.
        Why not in this method? because I cant stop halfway through. I am destructivly reading the list and reconstructing it in tmp,
        meaning I cant stop halfway through even if I do find a match. only thing I would be saving would be the comparison.
        */
        return getFrequencyOf(object)>0;
    }
    
    public void add(T object){
        list.addFirst(object);
    }
    
    /**
     * deletes the first instance of object from the list.
     * @param object object to delete
     * @return object that was deleted
     */
    public T remove(T object){
        T entry; //dont want to re-initalize this every loop.
        T result=null; //cant have return ending our loop early.
        SinglyLinkedList tmp = new SinglyLinkedList<T>();
        
        int size = list.size(); //If you dont do this, list.size will shrink every loop, and you will only get through half the list as i grows and list.size shrinks.
        for(int i=0; i<size; i++){
            entry = (T) list.removeFirst();
            //if the object we want to delete is found, we record it and break out of the loop BEFORE we put it on the new stack.
            if (entry.equals(object)){
                result = entry;
                break;
            }
            tmp.addLast(entry);
        }
        
        //finish the juggle the loop SHOULD do nothing if list is empty by now.
        size = list.size(); //If you dont do this, list.size will shrink every loop, and you will only get through half the list as i grows and list.size shrinks.
        for(int i=0; i<size; i++){
            tmp.addLast(list.removeFirst());
        }
        list=tmp;
        return result;
    }
    
    /**
     * removes a random object from the list
     * @return object that was deleted
     */
    public T remove(){
        T result=null; //cant have return ending our loop early.
        SinglyLinkedList tmp = new SinglyLinkedList<T>();
        
        java.util.Random rand = new java.util.Random();
        
        int indexToRemove = rand.nextInt(list.size());
        
        //juggle a bit before we delete
        for(int i=0; i<indexToRemove; i++){
            tmp.addLast(list.removeFirst());
        }
        
        //delete
        result=(T) list.removeFirst();
        
        //finish the juggle...
        for(int i=0; i<list.size(); i++){
            tmp.addLast(list.removeFirst());
        }
        
        list=tmp;
        return result;        
    }
    
    /**
     * retrieves an item by its index. more expensive than you think!
     * @param item index of item to retrieve
     * @return retrieved item
     */
    public T get(int item){
        T result = null;
       SinglyLinkedList tmp = new SinglyLinkedList<T>();
                        
        //juggle till we get to where we need to be
        for(int i=0; i<item; i++){
            tmp.addLast(list.removeFirst());
        }
        
        //record
        result=(T) list.removeFirst();
        tmp.addLast(result);
        
        //finish the juggle...
        for(int i=0; i<list.size(); i++){
            tmp.addLast(list.removeFirst());
        }
        
        list=tmp;//at this point Im just thankfull we can do this instead of having to juggle BACK!
        return result;
    }
    
    /**
     *
     * @return contents of object as string
     */
    public String toString(){
        String contents ="";
        T entry;
        SinglyLinkedList tmp = new SinglyLinkedList<T>();
        //the first record of juggling dates to between 1994 and 1781 BC, with the egyptians!
        int size = list.size(); //If you dont do this, list.size will shrink every loop, and you will only get through half the list as i grows and list.size shrinks.
        for(int i=0; i<size; i++){
            entry = (T) list.removeFirst();
            contents = contents+':'+entry.toString();
            tmp.addLast(entry);
        }
        list=tmp;
        return getClass().getName()+'@'+list.size()+':'+contents;
    }
    
    /**
     * compares with another object.
     * @param o object to compare with
     * @return true if objects are identical
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedBag)){return false;}
        
        LinkedBag l = (LinkedBag) o;
        if(l.size()!=list.size()){return false;}
        
        SinglyLinkedList tmp1 = new SinglyLinkedList<T>();
        SinglyLinkedList tmp2 = new SinglyLinkedList<T>();
        boolean result = true;
        T entry1, entry2;//need two of these, as I dont want to destroy the list passed to me, either.
        
        /*
        In ancient China, juggling was an art performed by some warriors.
        One such warrior was Xiong Yiliao, whose juggling of nine balls
        in front of troops on a battlefield reportedly caused the opposing troops to flee without fighting,
        resulting in a complete victory. (from wikipedia)
        */
        int size = list.size(); //If you dont do this, list.size will shrink every loop, and you will only get through half the list as i grows and list.size shrinks.
        for(int i=0; i<size; i++){
            entry1=(T) list.removeFirst();
            entry2=(T) l.list.removeFirst();
            //dont want to return with list items still 'in the air' (ive taken this juggling metaphor to far! help!)
            if(!entry1.equals(entry2)){result = false;}
            tmp1.addLast(entry1);
            tmp2.addLast(entry2);
        }
        list=tmp1;
        l.list=tmp2;
        return result;
    }
}
