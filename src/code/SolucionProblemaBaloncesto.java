package code;

import java.util.List;

public class SolucionProblemaBaloncesto {
	List<Jugador> jugadores;

	public void anyadirJugadorSolucion(Jugador j) {
		jugadores.add(j);
	}

	public List<Jugador> getSolucion(){
		return jugadores;
	}

	@Override
	public String toString() {
		return "SolucionProblemaBaloncesto [jugadores=" + jugadores + "]";
	}
	
	
	
}
