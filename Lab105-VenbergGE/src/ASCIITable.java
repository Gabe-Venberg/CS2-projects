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
 *utility library for nicely formatted ascii tables.
 * @author Gabriel Venberg
 */
public class ASCIITable {
    
    /**
     * render 2D array data into a table. the top level array is an array of rows.
     * @param data 2D array of data. Must have toString.
     * @param padding space padding on either side of data.
     * @return String containing table.
     */
    public static String render(Object data[][], int padding){
        int colWidth = calcColumnWidth(data);
        int rows = calcNoRows(data);
        String horizontalSpacer = assembleHorizontalSpacers(colWidth, padding, rows);
        /*ok, so each cell will have the colwidth for the data, then padding for padding,
        * then a | at the end. (plus 1 at the begginning of the table.
        there will be 2 rows for each row of data (horizontal sep) plus a horizontal sep
        at the end.
        */
        String string = "";
        //got everything set up, build the table row by row.
        for(int i=0; i<data.length; i++){
            string = string+horizontalSpacer+"\n";
            string = string+dataString(colWidth, padding, data[i])+'\n';
        }
        string = string+horizontalSpacer;
        return string;
    }
    
    /**
     * calculates the maximum number of entries the rows in the data set have
     * @param data 2D array of data
     * @return needed number of rows in the final table.
     */
    private static int calcNoRows(Object data[][]){
        int rows = 0;
        for(int i=0; i<data.length; i++){
            rows = Math.max(rows, data[i].length);
        }
        return rows;
    }
    /**
     * calculates the needed column width for a data array without padding
     * @param data the array of data
     * @return an integer representing the needed width of the column
     */
    private static int calcColumnWidth(Object data[][]){
        int maxWidth = 0;
        for(int i=0; i<data.length; i++){
            for(int j=0; j<data[i].length; j++){
                maxWidth = Math.max(maxWidth, data[i][j].toString().length());
            }
        }
        return maxWidth;
    }
    
    /**
     * gives the horizontal spacer needed for the table
     * @param colWidth width of each column;
     * @param padding padding on each side of data.
     * @param noOfCols number of columns;
     * @return a string suitable to use as the horizontal spacer for the table.
     */
    private static String assembleHorizontalSpacers(int colWidth, int padding, int noOfCols){
        String string = "+";
        for(int i=0; i<noOfCols; i++){
            for(int j=0; j<colWidth+2*padding; j++){
                string = string+'-';
            }
            string = string+'+';
        }
        return string;
    }
    
    /**
     * takes a single row of the data array and returns a row. Make sure your colWidth is accurate.
     * @param colWidth width of each column
     * @param data 1D array of data to print
     * @return a string containing the data
     */
    private static String dataString(int colWidth, int padding, Object data[]){
        String string ="|";
        //for each entry in the row
        for(int i=0; i<data.length; i++){
            //only calc this once.
            int length=data[i].toString().length();
            // front padding. Also, I wish java had string multiplication.
            for(int p=0; p<padding+(colWidth-length); p++){string = string+" ";}
            string = string+data[i].toString();
            for(int p=0; p<padding; p++){string = string+" ";}
            string = string+"|";
        }
        return string;
    }
}
