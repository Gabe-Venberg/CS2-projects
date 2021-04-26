/*
 * Data Structures & Algorithms 6th Edition 
 * Goodrich, Tamassia, Goldwasser
 * Code Fragments 9.1
 * 
 * An implementation of the Entry Interface
 * */

/**
 *
 * @author Gabriel Venberg
 */
public interface Entry <K,V>{
    K getKey();
    V getValue();
}
