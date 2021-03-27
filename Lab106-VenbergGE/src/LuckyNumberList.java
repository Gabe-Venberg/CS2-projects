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

import java.util.NoSuchElementException;

/**
 *
 * @author Gabriel Venberg
 */
public class LuckyNumberList {
    private LinkedPositionalList<LuckyNumber> luckyNumbers;
    
    public LuckyNumberList(){
        luckyNumbers = new LinkedPositionalList<LuckyNumber>();
    }
    
    public void addLuckyNumber(LuckyNumber l){
        luckyNumbers.addLast(l);
    }
    
    private class positionIterator implements Iterator<Position<LuckyNumber>>{
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        public boolean hasNext(){return(cursor!=null);}
        
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if(cursor==null){throw new NoSuchElementException("nothing left");}
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            return recent;
        }
        public void remove() throws IllegalStateException{
            if(recent==null){throw new IllegalStateException("nothing to remove");}
            luckyNumbers.remove(recent);
            recent=null;
        }
    }
    
    private class positionIterable implements Iterable<Position<LuckyNumber>>{
        public Iterator<Position<LuckyNumber>> iterator(){return new positionIterator();}
    }
    
    public Iterable<Position<LuckyNumber>> positions(){
        return new positionIterable();
    }
    
    private class EvenPositionIterator implements Iterator<Position<LuckyNumber>>{
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        public boolean hasNext(){return(cursor!=null);}
        
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if(cursor==null){throw new NoSuchElementException("nothing left");}
            if(recent==null){
                while((cursor != null) && !(cursor.getElement().getLuckyNumber()%2==0)){
                    cursor = luckyNumbers.after(cursor);
                }
            }
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            //advance cursor to next even number
            while((cursor != null) && !(cursor.getElement().getLuckyNumber()%2==0)){
                cursor = luckyNumbers.after(cursor);
            }
         return recent;
        }
        public void remove() throws IllegalStateException{
            if(recent==null){throw new IllegalStateException("nothing to remove");}
            luckyNumbers.remove(recent);
            recent=null;
        }
    }
    
    private class EvenPositionIterable implements Iterable<Position<LuckyNumber>>{
        public Iterator<Position<LuckyNumber>> iterator(){return new EvenPositionIterator();}
    }
    
    public Iterable<Position<LuckyNumber>> EvenPositions(){
        return new EvenPositionIterable();
    }
    
    //utility class
    private boolean isPrime(int n){
        for(int i=2; i<n/2;i++){
            if(n%i==0){return false;}
        }
        return true;
    }
    
    private class PrimePositionIterator implements Iterator<Position<LuckyNumber>>{
        private Position<LuckyNumber> cursor = luckyNumbers.first();
        private Position<LuckyNumber> recent = null;
        
        public boolean hasNext(){return(cursor!=null);}
        
        public Position<LuckyNumber> next() throws NoSuchElementException {
            if(cursor==null){throw new NoSuchElementException("nothing left");}
            if(recent==null){
                while(cursor != null && !isPrime(cursor.getElement().getLuckyNumber())){
                    cursor = luckyNumbers.after(cursor);
                }
            }
            recent = cursor;
            cursor = luckyNumbers.after(cursor);
            //advance cursor to next even number
            while(cursor != null && !isPrime(cursor.getElement().getLuckyNumber())){
                cursor = luckyNumbers.after(cursor);
            }
         return recent;
        }
        public void remove() throws IllegalStateException{
            if(recent==null){throw new IllegalStateException("nothing to remove");}
            luckyNumbers.remove(recent);
            recent=null;
        }
    }
    
    private class PrimePositionIterable implements Iterable<Position<LuckyNumber>>{
        public Iterator<Position<LuckyNumber>> iterator(){return new PrimePositionIterator();}
    }
    
    public Iterable<Position<LuckyNumber>> PrimePositions(){
        return new PrimePositionIterable();
    }
    
    
}
