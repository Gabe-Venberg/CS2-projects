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
 * utility library for nicely formatted ascii tables.
 * @author Gabriel Venberg
 */
public class ASCIITable {
    
    /**
     * generates an ASCII table based on a 2d data array. the top level array is an array of rows.
     * @param data 2d array containing data to put in table
     * @param padding how much padding to put on each side of entries
     * @param tableHeader string to put in the table header (may cause problems if extremely long)
     * @param columnHeaders array of strings to put at the top of each column.
     * @return 
     */
    public static String render(Object data[][], int padding, String tableHeader, String[] columnHeaders) throws IllegalArgumentException {
        int cols = calcNoCols(data);
        if(cols!=columnHeaders.length){throw new IllegalArgumentException("must have equal number of column headers as columns!");}
        int[] colWidths = calcColumnWidth(cols, data, columnHeaders);
        //colWidths does not count padding or the | chars betwwen tables.
        int width = sumOfArray(colWidths)+padding*cols*2+(cols-1);
        String horizontalSpacer = assembleHorizontalSpacers(colWidths, padding, cols);
        /*ok, so each cell will have the colwidth for the data, then padding for padding,
        * then a | at the end. (plus 1 at the begginning of the table.
        there will be 2 rows for each row of data (horizontal sep) plus a horizontal sep
        at the end.
        */
        String string = horizontalSpacer+'\n';
        //print table header
        string=string+tableHeader(tableHeader, width)+"\n";
        string = string+horizontalSpacer+"\n";
        //print coumn headers
        string=string+columnHeaderString(colWidths, padding, columnHeaders)+'\n';
        //got everything set up, build the table row by row.
        for(int i=0; i<data.length; i++){
            string = string+horizontalSpacer+"\n";
            string = string+dataString(colWidths, padding, data[i])+'\n';
        }
        string = string+horizontalSpacer;
        return string;
    }
    
    private static String tableHeader(String header, int width){
        String string="|";
        int halfPadding=(width-header.length())/2;
        //front padding
        for(int i=0; i<halfPadding; i++){string=string+" ";}
        //if the total padding we need is odd, put it in front of the header
        if((width-header.length())%2==1){string=string+" ";}
        string=string+header;
        //rear padding
        for(int i=0; i<halfPadding; i++){string=string+" ";}
        string=string+"|";
        return string;
    }
    
    /**
     * calcs the sum of all elements in an int array
     * @param array array to be summed
     * @return sum of array
     */
    private static int sumOfArray(int[] array){
        int sum=0;
        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum;
    }
    
    /**
     * calculates the maximum number of entries the rows in the data set have
     * @param data 2D array of data
     * @return needed number of rows in the final table.
     */
    private static int calcNoCols(Object data[][]){
        int rows = 0;
        for(int i=0; i<data.length; i++){
            rows = Math.max(rows, data[i].length);
        }
        return rows;
    }
    
    /**
     * calculates the needed column widths for a data array without padding
     * @param data the array of data
     * @return an array of integers representing the needed width of each column
     */
    private static int[] calcColumnWidth(int cols, Object data[][], String[] headers){
        int[] maxWidths = new int[cols];
        for(int i=0; i<cols; i++){
            maxWidths[i]=headers[i].length();
            for(int j=0; j<data.length; j++){
                maxWidths[i]=Math.max(maxWidths[i], data[j][i].toString().length());
            }
        }
        
        return maxWidths;
    }
    
    /**
     * gives the horizontal spacer needed for the table
     * @param colWidth width of each column;
     * @param padding padding on each side of data.
     * @param noOfCols number of columns;
     * @return a string suitable to use as the horizontal spacer for the table.
     */
    private static String assembleHorizontalSpacers(int[] colWidth, int padding, int noOfCols){
        String string = "+";
        for(int i=0; i<noOfCols; i++){
            for(int j=0; j<colWidth[i]+2*padding; j++){
                string = string+'-';
            }
            string = string+'+';
        }
        return string;
    }
    
    /**
     * takes a single row of the data array and returns a row. Make sure your colWidth is accurate.
     * @param colWidth width of each column
     * @param padding min padding to have around each entry
     * @param data 1D array of data to print
     * @return a string containing the data
     */
    private static String dataString(int[] colWidth, int padding, Object data[]){
        String string ="|";
        //for each entry in the row
        for(int i=0; i<data.length; i++){
            //only calc this once.
            int length=data[i].toString().length();
            // front padding. Also, I wish java had string multiplication.
            for(int p=0; p<padding+(colWidth[i]-length); p++){string = string+" ";}
            string = string+data[i].toString();
            //rear padding
            for(int p=0; p<padding; p++){string = string+" ";}
            string = string+"|";
        }
        return string;
    }
    
    /**
     * takes an array of strings (column headers) and outputs a single row of the column, center justified.
     * @param colWidth width of each column
     * @param padding min padding around each entry
     * @param columnHeaders 1d array of strings containing col headers
     * @return 
     */
    private static String columnHeaderString(int[] colWidth, int padding, String columnHeaders[]){
        String string="|";
        for(int i=0; i<columnHeaders.length; i++){
            //calc this once.
            int length=columnHeaders[i].length();
            int sidePadding=(colWidth[i]-length+padding*2)/2;
            //front padding
            for(int p=0; p<sidePadding; p++){string=string+" ";}
            //if we need an odd number of total padding, add the spare on the front
            if((colWidth[i]-length)%2==1){string=string+" ";}
            string=string+columnHeaders[i];
            //rear padding
            for(int p=0; p<sidePadding; p++){string=string+" ";}
            string=string+"|";
        }
        return string;
    }
}
