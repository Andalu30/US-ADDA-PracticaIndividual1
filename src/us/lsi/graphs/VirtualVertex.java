package us.lsi.graphs;

import java.util.Set;

/**
 * <a> El tipo representa un v�rtice de un grafo virtual no dirigido </a>
 * 
 * @author Miguel Toro
 *
 * @param <V> Tipo de los v�rtices
 * @param <E> Tipo de las aristas
 */
public interface VirtualVertex<V extends VirtualVertex<V,E>, E extends SimpleEdge<V>> {	
	/**
	 * @return Si es un valor v�lido del tipo
	 */
	boolean isValid();
	/**
	 * @return Conjunto de los v�rtices vecinos
	 */
	Set<V> getNeighborListOf();
	/**
	 * @return Conjunto de las aristas hacia los v�rtices vecinos
	 */
	Set<E> edgesOf(); 
	
	/**
	 * @param v V�rtice que se pregunta si es vecino
	 * @return Si el v�rtice es vecino
	 */
	default boolean isNeighbor(V v) {
		return getNeighborListOf().contains(v);
	}
	
//	 * @param v Un v�rtice vecino
//   * @return La arista hacia el v�rtice v
//	 */
//	E getEdge(V v);

}
