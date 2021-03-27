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
 * @version 01/25/2021
 */
public class Employee {
    private static int employeeCount =0;
    private int id;
    private String name;
    
    /**
     * 
     * @param name
     * @param id 
     */
    public Employee (String name, int id){
        this.id = id;
        this.name = name;
        employeeCount++;
    }
    
    /**
     * 
     * @return id
     */
    public int getID(){return id;}
    
    /**
     * 
     * @return name
     */
    public String getName(){return name;}
    
    /**
     * 
     * @return employeeCount
     */
    public int getEmployeeCount(){return employeeCount;}
    
    /**
     * 
     * @param id sets id
     */
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * 
     * @param name sets name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * 
     * @param o the object to be compared against
     * @return true if the objects are equal
     */
    public boolean equals( Object o )
    {
        if (!(o instanceof Employee)){
            return false;
        }
        Employee e=(Employee)o;
        return id == e.id
                && name.equals( e.name );
    }
    
    /**
     * 
     * @return contents of the instance
     */
    public String toString(){
        return getClass().getName() + '@' + ':'+name+':'+id+':'+employeeCount;
    }
    
}
