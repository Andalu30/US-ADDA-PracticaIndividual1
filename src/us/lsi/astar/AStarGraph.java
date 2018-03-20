package us.lsi.astar;


import org.jgrapht.Graph;

public interface AStarGraph<V, E> extends Graph<V,E> {
	
	/**
	 * @param edge es una arista
	 * @return El peso de edge
	 */
	@Override
	default double getEdgeWeight(E edge) {
		return 1.;
	}
	/**
	 * @param vertex es el v�rtice actual
	 * @return El peso de vertex
	 */
	default double getVertexWeight(V vertex) {
		return 0.;
	}
	/**
	 * @param vertex El v�rtice actual
	 * @param edgeIn Una arista entrante o incidente en el v�rtice actual. Es null en el v�rtice inicial.
	 * @param edgeOut Una arista saliente o incidente en el v�rtice actual. Es null en el v�rtice final.
	 * @return El peso asociado al v�rtice suponiendo las dos aristas dadas. 
	 */
	default double getVertexWeight(V vertex, E edgeIn, E edgeOut) {
		return 0.;
	}
	
	/**
	 * @param actual El v�rtice actual
	 * @param endVertex El v�rtice destino. Este v�rtice puede ser null. 
	 * 
	 * @return Una cota inferior del peso del camino desde el v�rtice actual al destino, 
	 * o desde el v�rtice actual al conjunto de v�rtices descrito por un predicado objetivo que se especificar� en el AStarAlgorithm.
	 * Debe cumplirse la distancia es cero si el v�rtice actual cumple el predicado objetivo
	 */
	default double getWeightToEnd(V actual, V endVertex) {
		return 0.;
	}
	
	
	
}
