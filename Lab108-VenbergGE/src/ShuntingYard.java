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
 * a collection of static functions to parse expressions based on the shunting yard algorithm.
 * @author Gabriel Venberg
 */
import java.util.ArrayList;
public class ShuntingYard {
    /*
    change if-else-if to catch block in shunting yard
    keep things as strings for the whole process, assume if its not an operator or grouping symbol it must be a number (removes seperate validation logic)
    add order of operations logic, dont asusme it will be fully parenthisized
    validate parenthases using the shunting yard algo insead if seperate algo.
    */
//    /**
//     * returns a number based on the operator precedence.
//     * @param c string containing the operator.
//     * @return number representing the precedence of the operator.
//     */
//    private static int getPrecedence(String c){
//        
//    }
    /**
     * converts a space-delimited infix expression represented as a string to an arrayqueue postfix expression.
     * @param expression expression to be evaluated
     * @return  ArrayQueue of strings
     * @throws IllegalArgumentException 
     */
    public static ArrayQueue shuntingYard(String expression) throws IllegalArgumentException {
        ArrayQueue<String> output = new ArrayQueue<>();
        
        
        ArrayStack<String> stack = new ArrayStack<>();
        
        //split into string array based on whitespace.
        String[] tokens = expression.split("\s");
        
        for(int i=0; i<tokens.length; i++){
            switch(tokens[i]){
                case "(":
                case "[":
                case "{":
                    stack.push(tokens[i]);
                    break;
                case ")":
                    //pop untill we find a maching bracket
                    while(!stack.top().equals("(")&&!stack.isEmpty()){
                        //if we encounter a different grouping symbol or run out of expression, then the grouping is wrong.
                        if(stack.top().equals("[")||stack.top().equals("{")||stack.isEmpty()){throw new IllegalArgumentException("invalid expression: mismached grouping");}
                        output.enqueue(stack.pop());
                    }
                    stack.pop();
                    break;
                case "]":
                    //pop untill we find a maching bracket
                    while(!stack.top().equals("[")&&!stack.isEmpty()){
                        //if we encounter a different grouping symbol or run out of expression, then the grouping is wrong.
                        if(stack.top().equals("{")||stack.top().equals("(")||stack.isEmpty()){throw new IllegalArgumentException("invalid expression: mismached grouping");}
                        output.enqueue(stack.pop());
                    }
                    stack.pop();
                    break;
                case "}":
                    //pop untill we find a maching bracket
                    while(!stack.top().equals("{")&&!stack.isEmpty()){
                        //if we encounter a different grouping symbol or run out of expression, then the grouping is wrong.
                        if(stack.top().equals("[")||stack.top().equals("(")||stack.isEmpty()){throw new IllegalArgumentException("invalid expression: mismached grouping");}
                        output.enqueue(stack.pop());
                    }
                    stack.pop();
                    break;
                case "+":
                case "-":
                    //while there is an operator with greater precedence
                    while(!stack.isEmpty()&&(stack.top().equals("*")||stack.top().equals("/"))){
                        output.enqueue(stack.pop());
                    }
                    stack.push(tokens[i]);
                    break;
                case "*":
                case "/":
                    //there are no greater precedence operators
                    stack.push(tokens[i]);
                    break;
                //must be a number
                default:
                    output.enqueue(tokens[i]);
                    break;
            }
        }
        
        //empty the stack of leftovers.
        while(!stack.isEmpty()){
            output.enqueue(stack.pop());
        }
        return output;
    }
    
    /**
     * evaluates an array queue based postfix expression
     * @param inputExpression ArrayQueue of strings containing expression.
     * @return double containing the result of the expression.
     */
    public static double evalPostfix(ArrayQueue<String> inputExpression) throws IllegalArgumentException {
        //this took me a while to figure out. methods were consuming the queue...
        ArrayQueue<String> expression = new ArrayQueue();
        for(int i=0; i<inputExpression.size(); i++){
            expression.enqueue(inputExpression.first());
            inputExpression.enqueue(inputExpression.dequeue());
        }
        
        ArrayStack<Double> stack = new ArrayStack<>();
        while(!expression.isEmpty()){
            //compiler doesnt like declaring these inside switch statement.
            double right;
            double left;
            switch(expression.first()){
                case "-":
                    expression.dequeue();
                    right=stack.pop();
                    left=stack.pop();
                    stack.push(left-right);
                    break;
                case "+":
                    expression.dequeue();
                    right=stack.pop();
                    left=stack.pop();
                    stack.push(left+right);
                    break;
                case "*":
                    expression.dequeue();
                    right=stack.pop();
                    left=stack.pop();
                    stack.push(left*right);
                    break;
                case "/":
                    expression.dequeue();
                    right=stack.pop();
                    left=stack.pop();
                    stack.push(left/right);
                    break;
                //must be number otherwise.
                default:
                    stack.push(Double.parseDouble(expression.dequeue()));
                    break;
            }
        }
        //at the end of this, there should be a single number.
        if(stack.size()!=1){
            String stackContents="";
            while(!stack.isEmpty()){
                stackContents=stackContents+stack.pop();
            }
            throw new IllegalArgumentException("stack does not have awnser! bug in evalPostFix! stack contains: "+stackContents);
        }
        return stack.pop();
    }
    
    /**
     * converts an arrayQueue infix expression to a linked binary tree output expression.
     * @param expression ArrayQueue containing expression. operators must be chars, numbers must be doubles.
     * @return linked binary tree representing the expression. operators are chars, numbers are doubles.
     */
    public static LinkedBinaryTree<String> convertToBinaryTree(ArrayQueue<String> inputExpression){
        //this took me a while to figure out. methods were consuming the queue...
        ArrayQueue<String> expression = new ArrayQueue<>();
        for(int i=0; i<inputExpression.size(); i++){
            expression.enqueue(inputExpression.first());
            inputExpression.enqueue(inputExpression.dequeue());
        }
        
        ArrayStack<LinkedBinaryTree<String>> stack = new ArrayStack<>();
        while(!expression.isEmpty()){
            //check if operator
            switch(expression.first()){
                case "+":
                case "-":
                case "*":
                case "/":
                    LinkedBinaryTree<String> newOperatorTree=new LinkedBinaryTree<>();
                    newOperatorTree.addRoot(expression.dequeue());
                    //need temp variables, as attach want new left child as first argument, but top of stack is new right child here.
                    LinkedBinaryTree newRight=stack.pop();
                    LinkedBinaryTree newLeft=stack.pop();
                    newOperatorTree.attach(newOperatorTree.root(), newLeft, newRight);
                    stack.push(newOperatorTree);
                    break;

                //must be number
                default:
                    LinkedBinaryTree<String> newNumberTree=new LinkedBinaryTree<>();
                    newNumberTree.addRoot(expression.dequeue());
                    stack.push(newNumberTree);
                    break;
            }
        }
        //after the loop, should be the completed tree as only value on stack.
        return stack.pop();
    }
    
}
