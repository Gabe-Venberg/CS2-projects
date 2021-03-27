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
public class Player {
    private String name;
    private String positionPlayed; //I think positionPlayed should be a string? I know absolutly nothing about football.
    int jersyNumber;
    
    public Player(String name, String positionPlayed, int jersyNumber){
        this.name = name;
        this.positionPlayed = positionPlayed;
        this.jersyNumber = jersyNumber;
    }
    /**
     * 
     * @return name of player
     */
    public String getName(){return name;}
    
    /**
     * 
     * @return positonPlayed of player
     */
    public String getPositionPlayed(){return positionPlayed;}
    
    /**
     * 
     * @return jersyNumber of player
     */
    public int getJersyNumber(){return jersyNumber;}
    
    /**
     * sets name of player
     * @param name 
     */
    public void setName(String name){this.name=name;}
    
    /**
     * sets positionPlayed of player
     * @param positionPlayed 
     */
    public void setPositionPlayed(String positionPlayed){this.positionPlayed=positionPlayed;}
    
    /**
     * sets jersyNumber of player
     * @param jersyNumber 
     */
    public void setJersyNumber(int jersyNumber){this.jersyNumber=jersyNumber;}
    
    /**
     * outputs contents of object as string
     * @return String
     */
    public String toString(){
        return getClass().getName()+'@'+':'+name+':'+positionPlayed+':'+jersyNumber;
    }
    
    /**
     * compares with another object
     * @param o object to compare to
     * @return true if objects are identical.
     */
    public boolean equals(Object o){
        if (!(o instanceof Player)){return false;}
        
        Player p = (Player) o;
        
        return name.equals(p.name)&&
                positionPlayed.equals(p.positionPlayed)&&
                jersyNumber==p.jersyNumber;
    }
}
