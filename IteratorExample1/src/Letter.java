/**
 *
 * @author latimer
 * @version 1.00    03/03/16
 * 
 * A simple Letter class that holds a single char element as a upper case String.
 * 
 */
public class Letter {
    
    String letter;
    
    public Letter() { }
    
    public Letter( String letter ) { this.letter = letter; }
    
    public Letter( char letter ) { 
        String newLetter = "" + letter; 
        this.letter = newLetter.toUpperCase();
    }
    
    public String toString( ){
        return letter;
    }
}
