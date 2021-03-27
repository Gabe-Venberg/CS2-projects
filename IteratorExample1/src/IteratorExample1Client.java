
/**
 *
 * @author latimer
 * @version 1.00    03/03/16
 * 
 * Example of an Iterator for the LinkedPositionalList class.
 * 
 */
public class IteratorExample1Client {


    public static void main(String[] args) {
        
        //
        // Create an instance of the Alphabet class which is
        // just a LinkedPositiionalList of Letters.
        //

        Alphabet alphabet = new Alphabet();
        
        //
        // Look at the toString( ) method in the Alphabet class.
        // This method uses a iterator to move across the list
        //
        
        System.out.println( "Using toString() alphabet =         " + alphabet.toString() );
        System.out.print("\n");
        
        //
        // Let's create an Iterator for the LinkedPositionalList
        //
        // Decision: Do you want a PositionInterator or an ElementIterator?
        //
        // Let's pick the PositionIterator.
        //
        // Since the PositionIterator class is a private class nested in 
        // the LinkedPositionalList class we cannot call the class methods 
        // directly.
        //
        // The PositionalIterable class is also a private class nested in 
        // the LinkedPositionalList class that we cannot call the class methods 
        // directly.
        //
        // Fortunately (or perhaps by design) the Iterable class is a public
        // class nexted in LinkedPositionalList class so we can call its 
        // class methods directly.
        //
        // This class has one method "positions()" that generally 
        // returns an object of type IterablePositions<E>
        // and in our specific case
        // returns an object of type IterablePostions<Letter>
        //
        // The object returned by "positions()" gives us access to the 
        // nested private class PositionIterable class that returns an
        // PositionIterator object.
        //
        // The PositionIterator object is the iterator.
        //
                               
        Iterator<Position<Letter>> letterListIterator = alphabet.positions().iterator();
        
        System.out.print("Using letterListIterator alphabet = ");
        while ( letterListIterator.hasNext() )
            System.out.print( letterListIterator.next().getElement().toString() + " " );
        System.out.print( "\n\n" );
     
        //
        // This seems like a lot of work to traverse a list but provided we have
        // access to the Iterator classes we can build an iterator to traverse 
        // the list without any knowledge of how the list is implemented.
        //
        // Also we have the opportunity to build customized iterators that 
        // allow us the specify how we will traverse the list.
        //
        // For example we may want to build an iterator that moves accross all 
        // of the vowels in the alphabet.
        //
        // To do this we create a nested interator class inside of our
        // Alphabet class.
        // 

        Iterator<Position<Letter>> vowelListIterator = alphabet.vowelPositions().iterator();
        
        System.out.print("Using VowelListIterator alphabet =  ");
        while ( vowelListIterator.hasNext() )
            System.out.print( vowelListIterator.next().getElement().toString() + " " );
        System.out.print( "\n\n" );
               
    }    
}
