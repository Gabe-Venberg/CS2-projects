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
 * an implementation of polynomial hash code and MAD compression functions
 * @author Gabriel Venberg
 */
public class Hashing {
    
    /**
     * hashes a given string (x0,x1,...,xn-1) by x0a^n-1+x1a^n-2+...+xn-2a+xn-1. using a given value of a
     * @param value the string to hash
     * @param a the value of a to use
     * @return an int representing the resulting hash.
     */
    public static int polynomialHashCode(String value, int a){
        //due to twos compliment math, we dont actually need to use longs to detect rollover, and can just use a mask to ignore the sign bit!
        //also, wasted 2 hours because I had set this to MIN_VALUE....
        final int mask = Integer.MAX_VALUE;
        int hash=0;
        //using horners rule iteratively
        for(int i=0; i<value.length(); i++){
            hash = (hash * a) + (int) value.charAt(i);
        }
        // im *pretty sure* we only need to zero the sign bit once... will test
        return hash&mask;
    }
    
    /**
     * compresses a hash code with by multiply add divide method with given constants
     * @param value the hash code to compress
     * @param N the given value of N (should be the size of your bucket array if using to make hash table)
     * @param p should be a prime number larger than N
     * @param a random value between 1 and p-1
     * @param b random value between 0 and p-1
     * @return the compressed hash.
     */
    public static int madCompression(int value, int N, int p, int a, int b) throws IllegalArgumentException{
        if(0>b||b>p-1){throw new IllegalArgumentException("b needs to be between 0 and p-1, inclusive.");}
        if(0>=a||a>p-1){throw new IllegalArgumentException("a needs to be betwwen 1 and p-1, inclusive.");}
        return ((a*value+b)%p)%N;
    }
}
