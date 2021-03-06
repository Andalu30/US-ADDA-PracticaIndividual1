/*
 * @author Juan Arteaga Carmona (juan.arteaga41567@gmail.com) - US: juaartcar
 */

package andalu30.PracticaIndividual1;

import java.util.List;
import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class ProblemaBaloncestoAG implements ValuesInRangeProblemAG<Integer, List<Integer>> {
	private List<Jugador> jugadores;
	private Integer  presupuesto = 10;
	private Integer seleccionarJugadores = 7;
	
	public static ProblemaBaloncestoAG create(String fichero) {
		return new ProblemaBaloncestoAG(fichero);
	}
	
	private ProblemaBaloncestoAG(String fichero) {
		this.jugadores = MetodosAuxiliares.getJugadoresDesdeArchivo(fichero);
	}
	

	@Override
	public Integer getVariableNumber() {
		return jugadores.size();
	}
	
	
	//Al ser binario no deberia de hacer falta, pero me obliga a usarlo...
	@Override
	public Integer getMax(Integer i) {
		return 1;
	}

	@Override
	public Integer getMin(Integer i) {
		return 0;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) { 
		Double v = 0.;
		Double k = 10.*this.jugadores.size();
		
		
		//V
		for (int i = 0; i < cr.decode().size(); i++) {
			if (cr.decode().get(i)==1) {
				v += jugadores.get(i).getValorCortos()+jugadores.get(i).getValorLargos();
			}
		}
		
		
		//Numero de jugadores!
		int contAux = 0;
		for (Integer i : cr.decode()){
			if (i==1) {
				contAux++;
			}
		}
		if (contAux!=seleccionarJugadores) {
			v=-k;
		}
		
		//Presupuesto
		Double r1 = 0.;
		for (int i = 0; i < cr.decode().size(); i++) {
			if (cr.decode().get(i)==1) {
				r1 += jugadores.get(i).getCache();
			}
		}
		if (!(r1 > presupuesto)) {
			r1 = 0.;
		}
				
		//Solo un base
		Double r2 = 0.;
		for (int i = 0; i < cr.decode().size(); i++) {
			if (cr.decode().get(i)==1 && (jugadores.get(i).getPos1().equals("Base") || jugadores.get(i).getPos2().equals("Base") )) {
				r2++;
			}
		}
		if (r2 == 1) {
			r2 = 0.;
		}else {
			;
		}
		
		//Al menos dos pivots
		Double r3 = 0.;
		for (int i = 0; i < cr.decode().size(); i++) {
			if (cr.decode().get(i)==1 && (jugadores.get(i).getPos1().equals("Pivot") || jugadores.get(i).getPos2().equals("Pivot") )) {
				r3++;
			}
		}
		if (r3 >= 2) {
			r3 = 0.;
		}else {
			r3 = -r3;
		}
		
		//Al menos tres aleros
		Double r4 = 0.;
		for (int i = 0; i < cr.decode().size(); i++) {
			if (cr.decode().get(i)==1 && (jugadores.get(i).getPos1().equals("Alero") || jugadores.get(i).getPos2().equals("Alero") )) {
				r4++;
			}
		}
		if (r4 >= 0) {
			r4 = 0.;
		}else {
			r4 = -r4;
		}
		
		Double r = r1+r2+r3+r4;
				
		Double fitness = v - (r*k);
		//System.out.println(fitness);
		
		return fitness;
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return cr.decode();
	}


	
}
