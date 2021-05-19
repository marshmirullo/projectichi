package thesociopath;


import java.util.ArrayList;
import java.util.Random;


public class Graph<T extends Comparable<T>> {
   Vertex<T,Integer> head;
   int size;
   
   Random r = new Random();
	
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
         System.out.println("-------------------------#Student " + temp.vertexInfo + "-------------------------" );
         System.out.println("Diving Rate = " + temp.dive + ", Lunch Time = " + temp.lunchStart + ", Lunch Period = " + temp.lunchPeriod);
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
   
   public String Study(T source,T destination,int rate){
        if (head==null)
           System.out.println("The graph is  empty...");
        if (!hasVertex(source) || !hasVertex(destination)) 
           System.out.println("There is no vertex " + source + " or " + destination + " in the graph");
        if(!hasEdge(source,destination)){
            Vertex<T,Integer> sourceVertex = head;
            while (sourceVertex!=null)	{
            if ( sourceVertex.vertexInfo.compareTo( source ) == 0 )   {
            // Reached source vertex, look for destination now
            Vertex<T,Integer> destinationVertex = head;
            while (destinationVertex!=null)	{
                if ( destinationVertex.vertexInfo.compareTo( destination ) == 0 )   {
                    int a = r.nextInt(10)+1;
                    if(rate==1){
                        addEdge(destination,source,10);
                        addEdge(source,destination,a);
                    }
                    if(rate==0){
                        addEdge(destination,source,2);
                        addEdge(source,destination,a);
                    }
                    return "Event 1 (teaching a stranger) is complete!";
               }
               destinationVertex=destinationVertex.nextVertex;
            }
         }
         sourceVertex=sourceVertex.nextVertex;
           }
        }
        else{
            return "Event 1 (teaching a stranger) is only for stranger. " + source + " and " + destination + " are already friend.";
        }
        return "Event 1 (teaching a stranger) failed!";
        
       
   }

}
