
public class Client {

    public static void main(String[] args) {
       
//        example1();
        
//        example2();

        example3();

//        example4();
        
//        example5();

//        example6();
    }

    public static void example1()
    {
        int num = 5;
        
        System.out.println("Factiorial( " + num + " ) = " + Recursion.factorial1( num ) );
    }

    public static void example2( )
    {
        int num = 5;
        
        Recursion.factorial2( num , 0 );
        
    }


    
    public static void example3( )
    {
        for ( int i = 0; i < 14; i++ )
        {
            System.out.printf("Factiorial( %2d ) = %,15d\n", i, Recursion.factorial1( i ) );
        }    
    }
    
    public static void example4( )
    {
        for ( int i = 0; i < 20; i++ )
        {
            System.out.printf("Factiorial( %2d ) = %,15d\n", i, Recursion.factorial1( i ) );
        }    
    } 
    
    public static void example5( )
    {
        for ( long i = 0; i < 25; i++ )
        {
            System.out.printf("FactiorialL( %2d ) = %,30d\n", i, Recursion.factorialL( i ) );
        }    
    }     


    public static void example6( )
    {
        for ( long i = 0; i < 100000; i++ )
        {
            System.out.printf("FactiorialL( %2d ) = %,30d\n", i, Recursion.factorialL( i ) );
        }    
    }         
}
