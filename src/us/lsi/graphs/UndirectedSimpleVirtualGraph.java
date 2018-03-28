package us.lsi.graphs;

import org.jgrapht.Graph;


/**
 * <p> Implementaci�n de un grafo virtual simple y no dirigido
 * Asumimos que los v�rtices son subtipo de VirtualVertex &lt; V,E &gt;
 * Asumimos que las aristas son subtipos de SimpleEdge &lt; V &gt; 
 * </p>
 * 
 * @see us.lsi.graphs.VirtualDirectedVertex
 * 
 * <p> El grafo es inmutable por lo que no est�n permitadas las operaci�n de moficaci�n.  </p>
 * 
 * @author Miguel Toro
 *
 * @param <V> El tipo de los v�rtices
 * @param <E> El tipo de las aristas
 * 
 */
public class UndirectedSimpleVirtualGraph<V extends VirtualVertex<V,E>, E extends SimpleEdge<V>>
		extends SimpleVirtualGraph<V,E> 
		implements Graph<V,E>{
	
	
	public UndirectedSimpleVirtualGraph() {
		super();
	}	
	
	@SafeVarargs
	public UndirectedSimpleVirtualGraph(V... vertexSet) {
		super(vertexSet);
	}


	
	

	@Override
	public int degreeOf(V v) {
		return v.edgesOf().size();
	}	
	
}
