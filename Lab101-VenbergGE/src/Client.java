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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author toric
 */
public class Client {
    public static void main(String[] args) throws FileNotFoundException{
        File inputFile = new File("/home/toric/Downloads/employeeList.txt"); //TODO!!! change to C:/data/EmployeeList.txt //supposedly with java you can use / on windows as well as linux.
        Scanner file = new Scanner(inputFile);
        Scanner scan = new Scanner(System.in);
        
        final int maxEmployees = 20;
        
        Employee[] employeeList =  new Employee[maxEmployees];
        
        //declaing the variables we will use in the loop.
        //im not sure whether or not its best practice to declare inside a loop,
        //but Im not sure its a good idea to re-instantiate the variable every loop.
        char employeeType;
        int id;
        String name;
        String titleOrPosition; // can use this for both, saving us a tiny bit of memory.
        int salary;
        double hourlyRate;
        
        //each line is one object
        for( int i = 0; i<maxEmployees; i++){
            if(file.hasNextLine()==false){
                break;
            }
            
            employeeType = file.next().charAt(0); //comparing with a single char string was giving me strange results.
            id = file.nextInt();
            name = file.next();
            titleOrPosition = file.next();
            if (employeeType == 'S'){
                salary = file.nextInt();
                employeeList[i]= new Salaried(name, titleOrPosition, id, salary);
            }
            else{
                hourlyRate = file.nextDouble();
                employeeList[i] = new Hourly(name, titleOrPosition, id, hourlyRate);
            }
            file.nextLine();
        }
        
        //WHY CANT JAVA JUST HAVE A PRINT() FUNCTION? IT WOULD SAVE ME SO MUCH TYPING!
        System.out.println("creating salaried employee.");
        System.out.print("enter the employees name: ");
        name = scan.next();
        System.out.println();
        System.out.print("enter the employees title: ");
        titleOrPosition = scan.next();
        System.out.println();
        System.out.print("enter the employees ID number: ");
        id = scan.nextInt();
        System.out.println();
        System.out.print("enter the employees salary: ");
        salary = scan.nextInt();
        System.out.println();
        //getting how many employees we currently have to figure out where I should put it in the array
        employeeList[employeeList[0].getEmployeeCount()] =
                new Salaried(name, titleOrPosition, id, salary);
        
        System.out.println("creating hourly employee");
        System.out.print("enter the employees name: ");
        name = scan.next();
        System.out.println();
        System.out.print("enter the employees position: ");
        titleOrPosition = scan.next();
        System.out.println();
        System.out.print("enter the employees ID number: ");
        id = scan.nextInt();
        System.out.println();
        System.out.print("enter the employees hourly wage: ");
        hourlyRate = scan.nextDouble();
        System.out.println();
        employeeList[employeeList[0].getEmployeeCount()] = 
                new Hourly(name, titleOrPosition, id, salary);
        
        for (int i=0; i < maxEmployees; i++){
            //cant just let it print the null values, it throws errors if you do.
            if (employeeList[i] != null){
                System.out.println(employeeList[i].toString());
            }
            else{
                System.out.println("value is a null value");
            }
        }
        
        for (int i=0; i < maxEmployees; i++){
            if (employeeList[i] != null){
                if (employeeList[i] instanceof Salaried){
                   //this FEELS hacky, but other ways Ive tried havent worked. Is there a way to 'cast in place', so to speak?
                   Salaried s = (Salaried) employeeList[i];
                   s.setSalary((int)((double)s.getSalary()*1.1));
                   employeeList[i] = (Employee) s;
                }
                else{
                    Hourly h = (Hourly) employeeList[i];
                    h.setHourlyRate(h.getHourlyRate()*1.1);
                    employeeList[i] = (Employee) h;
                }
            }
            else{
                break;
            }
        }
        
        for (int i=0; i < maxEmployees; i++){
            if (employeeList[i] != null){
                System.out.println(employeeList[i].toString());
            }
            else{
                break;
            }
        }
        
        //its telling that I choose these names immediately, I read way to many crypto blogs!
        Employee emp1 = new Employee("bob", 1);
        Employee emp2 = new Employee("bob", 1);
        Employee emp3 = new Employee("alice", 2);
        
        System.out.println(emp1.equals(emp2)+" should be true");
        System.out.println(emp2.equals(emp3)+" should be false");
        
        Hourly hour1 = new Hourly("alice", "sysAdmin", 1, 100);
        Hourly hour2 = new Hourly("alice", "sysAdmin", 1, 100);
        Hourly hour3 = new Hourly("bob", "dev", 2, 100);
        
        System.out.println(hour1.equals(hour2)+" should be true");
        System.out.println(hour2.equals(hour3)+" should be false");
        
        Salaried sal1 = new Salaried("bob", "sysAdmin", 1, 100);
        Salaried sal2 = new Salaried("bob", "sysAdmin", 1, 100);
        Salaried sal3 = new Salaried("alice", "dev", 2, 100);
        
        System.out.println(sal1.equals(sal2)+" should be true");
        System.out.println(sal2.equals(sal3)+" should be false");
        
    }
}
