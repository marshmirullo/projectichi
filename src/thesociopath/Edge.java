/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesociopath;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Edge<T extends Comparable<T>, N extends Comparable<N>>{
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

} 
