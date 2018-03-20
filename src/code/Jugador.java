package code;

public class Jugador {
	int id;
	String nombre;
	String pos1;
	String pos2;
	int cache;
	String nacion;
	int minJugados;
	int valorCortos;
	int valorLargos;
	
	
	
	public Jugador(int id, String nombre, String pos1, String pos2, int cache, String nacion, int minJugados,
			int valorCortos, int valorLargos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.cache = cache;
		this.nacion = nacion;
		this.minJugados = minJugados;
		this.valorCortos = valorCortos;
		this.valorLargos = valorLargos;
	}



	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public String getPos1() {
		return pos1;
	}



	public String getPos2() {
		return pos2;
	}



	public int getCache() {
		return cache;
	}



	public String getNacion() {
		return nacion;
	}



	public int getMinJugados() {
		return minJugados;
	}



	public int getValorCortos() {
		return valorCortos;
	}



	public int getValorLargos() {
		return valorLargos;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cache;
		result = prime * result + id;
		result = prime * result + minJugados;
		result = prime * result + ((nacion == null) ? 0 : nacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pos1 == null) ? 0 : pos1.hashCode());
		result = prime * result + ((pos2 == null) ? 0 : pos2.hashCode());
		result = prime * result + valorCortos;
		result = prime * result + valorLargos;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (cache != other.cache)
			return false;
		if (id != other.id)
			return false;
		if (minJugados != other.minJugados)
			return false;
		if (nacion == null) {
			if (other.nacion != null)
				return false;
		} else if (!nacion.equals(other.nacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pos1 == null) {
			if (other.pos1 != null)
				return false;
		} else if (!pos1.equals(other.pos1))
			return false;
		if (pos2 == null) {
			if (other.pos2 != null)
				return false;
		} else if (!pos2.equals(other.pos2))
			return false;
		if (valorCortos != other.valorCortos)
			return false;
		if (valorLargos != other.valorLargos)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", pos1=" + pos1 + ", pos2=" + pos2 + ", cache=" + cache
				+ ", nacion=" + nacion + ", minJugados=" + minJugados + ", valorCortos=" + valorCortos
				+ ", valorLargos=" + valorLargos + "]\n";
	}
	
	
	
	
	
	
}
