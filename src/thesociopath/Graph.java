package thesociopath;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Graph<T extends Comparable<T>,N extends Comparable <N>> {
    Vertex<T,Integer> head;
    int size;
   
    public class Vertex<T extends Comparable<T>, N extends Comparable <N>> {
        Random r = new Random();
    
        T vertexInfo;
        int rep=0;
        int lunchStart; 
        int lunchPeriod;
        int dive;
        int indeg;
        int outdeg;
        Vertex<T,N> nextVertex;
        Edge<T,N> firstEdge;
 
        public Vertex() {
            vertexInfo=null;
            indeg=0;
            outdeg=0;
            nextVertex = null;
            firstEdge = null;
        }
 
        public Vertex(T vInfo, Vertex<T,N> next) {
            vertexInfo = vInfo;
            indeg=0;
            outdeg=0;
            nextVertex = next;
            firstEdge = null;
            lunchStart = generateRandomTime();
            dive = r.nextInt(99)+1;
            int a = r.nextInt(60+1-5)+5;
            while(a%10!=0){
                a = r.nextInt(60+1-5)+5;
            }
            int afterPeriod = a + lunchStart;
            if(afterPeriod>1360){
                lunchPeriod = 1360 - lunchStart;
            }
            else{
                lunchPeriod = a;
            }
        }
 
        public int generateRandomTime(){
            Boolean check = false;
            int a=0;
            while(!check){
                a = r.nextInt(1400+1-1100) + 1100;
                if(a>=1160&&a<1200)
                    continue;
                else if(a>=1260&&a<1300)
                    continue;
                else if(a>=1360&&a<1400)
                    continue;
                else if(a%10!=0)
                    continue;
                else
                    break;
            }
            return a;
        }
    
        public int totalRep(int a){
            rep = rep + a;
            return rep;
        }
    }

    public class Edge<T extends Comparable<T>, N extends Comparable <N>> {
    
        Vertex<T,N> toVertex;
        N weight;
        Edge<T,N> nextEdge;
	
        public Edge()	{
            toVertex = null;
            weight = null;
            nextEdge = null;
        }
	
        public Edge(Vertex<T,N> destination, N w, Edge<T,N> a)	{
            toVertex = destination;
            weight = w;
            nextEdge = a;
        }

        public void setWeight(N weight) {
            this.weight = weight;
        }
    }

   Random r = new Random();
   Scanner s = new Scanner(System.in);
	
   public Graph(){
      head=null;
      size=0;
   }
   
   public void clear() {   
      head=null;
   }

   public int getSize()   {
      return this.size;
   }
   
   public int getIndeg(T v)  {
      if (hasVertex(v)==true)	{
         Vertex<T,Integer> temp = head;
         while (temp!=null) {
            if ( temp.vertexInfo.compareTo( v ) == 0 )
               return temp.indeg;
            temp=temp.nextVertex;
         }
      }
      return -1;
   }
         
   public int getOutdeg(T v)  {
      if (hasVertex(v)==true)	{
         Vertex<T,Integer> temp = head;
         while (temp!=null) {
            if ( temp.vertexInfo.compareTo( v ) == 0 )
               return temp.outdeg;
            temp=temp.nextVertex;
         }
      }
      return -1;
   }

   public boolean hasVertex(T v)	{
      if (head==null)
         return false;
      Vertex<T,Integer> temp = head;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )
            return true;
         temp=temp.nextVertex;
      }
      return false;
   }

   public boolean addVertex(T v)	{
      if (hasVertex(v)==false)	{
         Vertex<T,Integer> temp=head;
         Vertex<T,Integer> newVertex = new Vertex<>(v, null);
         if (head==null)   
            head=newVertex;
         else {
            Vertex<T,Integer> previous=head;;
            while (temp!=null)  {
               previous=temp;
               temp=temp.nextVertex;
            }
            previous.nextVertex=newVertex;
         }
         size++;
         return true;
      }
      else
         return false;
   }
   
   public void editVertexInfo(T v, T newID){
       if(hasVertex(v)){
           Vertex<T,Integer> temp = head;
           while(temp!=null){
               if(temp.vertexInfo.compareTo(v)==0){
                   temp.vertexInfo = newID;
               }
               temp = temp.nextVertex;
           }
       }
       else
            System.out.println("There is no vertex " + v + " in the graph.");
   }
   
   public int getIndex(T v) {
      Vertex<T,Integer> temp = head;
      int pos=0;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )
            return pos;
         temp=temp.nextVertex;
         pos+=1;
      }
      return -1;
   }
   
   public ArrayList<T> getAllVertexObjects() {
      ArrayList<T> list = new ArrayList<>();
      Vertex<T,Integer> temp = head;
      while (temp!=null)	{
         list.add(temp.vertexInfo);
         temp=temp.nextVertex;
      }
      return list;
   }

   public ArrayList<Vertex<T,Integer>> getAllVertices() {
      ArrayList<Vertex<T,Integer>> list = new ArrayList<>();
      Vertex<T,Integer> temp = head;
      while (temp!=null)	{
         list.add(temp);
         temp=temp.nextVertex;
      }
      return list;
   }
   
   public T getVertex(int pos) {
      if (pos>size-1 || pos<0) 
         return null;
      Vertex<T,Integer> temp = head;
      for (int i=0; i<pos; i++)
         temp=temp.nextVertex;
      return temp.vertexInfo;
   }
   
   public Vertex<T,Integer> getVertex(T v){
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(current.vertexInfo.equals(v))
               return current;
           current = current.nextVertex;
       }
       return null;
   }

   public boolean addEdge(T source, T destination, int w)   {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      if(hasEdge(source,destination))
          return false;
      Vertex<T,Integer> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,Integer> destinationVertex = head;
            while (destinationVertex!=null)	{
                if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                  // Reached destination vertex, add edge here
                    Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
                    Edge<T,Integer> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                    sourceVertex.firstEdge=newEdge;
                    sourceVertex.outdeg++;
                    destinationVertex.indeg++;
                    destinationVertex.totalRep(w);
                    return true;
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
   
   public boolean addUndirectedEdge(T source,T destination,Integer w){
        if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Vertex<T,Integer> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,Integer> destinationVertex = head;
            while (destinationVertex!=null)	{
               if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                  // Reached destination vertex, add edge here
                  Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
                  Edge<T,Integer> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                  sourceVertex.firstEdge=newEdge;
                  return true;
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
   
   public boolean removeEdge(T source,T destination){
       if(head==null)
           return false;
       if(!hasVertex(source) || !hasVertex(destination))
           return false;
       Vertex<T,Integer> sourceVertex = head;
       Vertex<T,Integer> destinationVertex = head;
       int w = getEdgeWeight(source,destination);
       while(destinationVertex!=null){
           if(destinationVertex.vertexInfo.compareTo(destination)==0){
               destinationVertex.totalRep(-w);
               break;
           }
           destinationVertex = destinationVertex.nextVertex;
       }
       while(sourceVertex!=null){
           if(sourceVertex.vertexInfo.compareTo(source)==0){
               Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
               Edge<T,Integer> previousEdge = sourceVertex.firstEdge;
               while(currentEdge!=null){
                   if(sourceVertex.firstEdge.toVertex.vertexInfo.compareTo(destination)==0){
                       sourceVertex.firstEdge = sourceVertex.firstEdge.nextEdge;
                       return true;
                   }
                   if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0){
                       previousEdge.nextEdge = currentEdge.nextEdge;
                       currentEdge = null;
                       return true;
                   }
                   previousEdge = currentEdge;
                   currentEdge = currentEdge.nextEdge;                  
               }
           }
           sourceVertex = sourceVertex.nextVertex;
       }
       return false;
   }
   
   public boolean hasEdge(T source, T destination) {
      if (head==null)
         return false;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return false;
      Vertex<T,Integer> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now 
            Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0) 
               // destination vertex found 
                  return true;
               currentEdge=currentEdge.nextEdge;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return false;
   }
   
   public ArrayList<T> getNotFriendsList(T v){
       ArrayList<T> list = new ArrayList<>();
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(!hasEdge(v,current.vertexInfo)&&!v.equals(current.vertexInfo))
               list.add(current.vertexInfo);
           current = current.nextVertex;
       }
       return list;
   }
   
   public ArrayList<Integer> getNotFriendsListIndex(T v){
       ArrayList<Integer> list = new ArrayList<>();
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(!hasEdge(v,current.vertexInfo)&&!v.equals(current.vertexInfo))
               list.add(getIndex(current.vertexInfo));
           current = current.nextVertex;
       }
       return list;
   }
   
   public ArrayList<T> getFriendsList(T v){
       ArrayList<T> list = new ArrayList<>();
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(hasEdge(v,current.vertexInfo)&&!v.equals(current.vertexInfo))
               list.add(current.vertexInfo);
           current = current.nextVertex;
       }
       return list;
   }
   
   public ArrayList<Integer> getFriendsListIndex(T v){
       ArrayList<Integer> list = new ArrayList<>();
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(hasEdge(v,current.vertexInfo)&&!v.equals(current.vertexInfo))
               list.add(getIndex(current.vertexInfo));
           current = current.nextVertex;
       }
       return list;
   }
   
   public int getEdgeWeight(T source, T destination) {
      int notFound=0;
      if (head==null)
         return notFound;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return notFound;
      if(!hasEdge(source,destination)){
          return notFound;
      }
      Vertex<T,Integer> sourceVertex = head;
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now 
            Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0) 
               // destination vertex found 
                  return currentEdge.weight;
               currentEdge=currentEdge.nextEdge;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
      }
      return notFound;
   }
   
   public void changeEdgeWeight(T source,T destination,int a){
      if (head==null)
           System.out.println("Error!");
      if (!hasVertex(source) || !hasVertex(destination)) 
           System.out.println("Error!");
      if(!hasEdge(source,destination)){
          addEdge(source,destination,a);
          addEdge(destination,source,2);
          return;
      }
      Vertex<T,Integer> sourceVertex = head;
      int w = getEdgeWeight(source,destination);
      while (sourceVertex!=null)	{
         if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            Edge<T,Integer> currentEdge = sourceVertex.firstEdge;
            while (currentEdge != null) {
               if (currentEdge.toVertex.vertexInfo.compareTo(destination)==0){
                   removeEdge(source,destination);
                   addEdge(source,destination,a);
                   break;
               }
               currentEdge=currentEdge.nextEdge;
            }
            break;
         }
         sourceVertex=sourceVertex.nextVertex;
      }
   }
   
   public ArrayList<T> getNeighbours (T v)  {
      if (!hasVertex(v))
         return null;
      ArrayList<T> list = new ArrayList<T>();
      Vertex<T,Integer> temp = head;
      while (temp!=null)	{
         if ( temp.vertexInfo.compareTo( v ) == 0 )   {
            // Reached vertex, look for destination now
            Edge<T,Integer> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
               list.add(currentEdge.toVertex.vertexInfo);
               currentEdge=currentEdge.nextEdge;
            }
         }
         temp=temp.nextVertex;
      }
      return list;   
   }
   
   public int getRep(T v){
       if (!hasVertex(v))
           return 0;
       Vertex<T,Integer> temp = head;
       while(temp!=null){
           if(temp.vertexInfo.compareTo(v) == 0){
               return temp.rep;
           }
           temp = temp.nextVertex;
       }
       return 0;
   }
   
   public void printEdges()   {
      Vertex<T,Integer> temp=head;
      while (temp!=null) {
         System.out.println("-------------------------#" + temp.vertexInfo + "-------------------------" );
         System.out.println("Diving Rate = " + temp.dive + ", Lunch Time = " + temp.lunchStart + ", Lunch Period = " + temp.lunchPeriod);
         System.out.println("Current reputation point = " + getRep(temp.vertexInfo));
         Edge<T,Integer> currentEdge = temp.firstEdge;
         System.out.println("List of friends with respective reputation point : ");
         while (currentEdge != null) {
            System.out.print(" ---> " + currentEdge.toVertex.vertexInfo + "(" + currentEdge.weight + "), " );
            currentEdge=currentEdge.nextEdge;
         }
         System.out.println("\n\n");
         temp=temp.nextVertex;
      }  
   }
   
   public void printSpecificEdges(T v){
       if(!hasVertex(v)){
           System.out.println("Vertex " + v + " cannot be found!");
       }
       else{
           Vertex<T,Integer> temp = head;
           while(temp!=null){
               if(temp.vertexInfo.compareTo(v)==0){
                   System.out.println("Current reputation point = " + getRep(v));
                   System.out.println("Diving Rate = " + temp.dive + ", Lunch Time = " + temp.lunchStart + ", Lunch Period = " + temp.lunchPeriod);
                   Edge<T,Integer> currentEdge = temp.firstEdge;
                   System.out.println("List of friends with respective reputation point : ");
                   while (currentEdge != null) {
                        System.out.print(" ---> " + currentEdge.toVertex.vertexInfo + "(" + currentEdge.weight + "), " );
                        currentEdge=currentEdge.nextEdge;
                    }
                   System.out.println("\n\n");                   
               }
               temp=temp.nextVertex;
           }
       }
   }
   
   public void event1(T v){        
        Vertex<T,Integer> current = head;
        while(current!=null){
            if(current.vertexInfo.equals(v)){
                ArrayList<T> list = getNotFriendsList(v);
                while(true){
                    System.out.println("Please choose any stranger you want to be friend with by looking at the list below: ");
                    System.out.println(list);
                    System.out.print("Enter student ID: ");
                    String name;
                    while(true){
                        name = s.nextLine();
                        if(list.contains(name))
                            break;
                        System.out.print("Name entered is not available. Please enter one more time: ");
                    }                    
                    Vertex<T,Integer> destination = head;
                    while(destination!=null){                        
                        if(destination.vertexInfo.equals(name)){
                            int sum=0;                    
                            Boolean question=false;
                            System.out.println("\n\nLET'S START!!!\n");
                            System.out.print("\n#Q1) Generics enable errors to be detected at compile time rather than at runtime: ");
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
                            if(sum>2){
                                addEdge(destination.vertexInfo,current.vertexInfo,10);
                                addEdge(current.vertexInfo,destination.vertexInfo,5);
                            }                            
                            if(sum<=2){
                                addEdge(destination.vertexInfo,current.vertexInfo,2);
                                addEdge(current.vertexInfo,destination.vertexInfo,2);
                            }                                              
                            list.remove(name);
                            break;
                        }
                        destination = destination.nextVertex;
                    }
                    System.out.print("Enter any number to teach other stranger or '0' to exit from event 1: ");
                    int a = s.nextInt();
                    if(a==0)
                        break;
                }
            }
            current = current.nextVertex;
        }
        
   }
   
   public void event2(T v){
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(current.vertexInfo.equals(v)){
               ArrayList<T> list = getFriendsList(v);
               if(list.size()<=1){
                   System.out.println("\nYou do not have enough friends. You cannot continue in this chit-chatting session.");
                   break;
               }
               while(true){  
                     if(list.size()<=1){
                        System.out.println("You do not have enough friends. You cannot continue in this chit-chatting session.");
                        break;
                    }
                    System.out.println("\n\nThis is your current friends list: " + list);
                    System.out.print("Choose any friend you would like to have chit-chat with. Enter the name here: ");
                    String name;
                    while(true){
                        name = s.nextLine();
                        if(list.contains(name))
                            break;
                        System.out.print("Name entered is not in your friends list. Please enter one more time: ");
                    }
                    System.out.println("\nYou will have your chit-chatting session with " + name + ". During the session, you will talk about your other friends with " + name + ".");
                    System.out.println("*for reminder, this session will only affect your friends reputation point, your reputation point will remain the same");
                    System.out.print("\nChoose your other friend that you want to bring into the conversation: ");
                    String name2 = s.nextLine();
                    while(name.equals(name2)||!list.contains(name2)){
                        if(name.equals(name2)){
                            System.out.print("You cannot enter the same name. Please choose other friends: ");
                            name2 = s.nextLine();
                            continue;
                        }
                        if(!list.contains(name2)){
                            System.out.print("Name entered is not available in your friend list. Please enter one more time: ");
                            name2 = s.nextLine();
                            continue;
                        }                      
                    }
                    System.out.println("\nYou will be talking about " + name2 + " with " + name + ".");
                    Vertex<T,Integer> temp2 = head;
                    while(temp2!=null){
                        if(temp2.vertexInfo.equals(name2)){
                            Vertex<T,Integer> temp = head;
                            while(temp!=null){
                                if(temp.vertexInfo.equals(name)){
                                    System.out.println("\nThese are relative reputation point of you and " + name2);
                                    System.out.println("\nYour reputation point relative to " + name2 + ": " + getEdgeWeight(temp2.vertexInfo,v));
                                    System.out.println(name2 + " reputation point relative to you: " + getEdgeWeight(v,temp2.vertexInfo));
                                    System.out.println("\nYou can decide whether you want to talk about good or bad message about " + name2 + " in your conversation with " + name + ".");
                                    System.out.print("Enter any number if you want to talk about good message and '0' for bad message: ");
                                    int select = s.nextInt();
                                    if(select==0){
                                        System.out.println("\nYou have decided to talk about bad message.");
                                        int newRep = -(getEdgeWeight(current.vertexInfo,temp2.vertexInfo));
                                        changeEdgeWeight(temp.vertexInfo,temp2.vertexInfo,newRep);
                                        System.out.println("Because of your chit-chatting session, " + name2 + " reputation point relative to " + name + " will be " + newRep);                                        
                                    }
                                    else{
                                        System.out.println("\nYou have decided to talk about good message. What a nice person are you!");
                                        int newRep = getEdgeWeight(current.vertexInfo,temp2.vertexInfo);
                                        changeEdgeWeight(temp.vertexInfo,temp2.vertexInfo,(newRep/2)+(newRep%2));
                                        System.out.println("Because of your chit-chatting session, " + name2 + " reputation point relative to " + name + " will be " + newRep);
                                    }
                                }
                                temp = temp.nextVertex;
                            }
                        }
                        temp2=temp2.nextVertex;
                    }
                    list.remove(name);
                    list.remove(name2);
                    System.out.print("\nDo you want to have another chit-chatting session? Enter any number to continue and '0' to exit: ");
                    int select = s.nextInt();
                    if(select==0)
                        break;                   
               }              
           }
           current = current.nextVertex;
       }
       System.out.println("\nChecking other students: \n");
       Vertex<T,Integer> current2 = head;
       while(current2!=null){ 
            ArrayList<T> list = getFriendsList(current2.vertexInfo);
            if(current2.vertexInfo.equals(v)){
                current2 = current2.nextVertex;
                continue;
            }            
            System.out.println("Checking " + current2.vertexInfo + " in Event 2.....");
            if(list.size()<=1){
                current2 = current2.nextVertex;
                continue;  
            }            
            while(true){ 
                int random = list.size();
                int name = r.nextInt(random);
                int name2 = r.nextInt(random);
                while(true){                    
                    if(name!=name2)
                        break;
                    name = r.nextInt(random);
                    name2 = r.nextInt(random);
                }      
                Vertex<T,Integer> temp2 = head;
                while(temp2!=null){
                    if(temp2.vertexInfo.equals(list.get(name2))){
                        Vertex<T,Integer> temp = head;
                        while(temp!=null){
                            if(temp.vertexInfo.equals(list.get(name))){
                                int select = r.nextInt(2);
                                if(select==0){
                                    int newRep = -(getEdgeWeight(current2.vertexInfo,temp2.vertexInfo));
                                    changeEdgeWeight(temp.vertexInfo,temp2.vertexInfo,newRep);                                       
                                }
                                else{
                                    int newRep = getEdgeWeight(current2.vertexInfo,temp2.vertexInfo);
                                    changeEdgeWeight(temp.vertexInfo,temp2.vertexInfo,(newRep/2)+(newRep%2));                    
                                }
                            }
                            temp = temp.nextVertex;
                        }
                    }
                    temp2=temp2.nextVertex;
                }
                T n1 = list.get(name);
                T n2 = list.get(name2);
                list.remove(n1);
                list.remove(n2);
                if(list.size()<=1)
                    break;                
                int select = r.nextInt(2);
                if(select==0)
                   break;                     
            }                        
            current2 = current2.nextVertex;
        }
    }
   
        
   
   public void event3(T v){
       Vertex<T,Integer> current = head;
       int lunchperiodleft = 0;
       ArrayList<T> list = new ArrayList<>();
       ArrayList<Integer> list_lunchStart = new ArrayList<>();
       ArrayList<Integer> list_lunchEnd = new ArrayList<>();
       while(current!=null){
           if(current.vertexInfo.compareTo(v)==0){
               int lunchEndTime_1 = TimeGenerator(current.lunchPeriod,current.lunchStart);
               lunchperiodleft = current.lunchPeriod;
               System.out.println("Your lunch time interval: " + current.lunchStart + " - " + lunchEndTime_1 + "\n");
               Vertex<T,Integer> destination = head;
               while(destination!=null){
                   if(destination.vertexInfo.compareTo(v)==0){
                       destination = destination.nextVertex;
                       continue;
                   }
                   int lunchEndTime_2 = TimeGenerator(destination.lunchPeriod ,destination.lunchStart); //lunch end time for destination vertex
                   if(lunchEndTime_1>destination.lunchStart&&current.lunchStart<destination.lunchStart){
                       list.add(destination.vertexInfo);
                       list_lunchStart.add(destination.lunchStart);
                       list_lunchEnd.add(lunchEndTime_1);
                       destination = destination.nextVertex;
                       continue;
                   }
                   if(current.lunchStart<lunchEndTime_2&&current.lunchStart>destination.lunchStart){
                       list.add(destination.vertexInfo);
                       list_lunchStart.add(current.lunchStart);
                       list_lunchEnd.add(lunchEndTime_2);
                       destination = destination.nextVertex;
                       continue;
                   }
                   if(destination.lunchStart>=current.lunchStart&&lunchEndTime_1>=lunchEndTime_2){
                       list.add(destination.vertexInfo);
                       list_lunchStart.add(destination.lunchStart);
                       list_lunchEnd.add(lunchEndTime_2);
                       destination = destination.nextVertex;
                       continue;
                   }
                   if(current.lunchStart>=destination.lunchStart&&lunchEndTime_2>=lunchEndTime_1){
                       list.add(destination.vertexInfo);
                       list_lunchStart.add(current.lunchStart);
                       list_lunchEnd.add(lunchEndTime_1);
                       destination = destination.nextVertex;
                       continue;
                   }
                   destination = destination.nextVertex;
               }
               break;
           }
           current = current.nextVertex;
       }
       int timeavailable = current.lunchStart;
       while(true){
           if(timeavailable!=current.lunchStart){
               for(int i=0;i<list.size();i++){
                   if(list_lunchEnd.get(i)==list_lunchStart.get(i)){
                       list.remove(i);
                       list_lunchStart.remove(i);
                       list_lunchEnd.remove(i);
                       continue;
                   }
                   if(list_lunchEnd.get(i)<timeavailable){
                       list.remove(i);
                       list_lunchStart.remove(i);
                       list_lunchEnd.remove(i);
                       continue;
                   }
                   if(list_lunchStart.get(i)>timeavailable){
                       continue;
                   }
                   list_lunchStart.set(i, timeavailable);
               }
           }
           
           if(list.isEmpty()){
               System.out.println("\nThe list is empty. You do not have any friends available to have lunch with.\n");
               break;
           }
           if(lunchperiodleft<=0){
               System.out.println("\nAt the moment, you do not have any lunch period left.\n");
               break;
           }
           System.out.println("\nCurrent lunch period left: " + lunchperiodleft);
           System.out.println("List of student with their lunch time available: \n");
           for(int i=0;i<list.size();i++){
               System.out.print((i+1) + ") " + list.get(i) + ": " + list_lunchStart.get(i) + " - " + list_lunchEnd.get(i) + "\n");               
           }
           System.out.print("\nChoose any of the person above to have lunch with. Enter the number of the person: ");
           int select = s.nextInt();
           Vertex<T,Integer> temp = head;
           while(temp!=null){
               if(temp.vertexInfo.compareTo(list.get(select-1))==0){
                   System.out.println("You have choose " + list.get(select -1 ) + " to have lunch together.");
                   System.out.print("Time interval available to have lunch together: " + list_lunchStart.get(select-1) + " - " + list_lunchEnd.get(select-1));
                   //TimeIntervalGenerator(current.vertexInfo,temp.vertexInfo);
                   System.out.print("\nHow many minutes do you want to spend lunch together?: ");
                   int duration = s.nextInt();
                   while(duration>(list_lunchEnd.get(select-1)-list_lunchStart.get(select-1))){
                       System.out.print("\nThe time entered exceed " + list.get(select-1) + " lunch time. Please enter one more time: ");
                       duration = s.nextInt();
                   }
                   lunchperiodleft-=duration;
                   System.out.println("\nYou have spend " + duration + " minutes with " + list.get(select-1) + ". Your reputation point will increase by 1.");
                   timeavailable = TimeGenerator(timeavailable,duration);
                   if(timeavailable==TimeGenerator(current.lunchStart,current.lunchPeriod)){
                       System.out.println("There is no free time left to have lunch.");
                   }
                   else{
                        System.out.println("\nCurrent free time interval: " + timeavailable + " - " + TimeGenerator(current.lunchStart,current.lunchPeriod));
                   }
                   list.remove(select-1);
                   list_lunchStart.remove(select-1);
                   list_lunchEnd.remove(select-1);
                   int newRep = getEdgeWeight(temp.vertexInfo,current.vertexInfo);
                   changeEdgeWeight(temp.vertexInfo,current.vertexInfo,newRep+1);
                   break;
               }
               temp = temp.nextVertex;
           }          
           System.out.print("\nDo you want to spend your lunch with another person? Enter any number to continue and 0 to exit: ");
           int exit = s.nextInt();
           if(exit==0)
               break;           
       }      
   }
   
   public int TimeGenerator(int a,int b){
       int sum = a + b;
       if(sum%100>=60){
           return (sum+100)-60;
       }
       else if(sum%100==0){
           int temp = 60 - (a%100);
           return sum + (b-temp);
       }
       else
           return sum;
   }
   
    public void event5(T v){
       path.clear();
       Vertex<T,Integer> user = getVertex(v);
       ArrayList<Integer>[] adjList = new ArrayList[10];
       for(int i=0;i<10;i++){
           adjList[i] = getFriendsListIndex(getVertex(i));           
       }
       ArrayList<T> namelist = getAllVertexObjects();
       namelist.remove(v);
       System.out.println("Students name list: " + namelist);
       System.out.print("Choose your crush from list above: ");
       String crushName;
       Vertex<T,Integer> crush = head;
       while(true){
           crushName = s.nextLine();
           if(namelist.contains(crushName)){
               while(crush!=null){
                   if(crush.vertexInfo.equals(crushName))
                       break;
                   crush = crush.nextVertex;
               }
               namelist.remove(crushName);
               break;
           }
           System.out.print("Name entered is not available. Please enter one more time: ");
       }
       ArrayList<Integer> possibleStartPoint = getNotFriendsListIndex(crush.vertexInfo);
       int rumorsStartPoint;
       while(true){
           rumorsStartPoint = r.nextInt(9)+1;
           if(rumorsStartPoint!=getIndex(crush.vertexInfo)&&possibleStartPoint.contains(rumorsStartPoint))
               break;
       }
       Vertex<T,Integer> rumors = head;
       while(rumors!=null){
           if(rumors.vertexInfo.equals(getVertex(rumorsStartPoint)))
               break;
           rumors = rumors.nextVertex;
       }
       boolean[] isVisited = new boolean[10];
       LinkedList<T> pathlist = new LinkedList<>();
       pathlist.add(rumors.vertexInfo);
       System.out.println("\nIt is believed that the rumors start from " + rumors.vertexInfo + ". You need to stop the rumors from reach to your crush.\n");
       System.out.println("\n\n\nChecking the graph...\n");
       getAllPath(getIndex(rumors.vertexInfo),getIndex(crush.vertexInfo),isVisited,pathlist,adjList);
       if(path.isEmpty()){
           System.out.println("There is no connection between " + rumors.vertexInfo + " to " + crush.vertexInfo + ". Don't worry!\n");
           return;
       }
       System.out.println("These are some of the path that connect " + rumors.vertexInfo + " to " + crush.vertexInfo + ": \n");
       for(int i=0;i<path.size();i++){
           System.out.print(i+1 + ") ");
           if(path.get(i).contains(v)){
               path.get(i).print();
               System.out.print("*this path is removed because user is included in this path, the connection breaks\n");
               path.remove(i);
               continue;
            }          
           path.get(i).print();
       }
       if(path.isEmpty()){
           System.out.println("\nThere is no connection between " + rumors.vertexInfo + " to " + crush.vertexInfo + ". Don't worry!");
           return;
       }
       System.out.println("\n\n\nChecking the rumors position.....");
       System.out.println("\n*\"^^^\" is the indicator to the current rumors position among students");
       System.out.println("\n\n\n----------Day 0----------");
       for(int i=0;i<path.size();i++){
           System.out.print(i+1 + ") ");
           path.get(i).print();
       }
       System.out.println("    ^^^"); //indicator to current rumors position  
       System.out.println("\n\nIn the next day, the rumors has move to the next person.");
       int day=1;
       while(!path.isEmpty()){  
           for(int i=0;i<path.size();i++){
               path.get(i).removeFirst();
               if(path.get(i).size==1){
                   System.out.println("\n\n**************************************************");
                   System.out.println("\nOh no! The rumors has reached your crush in path " + (i+1) + "! T_T");
                   return;
               }
           }
           System.out.println("\n\n\n----------Day " + day + "----------");
           for(int i=0;i<path.size();i++){
                System.out.print(i+1 + ") ");
                path.get(i).print();
            }
           System.out.println("    ^^^");
           System.out.print("\nChoose any path you want to convince to stop the rumors. Enter the path number: ");
           int pathOption;
           while(true){
               pathOption = s.nextInt();
               if((pathOption-1)>=0&&(pathOption-1)<path.size())
                   break;
           }           
            System.out.println("\nYou will convince " + path.get(pathOption-1).head.element + " in path " + pathOption + ".....");
            System.out.println("\nChecking......");
            System.out.println("\nNice! You have stop rumors in path " + pathOption + ".");
            for(int i=0;i<path.size();i++){
                if(i==pathOption-1)
                    continue;
                if(path.get(i).head.element.equals(path.get(pathOption-1).head.element)){
                    System.out.println("\nBecause both path have the same person, rumors has stop in path " + i+1 + " too.");
                    path.remove(i);
                }
            }
            path.remove(pathOption-1);
            for(int i=0;i<path.size();i++){
                
            }
            if(path.isEmpty())
                break;
            System.out.println("\nLet's move to the next day.");
            day++;
       }
        System.out.println("The rumors did not reach your crush! ^__^");
    }
    
    ArrayList<LinkedList<T>> path = new ArrayList<>();
   
    public void getAllPath(Integer s,Integer d,boolean[] isVisited,LinkedList<T> pathlist,ArrayList<Integer>[] adjList){
        if(s.equals(d)){              
            LinkedList<T> temp = new LinkedList<>();
            for(int i=0;i<pathlist.size;i++){
                temp.add(i, pathlist.get(i));
            }
            path.add(temp);
            return;
        }
        isVisited[s] = true;
        for(Integer i : adjList[s]){
            if(!isVisited[i]){
                pathlist.add(getVertex(i));
                getAllPath(i,d,isVisited,pathlist,adjList);
                pathlist.remove(pathlist.indexOf(getVertex(i)));
            }
        }
        isVisited[s] = false;
    }
    
}
