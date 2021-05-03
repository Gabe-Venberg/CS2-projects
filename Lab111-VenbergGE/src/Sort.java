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
 * Class containing a bunch of static sort methods.
 * @author Gabriel Venberg
 */
public class Sort  {
    
    /**
     * copies an array
     * @param <K> type of array
     * @param toCopy array to copy
     * @return copy of array
     */
    public static <K> K[] arrayCopyRange(K[] toCopy, int start, int end){
        K[] output = (K[]) new Object[end-start];
        for(int i=start; i<end; i++){
            output[i-start]=toCopy[i];
        }
        return output;
    }
    
    private static <K> LinkedQueue<K> arrayToQueue(K[] toConvert){
        LinkedQueue<K> output = new LinkedQueue<>();
        for(int i=0; i<toConvert.length; i++){
            output.enqueue(toConvert[i]);
        }
        return output;
    }
    
    private static <K> K[] queueToArray(LinkedQueue<K> toConvert){
        K[] output = (K[]) new Object[toConvert.size()];
        for(int i=0; i<output.length; i++){
            output[i]=toConvert.dequeue();
        }
        return output;
    }
    
    /**
     * sorts a list in place with a bubble sort. stable
     * @param <K> type of array
     * @param toSort array to sort
     * @param comp comparator to use.
     */
    public static <K> void badBubbleSort(K[] toSort, Comparator<K> comp){
        //dont want to be constantly creating and destroying this if we dont have to.
        K tmp;
        for(int i=0; i<toSort.length; i++){
            //start at one to avoid arrayIndexOutOfBounds
            for(int j=1; j<toSort.length; j++){
                if(comp.compare(toSort[j-1], toSort[j])>0){
                    //swapping
                    tmp=toSort[j-1];
                    toSort[j-1]=toSort[j];
                    toSort[j]=tmp;
                }
            }
        }
    }
    
    /**
     * sorts a list in place with an optimized bubble sort. stable
     * @param <K> type of array
     * @param toSort array to sort
     * @param comp comparator to use.
     */
    public static <K> void bubbleSort(K[] toSort, Comparator<K> comp){
        K tmp;
        int lastSwap;
        int sortTo = toSort.length-1;
        do{
            //last place we did a swap
            lastSwap=0;
            //the last place we did a swap is the last place we need to look.
            for(int i=1; i<sortTo; i++){
                if(comp.compare(toSort[i-1], toSort[i])>0){
                    lastSwap=i;
                    //swapping
                    tmp=toSort[i-1];
                    toSort[i-1]=toSort[i];
                    toSort[i]=tmp;
                }
            }
            //so we only compare along the array for as long as we need to.
            sortTo=lastSwap;
        } while(lastSwap<=1);
    }
    
    /**
     * merges the sorted arrays A and B into array C
     * @param <K> type of the arrays
     * @param A first sorted array
     * @param B second sorted array
     * @param C array to merge A and B into.
     * @param comp comparator to use.
     */
    private static <K> void merge(K[] A, K[] B, K[] C, Comparator<K> comp){
        //counters for arrays A and B
        int a=0, b=0;
        while(a+b<C.length){
            //the first part is so that if we have 'emptied' out one list, the rest of the other list is rapidly copied across. the <=0 should make the mergesort stable.
            if(b==B.length||(a<A.length&&comp.compare(A[a], B[b])<=0))
                //put next element of a into c and increment a's counter.
                C[a+b]=A[a++];
            else{
                //put next element of b into c and increment b's counter.
                C[a+b]=B[b++];
            }
        }
    }
    
    /**
     * sorts an array in place with a mergesort. stable
     * @param <K> type of array
     * @param toSort array to sort
     * @param comp comparator to use
     */
    public static <K> void mergeSort(K[] toSort, Comparator<K> comp){
        //if array is trivially sorted. I think this could be made a bit more space efficent by sorting the array with 1 or 0 swaps when we hit a 2 long array...
        if(toSort.length<2){return;}
        //devide the arrays.
        int mid=toSort.length/2;
        K[] A = arrayCopyRange(toSort, 0, mid);
        K[] B = arrayCopyRange(toSort, mid, toSort.length);
        //recurse
        mergeSort(A, comp);
        mergeSort(B, comp);
        //merge results
        merge(A, B, toSort, comp);
    }
    
    /**
     * sorts a queue in place with a quicksort. unstable
     * @param <K> type of array
     * @param toSort array to sort
     * @param comp comparator to use
     */
    public static <K> void quickSort(Queue<K> toSort, Comparator<K> comp){
        //queue is trivially sorted
        if(toSort.size()<2){return;}
        //divide
        K pivot=toSort.first();
        Queue<K> less = new LinkedQueue<>();
        Queue<K> equal = new LinkedQueue<>();
        Queue<K> greater = new LinkedQueue<>();
        
        while(!toSort.isEmpty()){
            K element = toSort.dequeue();
            int c = comp.compare(element, pivot);
            if(c<0){less.enqueue(element);}
            else if(c>0){greater.enqueue(element);}
            else{equal.enqueue(element);}
        }
        //recurse
        quickSort(less, comp);
        quickSort(greater, comp);
        
        //concatenate results
        while(!less.isEmpty()){toSort.enqueue(less.dequeue());}
        while(!equal.isEmpty()){toSort.enqueue(equal.dequeue());}
        while(!greater.isEmpty()){toSort.enqueue(greater.dequeue());}
    }
    
    /**
     * sorts an array with a quicksort. unstable
     * @param <K> type of array
     * @param toSort array to sort
     * @param comp comparator to use
     */
    public static <K> K[] quickSortArray(K[] toSort, Comparator<K> comp){
        LinkedQueue<K> tmp = arrayToQueue(toSort);
        quickSort(tmp, comp);
        return queueToArray(tmp);
    }
    
    /**
     * sorts an array based on multiple keys, from least
     * @param <K> type of array to be sorted
     * @param toSort array to sort
     * @param comp array of comparators to sort by, with array going from most significant comparator at the start and ending with the least significant comparator.
     */
    //in order to keep this generic, we cant use a bucket sort, as we dont have any assumptions about the range of the data. instead, this just applies a series of stable sorts, as described in the textbook and lecture. (latimer didnt get around to explaining the non-naive way to do a radix sort.
    public static <K> void radixSort(K[] toSort, Comparator<K>[] comp) throws IllegalArgumentException{
        if(comp.length==0){throw new IllegalArgumentException("must have at least 1 comparator in comp array.");}
        
        for(int i=comp.length-1; i<=0; i++){
            mergeSort(toSort, comp[i]);
        }
    }
}
