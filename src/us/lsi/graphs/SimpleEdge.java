package us.lsi.graphs;

import org.jgraph.graph.DefaultEdge;

/**
 * <p> Una arista simple entre dos v�rtices de tipo V </p>
 * 
 * @author Miguel Toro
 *
 * @param <V> El tipo de los v�rtices de la arista
 */
public class SimpleEdge<V> extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double weight;

		
	/**
	 * @param v1 Un v�rtice
	 * @param v2 Un segundo v�rtice
	 * @param <V> El tipo de los v�rtices que une la arista
	 * @return Una arista entre ambos v�rtices
	 */
	public static <V> SimpleEdge<V> create(V v1, V v2) {
		return new SimpleEdge<>(v1,v2);
	}
	
	/**
	 * @param v1 Un v�rtice
	 * @param v2 Un segundo v�rtice
	 * @param weight El peso de la arista
	 * @param <V> el tipo de los v�rtices
	 * @return Una arista entre ambos v�rtices
	 */
	public static <V> SimpleEdge<V> create(V v1, V v2, double weight) {
		return new SimpleEdge<V>(v1, v2, weight);
	}


	protected SimpleEdge(V c1, V c2) {
		super();
		super.source = c1;
		super.target = c2;
		this.weight = 1.;
	}

	protected SimpleEdge(V c1, V c2, double weight) {
		super();
		super.source = c1;
		super.target = c2;
		this.weight = weight;
	}
	
	
	/**
	 * @return El v�rtice origen
	 */
	@Override
	@SuppressWarnings("unchecked")
	public V getSource(){
		return (V) super.source;
	}
	
	/**
	 * @return El v�rtice destino
	 */
	@Override
	@SuppressWarnings("unchecked")
	public V getTarget(){
		return (V) super.target;
	}
	
	/**
	 * @return El peso asociado a la arista
	 */
	public double getEdgeWeight(){
		return this.weight;
	}
		
	/**
	 * @param weight El nuevo peso de la arista
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @param v Un v�rtice de la arista
	 * @return El otro v�rtice
	 */
	@SuppressWarnings("unchecked")
	public V otherVertex(V v){
		V r = null;
		if(v.equals(this.source)){
			r = (V)super.target;
		} else if(v.equals(this.target)){
			r = (V)super.source;
		}
		return r;
	}
	
	@Override
	public String toString() {
		return "("+this.source+","+this.target+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SimpleEdge))
			return false;
		SimpleEdge<?> other = (SimpleEdge<?>) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}	
	
}

