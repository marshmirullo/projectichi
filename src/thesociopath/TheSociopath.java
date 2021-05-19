package thesociopath;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class TheSociopath {
    
    static String[] studentsID = {null,"Mustafa","Zhongli","Silva","Asta","Viraj","Ryujin","Ivan","Charlotte","Nafisa"};
   
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("---------------WELCOME TO THE SOCIOPATH---------------\n\n");
        System.out.print("Please enter your name for student ID: ");
        studentsID[0] = s.nextLine();
        
        Graph<String> students = new Graph<>();
        for(String a : studentsID)
            students.addVertex(a);
        Random r = new Random();
        for(int i=0;i<studentsID.length;i++){
            int a = r.nextInt(2)+1;
            ArrayList<Integer> check = new ArrayList<>();
            for(int j=0;j<a;j++){
                int b = r.nextInt(10);
                int c = r.nextInt(10)+1;
                int d = r.nextInt(10)+1;
                if(check.contains(b)||b==i)
                    continue;
                check.add(b);
                students.addEdge(studentsID[i], studentsID[b], c);
                students.addEdge(studentsID[b], studentsID[i], d);
            }
        }
        System.out.println("\nDetails of your student ID are as below: ");
        students.printSpecificEdges(studentsID[0]);
        System.out.println("\n\nPlease choose any option available to continue. Enter the respective number below: \n");
        Boolean test = true;
        while(test){
            System.out.println("1) Check current profile details.");
            System.out.println("2) Check profile of specific student ID.");
            System.out.println("3) Check profile of all students.");
            System.out.println("4) Change the old student ID to the new one.");
            System.out.println("5) Check out events available.");
            System.out.println("\n*please enter '0' to exit\n");
            System.out.print("Option: ");
            int option = s.nextInt();
            System.out.println("");
            switch(option){
                case 1:
                {
                    System.out.println("Student ID: " + studentsID[0]);
                    students.printSpecificEdges(studentsID[0]);
                    System.out.println("Option 1 completed.\n");
                    break;
                }
                case 2:
                {
                    System.out.print("Enter student ID to be checked: ");
                    s.nextLine();
                    String check = s.nextLine();
                    students.printSpecificEdges(check);
                    System.out.println("Option 2 completed.\n");
                    break;
                }
                case 3:
                {
                    System.out.println("Checking all students profile...\n");
                    students.printEdges();
                    System.out.println("Option 3 completed.\n");
                    break;
                }
                case 4:
                {
                    System.out.print("Enter the new student ID to replace the old one: ");
                    s.nextLine();
                    String newID = s.nextLine();
                    students.editVertexInfo(studentsID[0],newID);
                    studentsID[0]=newID;
                    System.out.println("Option 4 completed.\n");
                    break;
                }
                case 5:{
                    System.out.println("In construction...");
                    break;
                }
                case 0:
                {
                    test = false;
                    break;
                }
                default:
                    System.out.println("Please enter the right option number.");                
            }
            System.out.print("Do you want to continue with other option? Enter any number to continue and '0' to exit: ");
            int exit = s.nextInt();
            if(exit==0){
                test = false;
                break;
            }
        }      
    }
    
}
