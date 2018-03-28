package andalu30.PracticaIndividual1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.AlgoritmoPLI;

public class ProblemaBaloncestoPLI {

	public static void main(String[] args) {

		Integer presupuesto = 10;
		Integer seleccionarJugadores = 7;

		List<Jugador> jugadores = MetodosAuxiliares.getJugadoresDesdeArchivo("ficheros/suplentes.txt");

		//Inicializacion de la string
		String r = "";
		r = r+"max: ";

		//Funcion objetivo. Variables y pesos
		for(int i =0;i<jugadores.size();i++){
			if (i!=0) r +=" + ";
			r += jugadores.get(i).getValorCortos()+jugadores.get(i).getValorLargos() + "*x"+i;
		}
		r += ";\n\n";

		//Restricciones.
		//Suma de cache<= presupuesto
				for (int i = 0; i < jugadores.size(); i++) {
					if (i!=0) r = r+"+";
					r += jugadores.get(i).getCache()+"*x" + i;
				}
				r+= " <= "+presupuesto+";\n";

		//Seleccionar solamente n jugadores
		for (int i = 0; i < jugadores.size(); i++) {
			if (i!=0) r = r+"+";
			r += "x" + i;
		}
		r += " = "+seleccionarJugadores+";\n";

		//Solo un base
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getPos1().equals("Base")|| jugadores.get(i).getPos2().equals("Base")) {
				r += "x"+i+"+";
			}
		}
		r += "0 = 1;\n";


		//Pivots
		for (int i = 0; i < jugadores.size(); i++) {
			if( jugadores.get(i).getPos1().equals("Pivot") || jugadores.get(i).getPos2().equals("Pivot")) {
				r += "x"+i+"+";
			}
		}
		r += "0 >= 2;\n";


		//Aleros
		for (int i = 0; i < jugadores.size(); i++) {
			if( jugadores.get(i).getPos1().equals("Alero") || jugadores.get(i).getPos2().equals("Alero")) {
				r += "x"+i+"+";
			}
		}
		r += "0 >= 3;\n\nbin ";

		//Declaracion variables binarias
		for (int i = 0; i < jugadores.size(); i++) {
			r+="x"+i+" ";
		}
		r+=";";
		//--------------------------------------------------
		//Guardar la string a un archivo

		try {
			PrintWriter out = new PrintWriter("ficheros/ArchivoLPSolveGenerado.txt");
			out.print(r);
			out.close();
		} catch (Exception e) {
			System.err.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
		}



//-------------------------------------------------------------------


		AlgoritmoPLI a = Algoritmos.createPLI("ficheros/ArchivoLPSolveGenerado.txt");

		List<String> NombresSolucion = new ArrayList<>();

		a.ejecuta();

		double[] solucion = a.getSolucion();
		for (int i=0;i<solucion.length;i++) {
			if (solucion[i]==1.) {
				NombresSolucion.add(jugadores.get(i).getNombre());
			}
		}


		//Impresiones por pantalla:
		System.out.println("Se ha generado un archivo llamado \"ArchivoLPSolveGenerado.txt\" que contiene la definición de la solución del problema en formato LPSolve");
		System.out.println("Este es el archivo con formato LPSolve:\n\n"+r);
		System.out.println("\n\nUna vez ejecutado, esta es la solución al problema (tambien se guardará en un archivo de texto):");
		System.out.println(NombresSolucion);
		
		
		try {
			PrintWriter out = new PrintWriter("ficheros/Solucion.txt");
			out.print(NombresSolucion);
			out.close();
		} catch (Exception e) {
			System.err.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
		}

		
	}

}
