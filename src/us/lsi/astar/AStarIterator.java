package us.lsi.astar;

/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* -------------------------
 * ClosestFirstIterator.java
 * -------------------------
 * (C) Copyright 2003-2008, by John V. Sichi and Contributors.
 *
 * Original Author:  John V. Sichi
 * Contributor(s):   Barak Naveh
 *
 * $Id: ClosestFirstIterator.java 697 2009-09-08 22:21:13Z perfecthash $
 *
 * Changes
 * -------
 * 02-Sep-2003 : Initial revision (JVS);
 * 31-Jan-2004 : Reparented and changed interface to parent class (BN);
 * 29-May-2005 : Added radius support (JVS);
 * 06-Jun-2005 : Made generic (CH);
 *
 */


import org.jgrapht.*;
import org.jgrapht.traverse.CrossComponentIterator;
import org.jgrapht.util.*;



/**
 * @author Miguel Toro
 *
 * @param <V> Tipo de los v�rtices
 * @param <E> Tipo de las aristas
 */
public class AStarIterator<V, E> extends CrossComponentIterator<V,E, FibonacciHeapNode<AStarIterator.QueueEntry<V, E>>>
{
    //~ Instance fields --------------------------------------------------------

	private AStarGraph<V, E> wGraph;
	private V endVertex;
	
    /**
     * Priority queue of fringe vertices.
     */
    private FibonacciHeap<QueueEntry<V, E>> heap =
        new FibonacciHeap<QueueEntry<V, E>>();

    /**
     * Maximum distance to search.
     */
    private double radius = Double.POSITIVE_INFINITY;

    private boolean initialized = false;

    //~ Constructors -----------------------------------------------------------

    /**
     * Crea un iterador para el grafo
     *
     * @param g El grafo
     */
    public AStarIterator(AStarGraph<V, E> g)
    {
        this(g,null,null, Double.POSITIVE_INFINITY);
    }

    /**
     * Crea un nuevo iterador para el gr�fico especificado. Es un iterador que continua por el siguiente v�rtice 
     * que cuya funci�n f(v) sea la m�s peque�a posible. La iteraci�n 
     * se iniciar� en el v�rtice de inicio especificada y se limitar� a la 
     *�componente conexa que incluya ese v�rtice. 
     *
     * @param g Grafo 
     * @param startVertex V�rtice Origen
     * @param endVertex V�rtice destino
     */
    public AStarIterator(AStarGraph<V, E> g, V startVertex, V endVertex)
    {
        this(g, startVertex, endVertex, Double.POSITIVE_INFINITY);
    }

    
    
    AStarIterator(AStarGraph<V, E> g, V startVertex, V endVertex,  double radius)
    {
        
    	super(g, startVertex);
        this.radius = radius;
        checkRadiusTraversal(isCrossComponentTraversal());
        initialized = true;
        this.wGraph = g;
        this.endVertex = endVertex;
    }

    //~ Methods ----------------------------------------------------------------

   
    @Override
	public void setCrossComponentTraversal(boolean crossComponentTraversal)
    {
        if (initialized) {
            checkRadiusTraversal(crossComponentTraversal);
        }
        super.setCrossComponentTraversal(crossComponentTraversal);
    }

