package us.lsi.ag.agchromosomes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

import us.lsi.ag.ProblemAG;
import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.math.Math2;

/**
 * @author Miguel Toro
 * 
 * 
 * <p> Una implementaci�n del tipo ValuesInRangeCromosome&lt;Integer&gt;. Toma como informaci�n la definici�n de un problema que implementa el interfaz 
 * ValuesInRangeProblemAG. </p>
 * 
 * <p> Asumimos que el n�mero de varibles es n. La lista decodificada est� formada por una lista de  
 * enteros de tama�o n cuyos elementos para cada i son 
 * valores en en rango [getMin(i),getMax(i)]. </p>
 * 
 * <p> La implementaci�n usa un cromosoma binario del tama�o n*nbits. 
 * Siendo nbits el n�mero de bits usados para representar cada uno de los enteros. </p>
 * 
 * <p> Es un cromosoma adecuado para codificar problemas de subconjuntos de multiconjuntos</p>
 *
 */
public class RangeChromosome extends BinaryChromosome implements ValuesInRangeChromosome<Integer> {
	
	/**
	 * N�mero de bits usado para representar un entero. El rango de enteros que podemos obtener depender� de este n�mero de bits.
	 */
	public static Integer bitsNumber = 5;
	
	public static ValuesInRangeProblemAG<Integer,?> problem;
	
	/**
	 * Dimensi�n del cromosoma igual a bitsNumber*getVariableNumber()
	 */
	
	public static int DIMENSION;
	
	@SuppressWarnings("unchecked")
	public static void iniValues(ProblemAG problema){
		RangeChromosome.problem = (ValuesInRangeProblemAG<Integer,?>) problema; 
		RangeChromosome.DIMENSION = RangeChromosome.bitsNumber*RangeChromosome.problem.getVariableNumber();
	}
	
	private static Integer pow = Math2.pow(2., bitsNumber).intValue();
	
	public RangeChromosome(Integer[] representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	public RangeChromosome(List<Integer> representation) throws InvalidRepresentationException {
		super(representation);
		this.ft = this.calculateFt();
	}

	@Override
	public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> ls) {
		return new RangeChromosome(ls);
	}
	
	@Override
	public List<Integer> decode() {
		List<Integer> ls = super.getRepresentation();
		List<Integer> r = new ArrayList<Integer>();
		int index1 = 0;
		for(int i = 0; i < getObjectsNumber(); i++){			
			int index2 = index1+bitsNumber;
			Integer e = Math2.decode(ls.subList(index1, index2));
			Integer d = getMin(i)+Math2.escalaIncluded(e, pow, getMax(i)-getMin(i));
			r.add(d);
			index1 = index1+bitsNumber;;
		}
		return r;
	}

	@Override
	public List<Integer> getRepresentation(){
		return super.getRepresentation();
	}
	
	public static RangeChromosome getInitialChromosome() {
		List<Integer> ls = BinaryChromosome.randomBinaryRepresentation(RangeChromosome.DIMENSION);
		return new RangeChromosome(ls);
	}

	@Override
	public double fitness() {
		return ft;
	}
	
	private double ft;
	
	private double calculateFt(){
		return RangeChromosome.problem.fitnessFunction(this);
	}

	public Integer getObjectsNumber() {
		return RangeChromosome.problem.getVariableNumber();
	}

	public Integer getMax(int i) {
		return RangeChromosome.problem.getMax(i);
	}

	public Integer getMin(int i) {
		return RangeChromosome.problem.getMin(i);
	}
	
	public ValuesInRangeProblemAG<Integer,?> getProblem() {
		return RangeChromosome.problem;
	}
	
}
