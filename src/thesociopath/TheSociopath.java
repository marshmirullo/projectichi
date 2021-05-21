package thesociopath;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class TheSociopath {
    
    static String[] studentsID = {null,"Arjuna","Zhongli","Silva","Asta","Viraj","Ryujin","Ivan","Charlotte","Nafisa"};
   
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
                    System.out.println("***WELCOME TO THE EVENTS!!!***\n\n");
                    System.out.println("Events available are as below: \n");
                    System.out.println("Event 1: Teaching a stranger to solve lab question");
                    System.out.println("Event 2: Chit-Chat");
                    System.out.println("Event 3: Your road to glory(lunch with friends)");
                    System.out.println("Event 4: Arranging books");
                    System.out.println("Event 5: Meet your crush");
                    System.out.println("Event 6: Build new friendship\n");
                    System.out.print("Please choose any option available to continue. Enter respective number of the event: ");
                    int select = s.nextInt();
                    switch(select){
                        case 1:{
                            System.out.println("\n\n----------Event 1----------");
                            System.out.println("\nIn this event, you will teach a stranger about lab question. Please choose any stranger you want to be friend with by looking at the list below: ");
                            students.getNotFriendsList(studentsID[0]);
                            System.out.print("Enter student ID: ");
                            s.nextLine();
                            String name = s.nextLine();
                            System.out.println("\nFor this event, you need to answer some true/false question from your friend. In order to get good impression from your new friend, you need to get at least 3 correct answer from total of 5 questions.");
                            System.out.println("Please enter true or false for each statement.\n\n");
                            int sum=0;
                            Boolean question=false;
                            System.out.print("#Q1) Generics enable errors to be detected at compile time rather than at runtime: ");
                            question = s.nextBoolean();
                            if(question)
                                sum+=1;
                            System.out.print("\n#Q2) Stack holds data in a first-in,first-out(FIFO) style: ");
                            question = s.nextBoolean();
                            if(!question)
                                sum+=1;
                            System.out.print("\n#Q3) In queue data structure, method 'enqueue()' will delete the first element: ");
                            question = s.nextBoolean();
                            if(!question)
                                sum+=1;
                            System.out.print("\n#Q4) For graph, vertices can be represented with Array,ArrayList or Linked-list: ");
                            question = s.nextBoolean();
                            if(question)
                                sum+=1;
                            System.out.print("\n#Q5) Integer and Boolean are primitive data type while String and Array are reference data type: ");
                            question = s.nextBoolean();
                            if(question)
                                sum+=1;
                            System.out.println("\nCongratulation! You managed to help your friend with the questions. From 5 questions, you get " + sum + " correct answer!\n");
                            if(sum>=3)
                                students.event1(studentsID[0], name, 1);
                            else
                                students.event1(studentsID[0], name, 0);
                            break;
                        }
                        case 2:{
                            System.out.println("\n\n----------Event 2----------");
                            break;
                        }
                        case 3:{
                            System.out.println("\n\n----------Event 3----------");
                            System.out.println("\nIn this event, you can choose any friends or strangers to have lunch together. The details of this event are as below: \n");
                            System.out.println("*you can only have lunch with one person at one time");
                            System.out.println("*the minimum time duration spend for each lunch is 5 minutes");
                            System.out.println("*you can only have lunch with a person if you both have the same lunch period\n");
                            System.out.println("List of students with their lunch time interval: \n");                            
                            students.event3(studentsID[0]);
                            break;
                        }
                        case 4:{
                            System.out.println("----------Event 4----------");
                            break;
                        }
                        case 5:{
                            System.out.println("----------Event 5----------");
                            break;
                        }
                        case 6:{
                            System.out.println("----------Event 6----------");
                            break;
                        }
                        default:
                            System.out.println("Number entered is wrong...");
                    }
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
