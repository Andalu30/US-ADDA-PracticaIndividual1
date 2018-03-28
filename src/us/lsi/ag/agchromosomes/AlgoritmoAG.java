package us.lsi.ag.agchromosomes;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.ElitisticListPopulation;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.StoppingCondition;
import org.apache.commons.math3.random.JDKRandomGenerator;

import com.google.common.base.Preconditions;

import us.lsi.ag.ProblemAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.algoritmos.AbstractAlgoritmo;

/**
 * <p> Implementaci�n de un Algoritmo Gen�tico </p>
 * 
 * 
 * @author Miguel Toro
 *
 */
public class AlgoritmoAG extends AbstractAlgoritmo {

	
	/**
	 * Tama�o de la poblaci�n. Usualmente de un valor cercano a la DIMENSION de los cromosomas o mayor
	 */
	public static int POPULATION_SIZE = 30;
	
	/**
	 * Tasa de elitismo. El porcentaje especificado de los mejores cromosomas pasa a la siguiente generaci�n sin cambio
	 */
	public static double ELITISM_RATE = 0.2;
	
	/**
	 * Tasa de cruce: Indica con qu� frecuencia se va a realizar la cruce. 
	 * Si no hay un cruce, la descendencia es copia exacta de los padres. 
	 * Si hay un cruce, la descendencia est� hecha de partes del cromosoma de los padres. 
	 * Si la probabilidad de cruce es 100%, entonces todos los hijos se hacen mediante cruce de los padres
	 * Si es 0%, la nueva generaci�n se hace de copias exactas de los cromosomas de los padres.
	 * El cruce se hace con la esperanza de que los nuevos cromosomas tendr�n las partes buenas de los padres
	 * y tal vez los nuevos cromosomas ser�n mejores. Sin embargo, es bueno dejar una cierta parte de la poblaci�n sobrevivir a la siguiente generaci�n.
	 * 
	 * <br>
	 * Tasa de cruce. Valores usuales entre  0,.8 y 0.95
	 */
	
	public static double CROSSOVER_RATE = 0.8;
	
	/**
	 * La tasa de de mutaci�n indica con qu� frecuencia ser�n mutados cada uno de los cromosomas mutados. 
	 * Si no hay mutaci�n, la descendencia se toma despu�s de cruce sin ning�n cambio. 
	 * Si se lleva a cabo la mutaci�n, se cambia una parte del cromosoma. 
	 * Si probabilidad de mutaci�n es 100%, toda cromosoma se cambia, si es 0%, no se cambia ninguno. 
	 * La mutaci�n se hace para evitar que se caiga en un m�ximo local.
	 * 
	 * 
	 * Tasa de mutaci�n. Valores usales entre 0.5 y 1.
	 */
	public static double MUTATION_RATE = 0.6;
	

	public static long INITIAL_TIME;
	
	
	private ChromosomeType tipo;
	private CrossoverPolicy crossOverPolicy;	
	private MutationPolicy mutationPolicy;
	private SelectionPolicy selectionPolicy;
	protected StoppingCondition stopCond;
		
	

	/**
	 * Lista con los mejores cromosomas de cada una de la generaciones si se usa la condici�n de parada SolutionsNumbers.
	 * En otro caso null.
 	 */
	public static List<Chromosome> bestChromosomes;
	

	protected Population initialPopulation;
	
	
	protected Chromosome bestFinal;
	protected Population finalPopulation;
	
	
	
	/**
	 * @param tipo El tipo del chromosoma
	 * @param problema Problema a resolver
	 */
	public AlgoritmoAG(ChromosomeType tipo, ProblemAG problema) {
		super();
		this.tipo = tipo;				
		this.selectionPolicy =  ChromosomeFactory.getSelectionPolicy();
		this.mutationPolicy = ChromosomeFactory.getMutationPolicy(tipo, problema);
		this.crossOverPolicy = ChromosomeFactory.getCrossoverPolicy(tipo, problema);
		this.stopCond = StoppingConditionFactory.getStoppingCondition();
		ChromosomeFactory.iniValues(tipo, problema);
		JDKRandomGenerator random = new JDKRandomGenerator();		
		random.setSeed((int)System.currentTimeMillis());
		GeneticAlgorithm.setRandomGenerator(random);
	}

	/**
	 * Inicializa aleatoriamente la poblaci�n.
	 */
	private ElitisticListPopulation randomPopulation() {
		List<Chromosome> popList = new LinkedList<>();

		for (int i = 0; i < POPULATION_SIZE; i++) {
			Chromosome randChrom = ChromosomeFactory.randomChromosome(this.tipo);
			popList.add(randChrom);
		}
		return new ElitisticListPopulation(popList, popList.size(), ELITISM_RATE);
	}	

	/**
	 * Ejecuta el algoritmo
	 */
	public void ejecuta() {
		INITIAL_TIME = System.currentTimeMillis();
		this.initialPopulation = randomPopulation();
		Preconditions.checkNotNull(this.initialPopulation);		
		
		GeneticAlgorithm ga = new GeneticAlgorithm(
				crossOverPolicy, 
				CROSSOVER_RATE,
				mutationPolicy, 
				MUTATION_RATE, 
				selectionPolicy);
		
		
		this.finalPopulation = ga.evolve(this.initialPopulation, this.stopCond);		
		Preconditions.checkNotNull(this.finalPopulation);
		this.bestFinal = this.finalPopulation.getFittestChromosome();
	}

	/**
	 * @return Poblaci�n inicial
	 */
	public Population getInitialPopulation() {
		return initialPopulation;
	}

	/**
	 * @return El mejor cromosoma en la poblaci�n final
	 */
	public Chromosome getBestFinal() {
		return bestFinal;
	}

	/**
	 * @return Poblaci�n final
	 */
	public Population getFinalPopulation() {
		return finalPopulation;
	}	
	
}
