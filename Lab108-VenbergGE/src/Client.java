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
 *
 * @author Gabriel Venberg
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import java.util.Scanner;
public class Client {
    public static void main(String[] args){
        String filePath = (String) JOptionPane.showInputDialog(null, "Enter the path of the file");
        File file = new File(filePath);
        try {
            Scanner fileContents = new Scanner(file);
            
            while(fileContents.hasNextLine()){
                
                String expression = fileContents.nextLine();
                System.out.println("input expression is: "+expression);
                try{
                    ArrayQueue postFix = ShuntingYard.shuntingYard(expression);
                    
                    //computing the result ahead of time to catch exceptions before the postfix is printed.
                    double result = ShuntingYard.evalPostfix(postFix);
                    
                    System.out.println("postfix is: ");
                    //non-destrutive printing
                    for(int i=0; i<postFix.size(); i++){
                        System.out.print(postFix.first().toString()+" ");
                        postFix.enqueue(postFix.dequeue());
                    }
                    System.out.println();
                    
                    System.out.println("expression evaluates to: "+result);
                    System.out.println();
                    
                    LinkedBinaryTree<String> expressionTree = ShuntingYard.convertToBinaryTree(postFix);
                    System.out.println("preorder of tree is:");
                    for(Position s:expressionTree.preorder()){
			System.out.print(s.getElement());
                    }
                    System.out.println();
                    
                    System.out.println("postorder of tree is:");
                    for(Position s:expressionTree.postorder()){
			System.out.print(s.getElement());
                    }
                    System.out.println();
                    
                    System.out.println("inorder of tree is:");
                    for(Position s:expressionTree.inorder()){
			System.out.print(s.getElement());
                    }
                    System.out.println();
                    
                    System.out.println("parenthasized expression is:");
                    eulerTourPrint(expressionTree, expressionTree.root());
                    System.out.println("\n\n");
                }
                catch(IllegalArgumentException ex){
                    System.out.println("invalid expression!");
                }
                catch(NullPointerException ex){
                    System.out.println("invalid expression!");
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "file does not exist.");
            System.exit(1);
        }
    }
    	private static void eulerTourPrint(AbstractBinaryTree t, Position p){
		if(t.isInternal(p)){System.out.print('(');}
		if(t.left(p)!=null){eulerTourPrint(t, t.left(p));}
		System.out.print(p.getElement());
		if(t.right(p)!=null){eulerTourPrint(t, t.right(p));}
		if(t.isInternal(p)){System.out.print(')');}
	}
}
