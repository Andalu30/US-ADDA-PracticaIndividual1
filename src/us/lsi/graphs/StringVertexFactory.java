package us.lsi.graphs;


/**
 * <a> Tipo que deben los v�rtices de un grafo que queremos leer de un fichero cuyas l�neas
 * representan una arista. Las l�neas deben ser de la forma </a>
 * 
 * <a>s1,v1,v2,v3 </a>
 * 
 * <a>D�nde s1 es un identificador �nicos del v�rtice y v1, ... propiedades de la arista </a>
 * 
 * @author Miguel Toro
 *
 * @param <V> El tipo de los v�rtices
 */
public interface StringVertexFactory<V> {
	/**
	 * @param formato Los campos de la l�nea correspondiente: s1,v1,v2,v3
	 * @return El v�rtice creado
	 */
	public V createVertex(String[] formato);
}
