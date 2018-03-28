package us.lsi.ag.agchromosomes;

import java.util.List;






import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.BinaryChromosome;



import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;

import us.lsi.ag.IndexChromosome;
import us.lsi.ag.ProblemAG;
import us.lsi.ag.IndexProblemAG;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


/**
 * 
 * 
 * 
 * 
 * @author Miguel Toro
 * 
 * 
* <p> Una implementaci�n del tipo IndexChromosome. Toma como informaci�n la definici�n de un problema que implementa el interfaz IndexProblemAG.
 * A partir de esa informaci�n construye una secuencia normal. 
 * Asumimos que el n�mero de objetos es <code>n </code>y el tama�o de la secuencia normal <code>r</code>. 
 * Lista decodificada es una permutaci�n de un subconjunto de la secuencia normal.</p>
 *  
 * <p> La lista decodificada est� formada, por tanto, 
 *  por una lista de  tama�o menor o igual a <code>r</code>, cuyos valores son 
 * �ndices en el rango <code> [0,n-1]</code>, y cada �ndice <code>i</code> se  repite un n�mero de veces igual o menor 
 * al dado por la multiplicidad m�xima del objeto <code> i </code>
 * definida en el problema. </p>
 * 
 * <p> La implementaci�n usa un cromosoma de clave aleatoria de tama�o <code> r </code> y oro binario del 
 * mismo tama�o.
 * Es un cromosoma adecuado para codificar problemas de subcojuntos de permutaciones.</p>
 */
