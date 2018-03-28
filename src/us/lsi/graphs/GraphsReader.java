package us.lsi.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jgrapht.Graph;

/**
 * <p>
 * Clase adecuada para construir un grafo a partir de la informaci�n en un fichero
 * 
 * El fichero debe tener una estructura como la siguiente. 
 * 
 * Cada l�nea en la secci�n de v�rtices representa un v�rtice. 
 * El primer campo debe ser un identificador �nico para el mismo
 * Los otros campos propiedades
 * 
 * Cada l�nea de la secci�n de aristas representa una arista
 * Los dos primeros campos son los identificadores de los v�rtices extremos
 * Los otros campos propiedades
 * </p>
 * 
 * #VERTEX# <br>
 * s1,Sevilla <br>
 * s2,C�rdoba <br>
 * s3,C�diz <br>
 * s4,M�laga <br>
 * #EDGE# <br>
 * s1,s2,180. <br>
 * s1,s3,120. <br>
 * s2,s4,140. <br>
 * s4,s1,210. <br>
 *
 * 
 * 
 * @author Miguel Toro
 *
 */
public class GraphsReader {

	public static String[] eliminaBlancos(String[] s) {
		String[] r = new String[s.length];
		for (int i = 0; i < r.length; i++) {
			r[i] = s[i].trim();
		}
		return r;
	}

	/**
	 * @param file Fichero con la informaci�n de entrada
	 * @param vf Factor�a para construir los v�rtices a partir de las l�neas del fichero previamente tokenizada
	 * @param ef Factor�a para construir las aristas a partir de las l�neas del fichero previamente tokenizada
	 * @param graph Grafo de entrada 
	 * @param ew Funci�n que proporciona el peso de la arista. Debe ser distinto de null si el grafo es 
	 * de tipo WeightedGraph&lt;V,E&gt;	 
	 * @return Grafo con la informaci�n del fichero
	 * @param <V> El tipo de los v�rtices
	 * @param <E> El tipo de las aristas
	 * @throws IllegalArgumentException Si no encuentra el fichero o si el fichero tiene una estructura no adecuada
	 */
	public static <V, E> Graph<V, E> newGraph(String file,
			StringVertexFactory<V> vf, StringEdgeFactory<V, E> ef,
			Graph<V, E> graph,
			EdgeWeight<E> ew) {

		Map<String, V> idVertices = new HashMap<>();
		Graph<V, E> ret = graph;
		Graph<V,E> wg = null;
		if (ew!=null) wg = graph;
		Scanner sc = null;
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException("Fichero " + file + " no encontrado");
		}

		List<String> filas = new ArrayList<>();

		while (sc.hasNext()) {
			filas.add(sc.nextLine());
		}

		sc.close();
		
		try {
			List<String> vertices = filas.subList(1, filas.indexOf("#EDGE#"));
			List<String> aristas = filas.subList(filas.indexOf("#EDGE#") + 1, filas.size());

			for (String verticeStr : vertices) {
				String[] vertice = eliminaBlancos(verticeStr.split(","));

				if (vertice.length == 0) {
					throw new IllegalArgumentException(
							"El fichero de entrada de vertices no es correcto. Linea: "
									+ verticeStr);
				}
				V vertex = vf.createVertex(vertice);
				ret.addVertex(vertex);
				idVertices.put(vertice[0], vertex);
			}
			
			for (String aristaStr : aristas) {
				String[] arista = eliminaBlancos(aristaStr.split(","));
				
				if (arista.length < 2)
					throw new IllegalArgumentException("El n�mero de v�rtices de la arista no es correcto");

				if (arista.length == 2) {
					ret.addEdge(idVertices.get(arista[0]),
							idVertices.get(arista[1]));
				} else if (arista.length > 2) {
					E edge = ef.createEdge(idVertices.get(arista[0]),
							idVertices.get(arista[1]), arista);
					ret.addEdge(idVertices.get(arista[0]),
							idVertices.get(arista[1]), edge);
					if(wg!=null){
						wg.setEdgeWeight(edge, ew.getWeight(edge));
					}
				}
			}

			return ret;

		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"La composici�n del fichero no es correcta");
		}
	}
}
