/*
 * @author Juan Arteaga Carmona (juan.arteaga41567@gmail.com) - US: juaartcar
 */

package andalu30.PracticaIndividual1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;


public class TestAG {


	public static void main(String[] args){
		
		System.out.println("Ejecutando el algoritmo genetico. Por favor, espere.");

		
		AlgoritmoAG.POPULATION_SIZE = 10000;
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		
		AlgoritmoAG.ELITISM_RATE  = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		StoppingConditionFactory.FITNESS_MIN = 46.;
		
		
		ProblemaBaloncestoAG p = ProblemaBaloncestoAG.create("ficheros/suplentes.txt");
		
		AlgoritmoAG  ap = new AlgoritmoAG(ChromosomeType.Binary, p);
		
		ap.ejecuta();
		
		System.out.println("Algoritmo genetico ejecutado. Estas son las estadisticas:");
		
		ValuesInRangeChromosome<Integer> cr = ChromosomeFactory.asValuesInRange(ap.getBestFinal());
		System.out.println("Fitness del mejor cromosoma:      "+ p.fitnessFunction(cr));
		System.out.println("Solucion del algoritmo genetico: "+p.getSolucion(cr));
		
		List<Jugador> jugadores = MetodosAuxiliares.getJugadoresDesdeArchivo("ficheros/suplentes.txt");
		List<String> nombresSolucion = new ArrayList<>();
		
		for (int i=0; i<cr.decode().size();i++) {
			if (cr.decode().get(i).equals(1)) {
				nombresSolucion.add(jugadores.get(i).getNombre());
			}
		}
		System.out.println("\nInterpretacion de la solucion:\nSe deben de seleccionar los siguientes jugadores: "+nombresSolucion);	
		
	}

}