public class IndexPermutationSubListChromosome extends org.apache.commons.math3.genetics.Chromosome 

							implements IndexChromosome {

	public static List<Integer> normalSequence;
	
	public static IndexProblemAG<?> problema;
	
	/**
	 * Dimensi�n del cromosoma
	 */
	
	protected static int DIMENSION;
	
	
	public static void iniValues(ProblemAG problema){
		IndexPermutationSubListChromosome.problema = (IndexProblemAG<?>) problema; 
		IndexPermutationSubListChromosome.normalSequence = IndexPermutationSubListChromosome.problema.getNormalSequence();
		IndexPermutationSubListChromosome.DIMENSION = IndexPermutationSubListChromosome.normalSequence.size();
	}
	
	private BinaryChromosome2 binary;
	private RandomKey2 randomKey;	
	
	
	
	/**
	 * @param binary Un cromosoma binario
	 * @param randomKey Un cromosoma randomKey
	 */
	public IndexPermutationSubListChromosome(Chromosome binary, Chromosome randomKey) {
		super();
		if(binary instanceof BinaryChromosome2){
			this.binary = (BinaryChromosome2)binary;
		}
		if(randomKey instanceof RandomKey2){
			this.randomKey = (RandomKey2) randomKey;
		}
		Preconditions.checkArgument(this.binary!=null);
		Preconditions.checkArgument(this.randomKey!=null);
		Preconditions.checkArgument(this.binary.getLength()==this.randomKey.getLength());	
		this.ft = this.calculateFt();
	}
	
	/**
	 * Un constructor adecuado para crear un objeto por defecto de este tipo
	 */
	public IndexPermutationSubListChromosome() {
		super();
		List<Integer> ls1 = BinaryChromosome.randomBinaryRepresentation(100);
		List<Double>  ls2 = RandomKey.randomPermutation(100);
		BinaryChromosome2 c1 = new BinaryChromosome2(ls1);
		RandomKey2 c2 = new RandomKey2(ls2);
		this.binary =  c1;
		this.randomKey = c2;	
		this.ft = 0.;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.math3.genetics.Fitness#fitness()
	 */
	
	
	@Override
	public double fitness() {
		return ft;
	}
	
	private Double ft;
	
	private double calculateFt(){
		return IndexPermutationSubListChromosome.problema.fitnessFunction(this);
	}
	
	@Override
	public IndexProblemAG<?> getProblem() {
		return IndexPermutationSubListChromosome.problema;
	}

	public Integer getObjectsNumber() {
		return IndexPermutationSubListChromosome.problema.getObjectsNumber();
	}

	public Integer getMax(int i) {
		return IndexPermutationSubListChromosome.problema.getMaxMultiplicity(i);
	}
	
	
	@Override
	public int compareTo(Chromosome another) {
		if (!(another instanceof IndexPermutationSubListChromosome))
			throw new IllegalArgumentException();;
		IndexPermutationSubListChromosome other = (IndexPermutationSubListChromosome) another;
		Double f1 = this.fitness();
		Double f2 = other.fitness();
		return f1.compareTo(f2);
	}
	
	/**
	 * @return Una lista de enteros obtenida permutando la secuencia normal (0, 1, 2, ..., r-1) filtrada para incluir 
	 * s�lo los seleccionados por el cromosoma binario 
	 */
	@Override
	public List<Integer> decode() {	
		List<Integer> rk = randomKey.decode(normalSequence);
		List<Integer> r = Lists.newArrayList();
		List<Integer> bn = binary.getRepresentation();
		Preconditions.checkArgument(rk.size()==bn.size());
		for(int i=0; i< rk.size();i++){
			if(bn.get(i)==1){
				r.add(rk.get(i));
			}
		}
		return r;
	}

	/**
	 * @return La dimensi�n del cromosoma
	 */
	public int getLength() {
		return randomKey.getLength();
	}	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IndexPermutationSubListChromosome))
			return false;
		IndexPermutationSubListChromosome other = (IndexPermutationSubListChromosome) obj;
		if (binary == null) {
			if (other.binary != null)
				return false;
		} else if (!binary.equals(other.binary))
			return false;
		if (randomKey == null) {
			if (other.randomKey != null)
				return false;
		} else if (!randomKey.equals(other.randomKey))
			return false;
		return true;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((binary == null) ? 0 : binary.hashCode());
		result = prime * result
				+ ((randomKey == null) ? 0 : randomKey.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.fitness()+","+this.decode();
	}

	
	
	public BinaryChromosome getBinary() {
		return binary;
	}

	public RandomKey<Integer> getRandomKey() {
		return randomKey;
	}
	
	/**
	 * @param dimension La dimensi�n del cromosoma
	 * @pre Debe estar inicializada la propiedad factory
	 * @return Un cromosoma mixto aleatorio
	 * 
	 */
	private static IndexPermutationSubListChromosome random(Integer dimension){
		List<Integer> ls1 = BinaryChromosome.randomBinaryRepresentation(dimension);
		List<Double>  ls2 = RandomKey.randomPermutation(dimension);
		BinaryChromosome2 c1 = new BinaryChromosome2(ls1);
		RandomKey2 c2 = new RandomKey2(ls2);
		return new IndexPermutationSubListChromosome(c1, c2);
	}
	
	public static IndexPermutationSubListChromosome getInitialChromosome() {
		return IndexPermutationSubListChromosome.random(IndexPermutationSubListChromosome.DIMENSION);
	}

	private static class BinaryChromosome2 extends BinaryChromosome {		

		public BinaryChromosome2(Integer[] representation)
				throws InvalidRepresentationException {
			super(representation);
		}

		public BinaryChromosome2(List<Integer> representation)
				throws InvalidRepresentationException {
			super(representation);
		}

		@Override
		public double fitness() {
			return 0;
		}

		@Override
		public AbstractListChromosome<Integer> newFixedLengthChromosome(List<Integer> ls) {
			return new BinaryChromosome2(ls);
		}
		 
		@Override
		public List<Integer> getRepresentation(){
			return super.getRepresentation();
		}	
	}
	
	private static class RandomKey2 extends  RandomKey<Integer> {
		
		
		public RandomKey2(Double[] representation) throws InvalidRepresentationException {
			super(representation);
		}

		public RandomKey2(List<Double> representation) throws InvalidRepresentationException {
			super(representation);
		}

		@Override
		public double fitness() {
			return 0;
		}

		@Override
		public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> ls) {
			return new RandomKey2(ls);
		}
	}

	

	
	
	
	
}
