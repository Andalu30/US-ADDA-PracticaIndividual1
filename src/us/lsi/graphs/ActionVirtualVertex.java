package us.lsi.graphs;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Miguel Toro
 *
 * @param <A> Tipo de la acci�n
 * @param <V> Tipo del v�rtice
 * 
 * 
 * <a> Tipo adecuado para modelar un v�rtice de un grafo virtual simple cuyas aristas est�n 
 * definidas por un conjunto finito de acciones. 
 * Cada acci�n v�lida identifica de forma �nica uno de los v�cinos del v�rtice. 
 * Cada v�rtice conoce sus vecinos y la forma de llegar a ellos mediante una de las acciones v�lidas disponibles </a>
 */
public abstract class ActionVirtualVertex<A, V extends ActionVirtualVertex<A,V>> 
			implements VirtualVertex<V, ActionSimpleEdge<A,V>> {

	public ActionVirtualVertex() {
	}
	
	/**
	 * @return Si es un valor v�lido del tipo
	 */
	@Override
	public abstract boolean isValid();
	/**
	 * @pre isApplicable(a)
	 * @param a Una acci�n
	 * @return El vecino tras tomar esa acci�n
	 */
	public abstract V neighbor(A a);
	/**
	 * @param a Una acci�n
	 * @return Si la acci�n es aplicable en este v�rtice
	 */
	public abstract boolean isApplicable(A a);
	
	/**
	 * Para ser implementado por el subtipo
	 * @return Lista de acciones disponibles
	 */
	public abstract List<A> acciones();
	protected abstract V getThis();
	
	private Set<V> neighbors = null;
	private Set<ActionSimpleEdge<A,V>> edges = null;
	

	@Override
	public Set<V> getNeighborListOf() {
		if (this.neighbors==null) {
			this.neighbors=acciones().stream().filter(x -> this.isApplicable(x))
					.map(x -> this.neighbor(x)).collect(Collectors.toSet());
		}
		return this.neighbors;
	}

	@Override
	public Set<ActionSimpleEdge<A,V>> edgesOf() {
		if (this.edges==null) {
			this.edges=acciones()
					.stream()
					.filter(x -> this.isApplicable(x))
					.map(x -> ActionSimpleEdge.create(getThis(),
							this.neighbor(x), x)).collect(Collectors.toSet());
		}
		return edges;
	}

	@Override
	public boolean isNeighbor(V e) {
		return this.getNeighborListOf().contains(e);
	}

	
}
