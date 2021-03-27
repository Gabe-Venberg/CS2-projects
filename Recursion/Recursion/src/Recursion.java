
public class Recursion {

    public static int factorial1( int i )
    {
        if ( i < 1 )
            return 1;
        else
            return i * factorial1( i - 1 );
    }
    
    public static int factorial2( int i, int level )
    {
        System.out.println( indent(level) + ">>> factorial( " + i + " )" );
        if ( i < 1 )
        {
            System.out.println( indent(level) + "<<< 1 (the base case)" );
            return 1;
        }
        else
        {   
            int returnValue = factorial2( i - 1, level + 1 );
            int answer = i * returnValue;
            System.out.println( indent(level) + "<<< " + answer + " = " + i + " * " + returnValue );
            return i * returnValue;
        }
    }
    
    public static String indent( int level )
    {
        String returnString = "";
        for ( int i = 0; i < level; i++ )
            returnString += "  ";
        return returnString;
    }
    
    public static long factorialL( long i )
    {
        if ( i < 1 )
            return 1;
        else
        {
            return i * factorialL( i - 1 );
        }
    }    
}
