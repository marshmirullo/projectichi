/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesociopath;

import java.util.EmptyStackException;

import java.util.EmptyStackException;

/**
 *
 * @author USER
 */
public class StackBook<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    public int getSize(){
        return list.size();
    }
    
    /*
    public int get(){
        return 
    }
    */
    
    public void push(E o){
        list.add(o);
    }
    
    public E pop(){
        if(isEmpty())
            throw new EmptyStackException();       
 
            E popped = list.get(getSize()-1);
            list.remove(popped);
        return popped;
    }   
    
    public E peek(){
        if(isEmpty())
            throw new EmptyStackException();
        
        E peeked = list.get(getSize()-1);
        return peeked;
    }
    
    public boolean isEmpty(){
        return list.isEmpty(); // arraylist library
    }
    
    public String toString(){
        String out = "Stack :\n";
        for (Object elems : list.toArray())
            out += elems + "\n";
        return out;
    }
    
    public boolean search(E o){
        return list.contains(o);
    }
}

