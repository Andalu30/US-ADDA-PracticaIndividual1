package us.lsi.ag.agchromosomes;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

import us.lsi.ag.Chromosome;
import us.lsi.ag.ProblemAG;
import us.lsi.ag.ProblemaAGListInteger;

/**
 * @author Miguel Toro
 * 
 * <p> Una implementaci�n del tipo IChromosome&lt;Integer&gt;. Toma como informaci�n la definici�n de un problema que implementa el interfaz ProblemaAGListInteger. </p>
 *  
 * <p> La lista decodificada est� formada por una lista de  entero de tama�o r </p>
 * 
 * <p> La implementaci�n usa una lista de enteros de tama�o r.
 * Es un cromosoma general para trabajar con problemas cuyas soluciones pueden ser modeladas por listas de enteros. </p>
 *
 */

public class ListIntegerChromosome extends AbstractListChromosome<Integer> implements Chromosome<List<Integer>> {

	public static ProblemaAGListInteger<?> problema;
	
	/**
	 * Dimensi�n del cromosoma
	 */
	
	public static int DIMENSION;
	
	public static void iniValues(ProblemAG problema){
		ListIntegerChromosome.problema = (ProblemaAGListInteger<?>) problema; 
		ListIntegerChromosome.DIMENSION = ListIntegerChromosome.problema.getDimension();
	}
	
	public ListIntegerChromosome(List<Integer> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public ListIntegerChromosome(Integer[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}	
	
	@Override
	public List<Integer> decode() {
		return super.getRepresentation();
	}

	public static ListIntegerChromosome getInitialChromosome() {
		List<Integer> ls = ListIntegerChromosome.problema.getRandomList();
		return new ListIntegerChromosome(ls);
	}
	
	private double ft;
	
	private double calculateFt(){
		return ListIntegerChromosome.problema.fitnessFunction(this);
	}

	@Override
	public double fitness() {
		return ft;
	}

	@Override
	protected void checkValidity(List<Integer> arg0) throws InvalidRepresentationException {

	}

	@Override
	public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> ls) {
		return new ListIntegerChromosome(ls);
	}

	

}
