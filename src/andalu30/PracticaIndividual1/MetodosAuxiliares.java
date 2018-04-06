/*
 * @author Juan Arteaga Carmona (juan.arteaga41567@gmail.com) - US: juaartcar
 */

package andalu30.PracticaIndividual1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MetodosAuxiliares {

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
			System.err.println("Se ha procucido un error al abrir el archivo especificado en el path. "+e.getMessage());
		}

		return res;
	}
}
