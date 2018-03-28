package us.lsi.ag;

import java.util.List;

import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.MutationPolicy;

import us.lsi.ag.agchromosomes.ListIntegerChromosome;

/**
 * @author Miguel Toro
 *
 * @param <S>
 * 
 * <p> Es un cromosoma general para trabajar con problemas cuyas soluciones pueden ser modeladas por listas de enteros.</p>
 */
public interface ProblemaAGListInteger<S> extends ProblemAG {

	/**
	 * @return La dimensi�n de la lista de enteros
	 */
	int getDimension();

	/**
	 * @param listIntegerChromosome Un cromosoma
	 * @return la funci�n de fitness
	 */
	double fitnessFunction(ListIntegerChromosome listIntegerChromosome);

	/**
	 * @return Una lista aleatoria de enteros de tama�o getDimensi�n() que cumpla los requisitos del problema 
	 */
	List<Integer> getRandomList();
	
	/**
	 * @return El operador de Mutaci�n usado
	 */
	MutationPolicy getMutationPolicy();

	/**
	 * @return El operador de mezcla (crossover)
	 */
	CrossoverPolicy getCrossoverPolicy();
	
	/**
	 * @param c Un cromosoma
	 * @return La soluci�n asociada
	 */
	S getSolucion(ListIntegerChromosome c);
}