    /**
     * La longitud del camino m�s corto conocido hasta el v�rtice proporcionado. 
     * Si el v�rtice ha sido ya visitado estonces es verdaderamente la m�s corta;
     * pero si en otro caso una cota superior de la misma
     * 
     *
     * @param vertex V�rtice para el que se busca el camino hasta el origen
     *
     * @return Longitud del camino m�s corto conocido, o Double.POSITIVE_INFINITY si no 
     * se ha encontrado ning�n camino todav�a. 
     * 
     */
    public double getShortestPathLength(V vertex)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node == null) {
            return Double.POSITIVE_INFINITY;
        }

        return node.getKey();
    }

    /**
     * El �rbol de recubrimiento que contiene los caminos desde el origen 
     * a los v�rtices alcanzados.
     *
     * @param vertex V�rtice.
     *
     * @return La arista que inicia del camino desde el v�rtice al origen,
     * o <code>null</code> si el v�rtice no ha sido alcanzado 
     * o es el v�rtice origen
     * 
     */
    public E getSpanningTreeEdge(V vertex)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node == null) {
            return null;
        }

        return node.getData().spanningTreeEdge;
    }

    /**
     * @see CrossComponentIterator#isConnectedComponentExhausted()
     */
    @Override
	protected boolean isConnectedComponentExhausted()
    {
        if (heap.size() == 0) {
            return true;
        } else {
            if (heap.min().getKey() > radius) {
                heap.clear();

                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @see CrossComponentIterator#encounterVertex(Object, Object)
     */
    @Override
	protected void encounterVertex(V vertex, E edge)
    {
        double shortestPathLength;
        double initialWeight;
        
        if (edge == null) {
        	initialWeight = calculateInitialPathWeightFromOriginToActual(vertex);
            shortestPathLength = calculateInitialPathWeight(vertex);
        } else {
        	initialWeight = calculatePathWeightFromOriginToActual(vertex, edge);
            shortestPathLength = calculatePathWeight(vertex, edge, initialWeight);
        }
        FibonacciHeapNode<QueueEntry<V, E>> node = createSeenData(vertex, edge);
        node.getData().distance = initialWeight;
        putSeenData(vertex, node);
        heap.insert(node, shortestPathLength);
    }

    /**
     * Override superclass. When we see a vertex again, we need to see if the
     * new edge provides a shorter path than the old edge.
     *
     * @param vertex the vertex re-encountered
     * @param edge the edge via which the vertex was re-encountered
     */
    @Override
	protected void encounterVertexAgain(V vertex, E edge)
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = getSeenData(vertex);

        if (node.getData().frozen) {
            // no improvement for this vertex possible
            return;
        }

        double distanceFromOriginToActual = calculatePathWeightFromOriginToActual(vertex, edge);
        double candidatePathLength = calculatePathWeight(vertex, edge, distanceFromOriginToActual);

        if (candidatePathLength < node.getKey()) {
            node.getData().spanningTreeEdge = edge;
            node.getData().distance = distanceFromOriginToActual;
            heap.decreaseKey(node, candidatePathLength);
        }
    }

    /**
     * @see CrossComponentIterator#provideNextVertex()
     */
    @Override
	protected V provideNextVertex()
    {
        FibonacciHeapNode<QueueEntry<V, E>> node = heap.removeMin();
        node.getData().frozen = true;

        return node.getData().vertex;
    }
    
    /**
     * Determina el peso inicial del camino que s�lo contiene el v�rtice dado
     *
     * @param vertex the vertex for which to calculate the path length.
     *
     * @return calculated path length.
     */
    
    protected double calculateInitialPathWeightFromOriginToActual(V vertex){
    	return wGraph.getVertexWeight(vertex);
    }
    
    /**
     *  
     * Determina el peso total del camino que pasa por el v�rtice dado
     *
     * @param vertex the vertex for which to calculate the path length.
     *
     * @return calculated path length.
     */
    
    protected double calculateInitialPathWeight(V vertex){
    	return calculateInitialPathWeightFromOriginToActual(vertex)+
    			wGraph.getWeightToEnd(vertex,endVertex);
    }
    
    /**
     * Determina la longitud del camino hasta vertex via an edge, using the path length for
     * the opposite vertex.
     *
     * @param vertex the vertex for which to calculate the path length.
     * @param edge the edge via which the path is being extended.
     *
     * @return calculated path length.
     */
    
    protected double calculatePathWeightFromOriginToActual(V vertex, E edge){
    	
    	V otherVertex = Graphs.getOppositeVertex(getGraph(), edge, vertex);
        FibonacciHeapNode<QueueEntry<V, E>> otherEntry = getSeenData(otherVertex);      
        
        E edgeBefore = this.getSpanningTreeEdge(otherVertex);
    	
    	double r = otherEntry.getData().distance 
    				+getGraph().getEdgeWeight(edge)
					+wGraph.getVertexWeight(vertex)
					+wGraph.getVertexWeight(otherVertex, edgeBefore, edge);
		return r;
    }
    
    /**
     * Determine la longitud total del camino a trav�s de vertex via an edge, using the path length for
     * the opposite vertex.
     *
     * @param vertex the vertex for which to calculate the path length.
     * @param edge the edge via which the path is being extended.
     * @param distance Distancia m�xima
     *
     * @return calculated path length.
     */
    
    protected double calculatePathWeight(V vertex, E edge, double distance)  { // cambiado de private a protected      		
        return distance+wGraph.getWeightToEnd(vertex,endVertex);
    }

    private void checkRadiusTraversal(boolean crossComponentTraversal)
    {
        if (crossComponentTraversal && (radius != Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException(
                "radius may not be specified for cross-component traversal");
        }
    }

    /**
     * The first time we see a vertex, make up a new heap node for it.
     *
     * @param vertex a vertex which has just been encountered.
     * @param edge the edge via which the vertex was encountered.
     *
     * @return the new heap node.
     */
    private FibonacciHeapNode<QueueEntry<V, E>> createSeenData(
        V vertex,
        E edge)
    {
        QueueEntry<V, E> entry = new QueueEntry<V, E>();
        entry.vertex = vertex;
        entry.spanningTreeEdge = edge;

        return new FibonacciHeapNode<QueueEntry<V, E>>(entry);
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * Private data to associate with each entry in the priority queue.
     */
    static class QueueEntry<V, E>
    {
        /**
         * Best spanning tree edge to vertex seen so far.
         */
        E spanningTreeEdge;

        /**
         * The vertex reached.
         */
        V vertex;

        /**
         * True once spanningTreeEdge is guaranteed to be the true minimum.
         */
        boolean frozen;
        
        /**
         * Distance to origin 
         */
        double distance;

        QueueEntry()
        {
        }
    }
}

// End ClosestFirstIterator.java

