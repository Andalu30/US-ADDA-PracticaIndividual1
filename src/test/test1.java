package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import code.Jugador;

public class test1 {

	public static void main(String[] args) {
		
		List<Jugador> listaJugadores = getJugadoresDesdeArchivo("DatosProblema.txt");
		System.out.println(listaJugadores);
		
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
