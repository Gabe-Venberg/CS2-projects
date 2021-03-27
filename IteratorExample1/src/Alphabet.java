
import java.util.NoSuchElementException;

/**
 *
 * @author latimer
 * @version 1.00    03/03/16
 * 
 * Example of an Iterator for the LinkedPositionalList class.
 * 
 */
public class Alphabet {
    
    private LinkedPositionalList<Letter> alphabet = null;

    //
    // Constructor builds a LinkedPositionalList of Letters.
    //    
    public Alphabet( ){
        
        alphabet = new LinkedPositionalList<>();

        String alphabetString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
        Letter nextLetter;
    
        for ( int i = 0; i < alphabetString.length(); i++ )
    
        {
            nextLetter = new Letter( alphabetString.charAt(i) );        
            alphabet.addLast( nextLetter );
        }                
    }
    
    public boolean isVowel( String s )
    {
        return ( s.equals("A") || s.equals("E") || s.equals("I") || s.equals("O") || s.equals("U") );
    }
            
    //
    // This method uses an iterator to traverse the list.
    //
    // Note that here we are using the nested iterator classes from the 
    // LinkedPositionalList class and not ones for the Alphabet class.
    //    
    @Override
    public String toString( )
    {
        String returnString = "";
               
        Iterator<Letter> listIterator = alphabet.iterator();
                        
        while ( listIterator.hasNext() )
            returnString += listIterator.next() + " ";
        
        return returnString;
    }
    
    //
    // The following classes are the nested Iterator classes from
    // Code Fragment 7.14
    //
    // Only the classes for the Position Itertor have bee included.
    //
    // These fragements have been modified so that they are specific to the 
    // Alphabet class.
    // 
    // It is necessary to put the iterator code here since we want to create
    // iterators specifically for the Alphabet class which is a concrete class
    // based on the generic ADT LinkedPositionalList.
    //  
    // Our code needs to have knowledge of Letter.
    // 
    // Generally the Generic placeholders <E> have been replaced with 
    // concret references <Letter>
    // AND
    // Call to LinkedPositionalList methods have be replaced by calls using
    // the instance reference alphabet
    // e.g.
    // private Position<Letter> cursor = first(); 
    // became
    // private Position<Letter> cursor = alphabet.first(); 
    // 
    
    //----- nested PositionIterator class -----
    private class PositionIterator implements Iterator<Position<Letter>>{
        private Position<Letter> cursor = alphabet.first();   // position of the next element to report
        private Position<Letter> recent = null;               // position of last reported element
        /** Tests whether the iterator has a next object. */
        @Override
        public boolean hasNext( ) { return ( cursor != null ); }
        /** Returns the next position in the iterator. */
        @Override
        public Position<Letter> next( ) throws NoSuchElementException {
            if ( cursor == null ) throw new NoSuchElementException( "nothing left " );
            recent = cursor;
            cursor = alphabet.after( cursor );
            return recent;
        }
        /** Removes the element returned by most recent call to next. */
        @Override
        public void remove( ) throws IllegalStateException {
            if ( recent == null ) throw new IllegalStateException( "nothing to remove" );
            alphabet.remove( recent );         // remove from outer list
            recent = null;              // do not allow remove again until next is called
        }
    } //----- end of nested PositionIterator class -----
    
    //----- nested PositionIterable class -----
    private class PositionIterable implements Iterable<Position<Letter>>{
        @Override
        public Iterator<Position<Letter>> iterator( ) { return new PositionIterator( ); }        
    } //----- end of nested PositionIterable class -----
    
    /** Returns an iterable representation of the list's positions.
     * @return  */
    public Iterable<Position<Letter>> positions( ) {
        return new PositionIterable( );  // create a new instace of the inner class
    }
    
    //
    // Below are the nested iterator classes modified to iterate over just the 
    // vowels in a the alphabet list
    //
    // Aside from taking care of name conflicts the only real modification 
    // that is necessary is in the next() method betweeen the lines:
    //      recent = cursor;
    //                        << modification goes here
    //      return recent;
    //
    // This is because cursor points to the Position that will be returned
    // the "next" time next() is called so:
    //      after saving the value of cursor to recent
    //      cursor is advanced to the next desired location
    //      -- you customize where the cursor is advanced to.
    //      then the value of recent is returned.
    //
    // You can create additional custom iterators by making additional copies
    // of the nested iterator classes, adjusting the names, and modifying 
    // how the value of cursor is updated on each call to next().
    //
    
    //----- nested PositionIterator class -----
    private class VowelPositionIterator implements Iterator<Position<Letter>>{
        private Position<Letter> cursor = alphabet.first();   // position of the next element to report
        private Position<Letter> recent = null;               // position of last reported element
        /** Tests whether the iterator has a next object. */
        @Override
        public boolean hasNext( ) { return ( cursor != null ); }
        /** Returns the next position in the iterator. */
        @Override
        public Position<Letter> next( ) throws NoSuchElementException {
            // On the first call to next (i.e. when recent == null) you need to      //<<< new code
            // advance recent until it is pointing to a vowel element.                //<<< new code
            if ( recent == null )                                                    //<<< new code 
            {                                                                        //<<< new code
                while ( cursor != null && !isVowel( cursor.getElement().letter) )    //<<< new code
                    cursor = alphabet.after( cursor );                               //<<< new code
            }                                                                        //<<< new code
                
            if ( cursor == null ) throw new NoSuchElementException( "nothing left " );
            recent = cursor;
            cursor = alphabet.after( cursor );
            
            // advance cursor to the next vowel
            
            while ( cursor != null && !isVowel( cursor.getElement().letter) )
                cursor = alphabet.after( cursor );
            
            return recent;
        }
        /** Removes the element returned by most recent call to next. */
        @Override
        public void remove( ) throws IllegalStateException {
            if ( recent == null ) throw new IllegalStateException( "nothing to remove" );
            alphabet.remove( recent );         // remove from outer list
            recent = null;              // do not allow remove again until next is called
        }
    } //----- end of nested PositionIterator class -----
    
    //----- nested PositionIterable class -----
    private class VowelPositionIterable implements Iterable<Position<Letter>>{
        @Override
        public Iterator<Position<Letter>> iterator( ) { return new VowelPositionIterator( ); }        
    } //----- end of nested PositionIterable class -----
    
    /** Returns an iterable representation of the list's positions.
     * @return  */
    public Iterable<Position<Letter>> vowelPositions( ) {
        return new VowelPositionIterable( );  // create a new instace of the inner class
    }
    
}
