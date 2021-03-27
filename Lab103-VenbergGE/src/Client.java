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
public class Client {
    public static void main(String args[]){
        ArrayBag mensTeam = new ArrayBag(2);
        
        //I have no clue how football works, so Im just using dumb values.
        mensTeam.add(new Player("name1", "position1", 1));
        mensTeam.add(new Player("name2", "position2", 2));
        mensTeam.add(new Player("name3", "position3", 3));
        mensTeam.add(new Player("name4", "position4", 4));
        mensTeam.add(new Player("name5", "position5", 5));
        mensTeam.add(new Player("name6", "position6", 6));
        mensTeam.add(new Player("name7", "position7", 7));
        mensTeam.add(new Player("name8", "position8", 8));
        
        System.out.println(mensTeam.toString());
        
        mensTeam.remove();
        
        System.out.println(mensTeam.toString());
        
        mensTeam.add(new Player("name9", "position9", 9));
        
        System.out.println(mensTeam.toString());
        
        mensTeam.remove(new Player("name9", "position9", 9));
        
        System.out.println(mensTeam.toString());
        
        ArrayBag courses = new ArrayBag(3);
        
        courses.add("MATH 265");
        courses.add("CSCI 222");
        courses.add("CSCI 161");
        
        System.out.println(courses.toString());
        
        courses.remove();
        
        System.out.println(courses.toString());
        
        LinkedBag womensTeam = new LinkedBag();
        
        //I have no clue how football works, so Im just using dumb values.
        womensTeam.add(new Player("name1", "position1", 1));
        womensTeam.add(new Player("name2", "position2", 2));
        womensTeam.add(new Player("name3", "position3", 3));
        womensTeam.add(new Player("name4", "position4", 4));
        womensTeam.add(new Player("name5", "position5", 5));
        womensTeam.add(new Player("name6", "position6", 6));
        womensTeam.add(new Player("name7", "position7", 7));
        womensTeam.add(new Player("name8", "position8", 8));
        
        System.out.println(womensTeam.toString());
        
        womensTeam.remove();
        
        System.out.println(womensTeam.toString());
        
        womensTeam.add(new Player("name9", "position9", 9));
        
        System.out.println(womensTeam.toString());
        
        womensTeam.remove(new Player("name9", "position9", 9));
        
        System.out.println(womensTeam.toString());
    }
}
