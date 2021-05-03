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
 * a simple container class for employees.
 * @author Gabriel Venberg
 */
public class Employee {
    private int SSN;
    private String name;
    private int dept;
    private int hireDate;
    
    public Employee(int SSN, int dept, int hireDate, String name) throws IllegalArgumentException{
        if(SSN<0||SSN>99999999){throw new IllegalArgumentException("SSN must be between 0 and 99999999");}
        if(dept<1||dept>5){throw new IllegalArgumentException("dept must be between 1 and 5");}
        if(hireDate<1995||hireDate>2021){throw new IllegalArgumentException("hireDate must be between 1995 and current year.");}
        this.SSN=SSN;
        this.dept=dept;
        this.hireDate=hireDate;
        this.name=name;
    }
    
    public int getSSN(){return SSN;}
    public int getDept(){return dept;}
    public int getHireDate(){return hireDate;}
    public String getName(){return name;}
    
    public void setDept(int dept){
        if(dept<1||dept>5){throw new IllegalArgumentException("dept must be between 1 and 5");}
        this.dept=dept;
    }
    
    public void setName(String name){
        this.name=name;
    }
}
