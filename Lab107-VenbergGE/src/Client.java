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
public class Client {
	public static void main(String[] args){
		LinkedBinaryTree<String> expression = new LinkedBinaryTree<String>();

		expression.addRoot("+");
		LinkedBinaryTree tmp = new LinkedBinaryTree();
		LinkedBinaryTree tmp2 = new LinkedBinaryTree();
		tmp.addRoot("+");
		tmp.addLeft(tmp.root(), "2");
		tmp.addRight(tmp.root(), "9");
		
		tmp2.addRoot("-");
		tmp2.addLeft(tmp2.root(), "7");
		tmp2.addRight(tmp2.root(), "*");
		tmp2.addLeft(tmp2.right(tmp2.root()), "3");
		tmp2.addRight(tmp2.right(tmp2.root()), "8");
		
		expression.attach(expression.root(), tmp, tmp2);
		
		System.out.println("literal expression is: (   2   +   9   )   +   (   7   -   (   3    *   8   )   )");
		
		System.out.println("hight is: "+expression.hight(expression.root()));
		
		System.out.println("preorder is:");
		for(Position s:expression.preorder()){
			System.out.print(s.getElement());
		}
		
		System.out.println();
		System.out.println("inorder is:");
		for(Position s:expression.inorder()){
			System.out.print(s.getElement());
		}
		
		System.out.println();
		System.out.println("postorder is:");
		for(Position s:expression.postorder()){
			System.out.print(s.getElement());
		}
		
		System.out.println();
		System.out.println("breadth first is:");
		for(Position s:expression.breadthFirst()){
			System.out.print(s.getElement());
		}
		
		System.out.println();
		System.out.println("parenthasized representation:");
		eulerTourPrint(expression, expression.root());

	}
	
	//utility class
	private static void eulerTourPrint(AbstractBinaryTree t, Position p){
		if(t.isInternal(p)){System.out.print('(');}
		if(t.left(p)!=null){eulerTourPrint(t, t.left(p));}
		System.out.print(p.getElement());
		if(t.right(p)!=null){eulerTourPrint(t, t.right(p));}
		if(t.isInternal(p)){System.out.print(')');}
	}
}
