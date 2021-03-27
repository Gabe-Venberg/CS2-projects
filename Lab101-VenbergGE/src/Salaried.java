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
 *@version 01/26/2021
 * @author toric
 */
public class Salaried extends Employee {
    
    private String title;
    private int salary;
    private static int salariedCount;
    
    /**
     * 
     * @param name
     * @param title
     * @param id
     * @param salary 
     */
    public Salaried(String name, String title, int id, int salary){
        super(name,id);
        this.title = title;
        this.salary = salary;
        salariedCount++;
    }
    
    /**
     * 
     * @return title
     */
    public String getTitle(){return title;}
    
    /**
     * 
     * @return salary
     */
    public int getSalary(){return salary;}
    
    /**
     * 
     * @return salariedCount
     */
    public int getSalariedCount(){return salariedCount;}
    
    /**
     * 
     * @param title sets title
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * 
     * @param salary sets salary
     */
    public void setSalary(int salary){
        this.salary = salary;
    }
    
    /**
     * 
     * @param o the object to comapre against
     * @return true if the objects are equal
     */
    public boolean equals (Object o){
        if (!(o instanceof Salaried)){
            return false;
        }
        Salaried s = (Salaried)o;
        return super.equals(s)
                && title.equals(s.title)
                && salary == s.salary;
    }
    
    /**
     * 
     * @return String contents of instance
     */
    public String toString(){
        return super.toString()+':'+getClass().getName()+'@'+title+':'+salary;
    }
    
}
