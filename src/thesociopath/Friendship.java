/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesociopath;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Friendship {
    private int total = 0;
    private int size;
    private int[][] map;

    public void run(){
        Scanner sc = new Scanner(System.in);
            
        // input = number of friends you have made friend with
        System.out.print("\nEnter number of friends you have made including you: ");
        size = sc.nextInt();
        
        // create array from size input
        map = new int[size][size];
        System.out.println("\nEnter relations by line (*list of friends out of 10 including you starts from [1]): " + "\nExample: 2 4 ");
        
        int i = 0;
        while(i < size){
        try{
            //loop until relation(size) is not more than the size input
                int a = sc.nextInt();
                int b = sc.nextInt();
                map[a-1][b-1] = 1;
                map[b-1][a-1] = 1;
                i++;
            }
        
            //catch error if input entered is not a number and if the number input is one = no friends
        catch (InputMismatchException | NumberFormatException | IndexOutOfBoundsException e){
            System.out.println("Invalid input! Enter/Think again: ");
                sc.next();
                }
            }
                DfsUtil();
                System.out.println("\nTotal number of unique ways the friendship can be formed: "+(total/2));
            
        }
        
        // The function used by Dfs
        public void DfsUtil(){ 
        boolean visited[] = new boolean[size];

        for (int i = 2; i <= size; i++) {
            for (int j = 0; j < size; j++) {
                // call DFS method
                Dfs(j, visited, i, 1);
            }
        }
    }
        // The function to do DFS traversal.
        // Uses recursive
        public void Dfs(int a, boolean visited[], int targetSize, int currentSize){
        if(currentSize==targetSize){
            total++;
        }
        for (int i = 0; i < size; i++) {
            
            //to check if the subtree/node has been traverse or not
            //mark the current node as visited
            if(!visited[i] && map[a][i]==1){
                visited[a] = true;
                
                //recursively backtracking to calculate relation 
                Dfs(i, visited, targetSize, currentSize+1);
                visited[a] = false;
            }
        }
    }
}
