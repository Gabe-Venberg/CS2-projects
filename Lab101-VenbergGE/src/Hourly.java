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
 * @author toric
 */
public class Hourly extends Employee{
    
    private String position;
    private double hourlyRate;
    private static int hourlyCount = 0;
    
    /**
     * 
     * @param name
     * @param position
     * @param id
     * @param hourlyRate 
     */
    public Hourly(String name, String position, int id, double hourlyRate){
        super(name, id);
        this.position = position;
        this.hourlyRate = hourlyRate;
        hourlyCount++;
    }
    
    /**
     * 
     * @return position
     */
    public String getPosition(){return position;}
    
    /**
     * 
     * @return hourlyRate
     */
    public double getHourlyRate(){return hourlyRate;}
    
    /**
     * 
     * @return hourlyCount
     */
    public int getHourlyCount(){return hourlyCount;}
    
    /**
     * 
     * @param position sets position
     */
    public void setPosition(String position){
        this.position = position;
    }
    
    /**
     * 
     * @param hourlyRate sets hourlyRate
     */
    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }
    
    /**
     * 
     * @param o object to compare against
     * @return true if contents of object are equal
     */
    public boolean equals (Object o){
        if (!(o instanceof Hourly)){
            return false;
        }
        Hourly h = (Hourly) o;
        return super.equals(h)
                && position.equals(h.position)
                && hourlyRate == h.hourlyRate;
    }
    
    /**
     * 
     * @return contents of instance
     */
    public String toString(){
        return super.toString()+':'+getClass().getName()+'@'+position+':'+hourlyRate;
    }
    
}
