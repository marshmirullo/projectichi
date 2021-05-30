package thesociopath;

import java.util.InputMismatchException;
import java.util.Scanner;
/*Event 4
Stacking books
*/
public class StackBook {
    private LL<Integer> books = new LL<>();
    private int numberOfBooks, heightOfBooks;
    private int count=0;
    boolean order=true;//order true indicate that there are books arranged in increasing order

    public void run(){
        Scanner s=new Scanner(System.in);
        
            System.out.println("Enter the number of books: ");
            numberOfBooks=s.nextInt();
            int i=0;
            while(i<numberOfBooks){
                try{
                    System.out.printf("Enter the height of the book no. %d: \n", i+1);
                    heightOfBooks=s.nextInt();
                    books.add(heightOfBooks);
                    i++;
                }
                catch (InputMismatchException | NumberFormatException ex ){
                    System.out.println("Invalid input! You have to enter a number");
                    s.next();
                    continue;
                }
            }
        arrange();
    }

    public void arrange(){
        while(order){
            order=false;
            for(int i=0; i<books.getSize()-1; i++){
                if(books.get(i)<books.get(i+1)){
                    books.remove(i+1);
                    order=true;
                }
            }
            count++;
        }
        count--;//because it will count +1 even after the order
    }

    public void output(){
        System.out.printf("You met librarian's request in %d round(s)\n",count);
        System.out.print("The Height of the books: ");
        books.print();

    }
} 

