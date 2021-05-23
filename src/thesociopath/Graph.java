package thesociopath;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Graph<T extends Comparable<T>> {
   Vertex<T,Integer> head;
   int size;
   
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
   
   public void getNotFriendsList(T v){
       ArrayList<T> list = new ArrayList<>();
       Vertex<T,Integer> current = head;
       while(current!=null){
           if(!hasEdge(v,current.vertexInfo)&&!v.equals(current.vertexInfo))
               list.add(current.vertexInfo);
           current = current.nextVertex;
       }
       System.out.println(list);
   }
   
   public int getEdgeWeight(T source, T destination) {
      int notFound=0;
      if (head==null)
         return notFound;
      if (!hasVertex(source) || !hasVertex(destination)) 
         return notFound;
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
           System.out.println("Error!");;
      if (!hasVertex(source) || !hasVertex(destination)) 
           System.out.println("Error!");
      if(!hasEdge(source,destination)){
          addEdge(source,destination,a);
          addEdge(destination,source,a);
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
   
   public void event1(T source,T destination){        
        if (head==null)
           System.out.println("The graph is  empty...");
        if (!hasVertex(source) || !hasVertex(destination)) 
           System.out.println("There is no vertex " + destination + " in the graph");
        if(!hasEdge(source,destination)){
            Vertex<T,Integer> sourceVertex = head;
            while (sourceVertex!=null)	{
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,Integer> destinationVertex = head;
            while (destinationVertex!=null)	{
                if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
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
                        
                    int rate = 0;
                    if(sum>2)
                        rate = 1;
                    if(sum<=2)
                        rate = 0;                    
                    int a = r.nextInt(10)+1;
                    if(rate==1){
                        addEdge(destination,source,10);
                        addEdge(source,destination,a);
                    }
                    if(rate==0){
                        addEdge(destination,source,2);
                        addEdge(source,destination,a);
                    }
                    System.out.println("Event 1 (teaching a stranger) is complete!\n");
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
           }
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
                   if(timeavailable==TimeGenerator(current.lunchStart,current.lunchPeriod))
                       break;
                   System.out.println("\nCurrent free time interval: " + timeavailable + " - " + TimeGenerator(current.lunchStart,current.lunchPeriod));
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
   
   public void TimeIntervalGenerator(T a,T b){
       Vertex<T,Integer> vertex1 = head;
       Vertex<T,Integer> vertex2 = head;
       while(vertex1!=null){
           if(vertex1.vertexInfo.compareTo(a)==0)
               break;
           vertex1 = vertex1.nextVertex;
       }
       while(vertex2!=null){
           if(vertex2.vertexInfo.compareTo(b)==0)
               break;
           vertex2 = vertex2.nextVertex;
       }
       int lunchEndTime_1 = TimeGenerator(vertex1.lunchStart,vertex1.lunchPeriod);
       int lunchEndTime_2 = TimeGenerator(vertex2.lunchStart,vertex2.lunchPeriod);
       if(lunchEndTime_1>vertex2.lunchStart&&vertex1.lunchStart<vertex2.lunchStart){
           System.out.println(vertex2.lunchStart + " - " + lunchEndTime_1);
       }
       else if(vertex1.lunchStart<lunchEndTime_2&&vertex2.lunchStart<vertex1.lunchStart){
           System.out.println(vertex1.lunchStart + " - " + lunchEndTime_2);
       }
       else if(vertex2.lunchStart>=vertex1.lunchStart&&lunchEndTime_1>=lunchEndTime_2){
           System.out.println(vertex2.lunchStart + " - " + lunchEndTime_2);
       }
       else if(vertex1.lunchStart>=vertex2.lunchStart&&lunchEndTime_2>=lunchEndTime_1){
           System.out.println(vertex1.lunchStart + " - " + lunchEndTime_1);
       }
       else
           System.out.println("Error!");
       
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
}
