package us.lsi.graphs;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphWalk;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * @author Miguel Toro
 * 
 * <a> Clase de factor�a para el tipo GraphPath&lt;V,E&gt; </a>
 *
 */
public class GraphPaths {

	public static <V,E> GraphPath<V,E> addGraphPath(GraphPath<V,E> r1, GraphPath<V,E> r2){
		Preconditions.checkArgument(r1.getEndVertex().equals(r2.getStartVertex()),"El v�rtice final no es igual al inicial");
		List<E> edgeList = Lists.newArrayList(r1.getEdgeList());
		edgeList.addAll(r2.getEdgeList());
		return new GraphWalk<V,E>(r1.getGraph(), r1.getStartVertex(), r2.getEndVertex(),
				edgeList, r1.getWeight()+r2.getWeight());
	}

	public static <V,E> GraphPath<V,E> create(Graph<V,E> g, V v1, V v2, E e, Double d){
		List<E> edgeList = Lists.newArrayList();
		edgeList.add(e);
		return new GraphWalk<V,E>(g, v1, v2, edgeList, d);
	}
}
