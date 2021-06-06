/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesociopath;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Friendship {
    static int total = 0;
    static int size;
    static int[][] graph;

    public Friendship() {
    }

        public void run(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of Friendship Relations: ");
        size = sc.nextInt();

        graph = new int[size][size];
        System.out.println("Enter relations by line: *The number you enter represents the student");
        for (int i = 0; i < size; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x-1][y-1] = 1;
            graph[y-1][x-1] = 1;
        }
        modifiedDfs();
            System.out.println("\nTotal number of unique ways the friendship can be formed: "+(total/2));
    }

        public void modifiedDfsUtil(int v, boolean visited[], int targetSize, int currentSize){
        if(currentSize==targetSize){
            total++;
            return;
        }
        for (int i = 0; i < size; i++) {
            if(!visited[i] && graph[v][i]==1){
                visited[v] = true;
                modifiedDfsUtil(i, visited, targetSize, currentSize+1);
                visited[v] = false;
            }
        }
    }

        public void modifiedDfs(){
        boolean visited[] = new boolean[size];
        for (int i = 2; i <=size; i++) {
            for (int j = 0; j < size; j++) {
                modifiedDfsUtil(j, visited, i, 1);
            }
        }
    }
}
