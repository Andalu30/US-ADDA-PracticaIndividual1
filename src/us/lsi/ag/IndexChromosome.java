package us.lsi.ag;

import java.util.List;

/**
 * @author Miguel Toro
 * 
 * <p> Un cromosoma adecuado para resolver problemas cuya soluci�n es un Multiset o una lista, posiblemente con elementoss repetidos,
 * formados con elementos de un conjunto dado </p>
 *
 */
public interface IndexChromosome extends Chromosome<List<Integer>> {
	/**
	 * @return El problema a resolver
	 */
	IndexProblemAG<?> getProblem();
	
}
