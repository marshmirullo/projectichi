package thesociopath;

import java.util.Random;


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
           if(a>=1160&&a<1200){
               continue;
           }
           else if(a>=1260&&a<1300){
               continue;
           }
           else if(a>=1360&&a<1400){
               continue;
           }
           else if(a%10!=0){
               continue;
           }
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
