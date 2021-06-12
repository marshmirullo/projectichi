
package thesociopath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class TheSociopath {
    
    static String[] studentsID = {null,"Arjuna","Scarlett","Silva","Asta","Alexa","Ryujin","Ivan","Charlotte","Nafisa"};
   
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("---------------WELCOME TO THE SOCIOPATH---------------\n\n");

        
        Login login = new Login();
        login.setVisible(true);
        
        System.out.print("\nEnter 0 to continue: ");
        while(true){
            int a = s.nextInt();
            if(a==0)
                break;
        }
        
        studentsID[0] = login.getName();
               
        Graph<String,Integer> students = new Graph<>();
        for(String a : studentsID)
            students.addVertex(a);      //adding all of students ID as vertex of the graph
        Random r = new Random();
        for(int i=0;i<studentsID.length;i++){
            int b;
            while(true){
                b = r.nextInt(10);
                if(b!=i)
                    break;
            }
            int c = r.nextInt(10)+1;
            int d = r.nextInt(10)+1;            
            students.addEdge(studentsID[i], studentsID[b], c);      //add new edge of two students
            students.addEdge(studentsID[b], studentsID[i], d);      //two times because the edge is two-way interaction                   
        }
        System.out.println("\nDetails of your student ID are as below: ");
        students.printSpecificEdges(studentsID[0]);
        System.out.println("\n\nPlease choose any option available to continue. Enter the respective number below: \n");
        Boolean test = true;
        while(test){
            System.out.println("1) Check current profile details.");
            System.out.println("2) Check profile of all students.");
            System.out.println("3) Check details of all students.");
            System.out.println("4) Change the old student ID to the new one.");
            System.out.println("5) Check out events available.");
            System.out.println("\n*please enter '0' to exit\n");
            System.out.print("Option: ");
            int option = s.nextInt();
            System.out.println("");
            switch(option){
                case 1:         //check the user current profile details: reputation point, lunch time, etc.
                { 
                    System.out.println("Student ID: " + studentsID[0]);
                    students.printSpecificEdges(studentsID[0]);
                    System.out.println("Option 1 completed.\n");
                    break;
                }
                case 2:         //check the profile of all students: birthday, nationality, etc.
                {           
                    
                   break;
                }
                case 3:         //displaying all students profile
                {
                    System.out.println("Checking all students profile...\n");
                    students.printEdges();
                    System.out.println("\nOption 3 completed.\n");
                    break;
                }
                case 4:        //change students ID of user to a new one
                {
                    System.out.print("Enter the new student ID to replace the old one: ");
                    s.nextLine();
                    String newID = s.nextLine();
                    students.editVertexInfo(studentsID[0],newID);
                    studentsID[0]=newID;
                    System.out.println("\nOption 4 completed.\n");
                    break;
                }
                case 5:{        //move to events section
                    System.out.println("\n\n***WELCOME TO THE EVENTS!!!***\n\n");
                    System.out.println("Events available are as below: \n");
                    System.out.println("Event 1: Teaching a stranger to solve lab question");
                    System.out.println("Event 2: Chit-Chat");
                    System.out.println("Event 3: Lunch with friends");
                    System.out.println("Event 4: Arranging books");
                    System.out.println("Event 5: Meet your crush");
                    System.out.println("Event 6: Build new friendship\n");
                    System.out.print("Please choose any option available to continue. Enter respective number of the event: ");
                    int select = s.nextInt();
                    switch(select){
                        case 1:{
                            System.out.println("\n\n----------Event 1----------");
                            System.out.println("\nIn this event, you will teach a stranger about lab questions. You need to answer some true/false question from your friend.\n");
                            System.out.println("*please enter true or false for each statement");
                            System.out.println("*there are 5 question in total");
                            System.out.println("*to get good impression from your new friend, you need to get at least 3 correct answer");
                            System.out.println("*at least 3/5 = 10 reputation point");
                            System.out.println("*2/5 and below = 2 reputation point\n\n");
                            students.event1(studentsID[0]);         //call for event 1 method in Graph class
                            System.out.println("\n\nEvent 1 completed!\n\n");
                            break;
                        }
                        case 2:{
                            int oldRep = students.getRep(studentsID[0]);
                            System.out.println("\n\n----------Event 2----------");
                            System.out.println("\nIn this event, everyone will have a chit-chatting session with their friends. The details of this event are as below: \n");
                            System.out.println("*please be aware that your friends chit-chatting session will affect your reputation points");
                            System.out.println("*your reputation point is not the only one that will be affected, all the reputation point of the students will be manipulated in this event");
                            System.out.println("*if the chit-chatting session is something good about you: your reputation point relative to new friend will be 50% of your old friend");
                            System.out.println("*if the chit-chatting session is something bad about you: your reputation point relative to new friend will be -100% of your old friend");
                            students.event2(studentsID[0]);         //call for event 2 method in Graph class
                            //checking whether user's reputation point is increasing or decreasing
                            if(students.getRep(studentsID[0])<oldRep)
                                System.out.println("\nOh no! Your reputation point is decreased. There must be some students who talk something bad about you.");
                            if(students.getRep(studentsID[0])>oldRep)
                                System.out.println("\nYour reputation point is increased! There must be some students who talk something good about you. This is a good thing for you.");
                            System.out.println("\n\nEvent 2 completed!\n\n");
                            break;
                        }
                        case 3:{
                            System.out.println("\n\n----------Event 3----------");
                            System.out.println("\nIn this event, you can choose any friends or strangers to have lunch together. The details of this event are as below: \n");
                            System.out.println("*the place you choose will determine number of students you will have lunch with");
                            System.out.println("*the minimum time duration spend for each lunch is 5 minutes");
                            System.out.println("*you can only have lunch with a person if you both have the same lunch period\n");
                            System.out.println("List of students with their lunch time interval: \n");                            
                            students.event3(studentsID[0]);         //call for method event 3 in Graph class
                            System.out.println("\n\nEvent 3 completed!\n\n");
                            break;
                        }
                        case 4:{
                            System.out.println("\n\n----------Event 4----------");
                            StackBook arrangeBook = new StackBook();        //create new object of class StackBook
                            arrangeBook.run();      //call method run in class StackBook
                            System.out.println("\n\nEvent 4 completed!\n\n");
                            break;
                        }
                        case 5:{
                            System.out.println("\n\n----------Event 5----------");
                            students.event5(studentsID[0]);         //call for event 5 method in Graph class
                            System.out.println("\n\nEvent 5 completed!\n\n");
                            break;
                        }
                        case 6:{
                            Friendship friendRelation = new Friendship();       //create new object of class Friendship
                            System.out.println("\n\n----------Event 6----------");
                            System.out.println("\n");
                            students.printSpecificEdges(studentsID[0]);
                            System.out.println("\nCongratulations for your graduations!\nNow let see how many friends you have made throughout the journey.");
                            System.out.println("\n---List of friends(including you)----");
                            System.out.println(Arrays.toString(studentsID));
                            friendRelation.run();       //call for method run in Friendship class 
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
                    System.out.println("\nPlease enter the right option number.");                
            }
            System.out.print("\nDo you want to continue with other option? Enter any number to continue and '0' to exit: ");
            int exit = s.nextInt();
            if(exit==0){
                test = false;
                System.out.println("\nBefore we end this program, we will show you diving rate(in percentage) for all students: \n");
                for(int i=1;i<=10;i++){
                    System.out.println(i + ") " + studentsID[i-1] + " : " + students.getDive(studentsID[i-1]) + "%\n");
                }
                break;
            }
        }      
    }
    
}
