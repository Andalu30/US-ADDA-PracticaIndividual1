package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import test.test1;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.AlgoritmoPLI;

public class ProblemaBaloncesto {

	public static void main(String[] args) {	
		
		Integer presupuesto = 10;
		Integer seleccionarJugadores = 7;
		
		List<Jugador> jugadores = getJugadoresDesdeArchivo("DatosProblema.txt");

		
		
		
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
			System.out.println(jugadores.get(i));
			System.out.println(jugadores.get(i).getPos1());
			
			if( jugadores.get(i).getPos1().equals("Pivot") || jugadores.get(i).getPos2().equals("Pivot")) {
				r += "x"+i+"+";
			}
		}
		r += "0 >= 2;\n";
		
		
		//Aleros
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println(jugadores.get(i));
			System.out.println(jugadores.get(i).getPos1());
			
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
			PrintWriter out = new PrintWriter("ArchivoLPSolveGenerado.txt");
			out.print(r);
			out.close();
		} catch (Exception e) {
			System.err.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
		}
		
		
		
//-------------------------------------------------------------------

		
		
		
		AlgoritmoPLI a = Algoritmos.createPLI("ArchivoLPSolveGenerado.txt");
		a.ejecuta();
		
		List<String> Mapsolucion = new ArrayList();
		
		double[] solucion = a.getSolucion();
		for (int i=0;i<solucion.length;i++) {
//			System.out.println(solucion[i]);
			if (solucion[i]==1.) {
				Mapsolucion.add(jugadores.get(i).getNombre());
				System.out.println("El jugador "+jugadores.get(i)+" es escogido.");
			}
		}
		System.out.println(Mapsolucion);
	}

	public static List<Jugador> getJugadoresDesdeArchivo(String path){
		List<Jugador> res = new ArrayList<>();
	
		try {
			File archivo = new File(path);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			// Lectura del fichero
	        String linea;
	        String[] div = null;
			while((linea=br.readLine())!=null) {
	        	div = linea.split(",");
				res.add(new Jugador(new Integer(div[0]), div[1],div[2],div[3],new Integer(div[4]),div[5],new Integer(div[6]),new Integer(div[7]),new Integer(div[8])));
			}
			fr.close();
			
		} catch (Exception e) {
			System.err.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
		}
        
		return res;
	}
	
}
