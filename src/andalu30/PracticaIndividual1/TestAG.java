package andalu30.PracticaIndividual1;

import java.util.stream.Collectors;

import us.lsi.ag.ValuesInRangeChromosome;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

import us.lsi.algoritmos.Algoritmos;

public class TestAG {


	public static void main(String[] args){

		AlgoritmoAG.ELITISM_RATE  = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		StoppingConditionFactory.FITNESS_MIN = 50.;
		
		
		ProblemaBaloncestoAG p = ProblemaBaloncestoAG.create("ficheros/suplentes.txt");
		
		AlgoritmoAG  ap = new AlgoritmoAG(ChromosomeType.Binary, p);
		
		ap.ejecuta();
		
		System.out.println("================================");
		ValuesInRangeChromosome<Integer> cr = ChromosomeFactory.asValuesInRange(ap.getBestFinal());
		System.out.println("Fitness del mejor cromosoma:      "+ p.fitnessFunction(cr));
		System.out.println(cr.decode());
		System.out.println("================================");

		
	}	

}
