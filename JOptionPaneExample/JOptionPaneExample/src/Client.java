import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) {
        
        showInputDialogExample();
        
        showInputDialogMenuExample();
        
        showMessageDialogExample();
        
        showConfirmDialogExample();
    
    }
    
    public static void showInputDialogExample( )
    {
        String response; 
                
        response = JOptionPane.showInputDialog( null, "My Question", "My Title", 0 );
        
        System.out.println("respone = " + response );
    }    


    public static void showInputDialogMenuExample()
    {            
        String response;
        
        String optionString = "A to run method A\nB to run method B\nC to exit";
        
        response = JOptionPane.showInputDialog( null, optionString, "My Title", 0 );        
        
        switch ( response ) 
        {
            case "a" :
            case "A" :
                System.out.println("Calling method A");    
                break;
            case "b" :
            case "B" :
                System.out.println("Calling method B");    
                break;
            case "c" :
            case "C" :    
                System.out.println("Setting sential flag to exit loop");
            default :
                System.out.println("Should not be able to get here!!!");
        }
    }    
    
    public static void showMessageDialogExample()
    {        
        String response = "George";
    
        JOptionPane.showMessageDialog(null, null, response, 0);        
    }
    
    public static void showConfirmDialogExample()
    {            
        String response = "Are you sure your name is George?";
        
        int reply = JOptionPane.showConfirmDialog(null, null, response, 0);
        
        System.out.println( reply );    
    }
    
}
