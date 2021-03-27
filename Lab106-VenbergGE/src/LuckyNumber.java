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
public class LuckyNumber {
    private String name;
    private int luckyNumber;
    
    Random rand = new Random();
    
    public LuckyNumber(String name){
        this.name = name;
        luckyNumber = rand.nextInt(10);
    }
    
    public String getName(){return name;}
    
    public int getLuckyNumber(){return luckyNumber;}
    
    public boolean equals(Object o){
        if (!(o instanceof LuckyNumber)){return false;}
        LuckyNumber l = (LuckyNumber) o;
        return name.equals(l.name)
                && luckyNumber == l.luckyNumber;
    }
    
    public String toString(){
        return getClass().getName() + ':'+name+';'+luckyNumber;
    }
}
