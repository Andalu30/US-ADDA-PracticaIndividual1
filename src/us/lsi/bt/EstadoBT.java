package us.lsi.bt;

import java.util.List;


/**
 * <p> Tipo mutable del estado de un problema que se quiere resolver por la t�cnica de Bactracking. 
 * Cada estado est� asociado un problema, del conjunto de problemas considerado, m�s el camino, secuencia de alternativas, para llegar a �l desde el problema inicial. </p>
 * 
 * <p>La documentaci�n puede encontarse en el: <a href="../../../document/Tema15.pdf" target="_blank">Tema15</a></p>
 *
 * @author Miguel Toro
 *
 * @param <S> El tipo de la soluci�n
 * @param <A> El tipo de la alternativa
 */
public interface EstadoBT<S extends SolucionBT, A> {
	
	
	public enum Tipo{Min,Max,TodasLasSoluciones,AlgunasSoluciones}
	
	/**
	 * @return El tipo del problema: minimiza, maximiza o busca un valor que involucra a todas las alternativas
	 *
	 * 
	 **/
	Tipo getTipo();	
	/**
	 * @return El estado inicial
	 */
	EstadoBT<S,A> getEstadoInicial();	
	/**
	 * @param a - alternativa escogida para cambiar el estado
	 * @return El estado siguiente seg�n la alternativa a
	 */
	EstadoBT<S, A> avanza(A a);
	/**
	 * @param a - alternativa a eliminar para restaurar el estado anterior
	 * @return El estado anterior
	 */
	EstadoBT<S, A> retrocede(A a);
	/**
	 * @return Tama�o del problema al que est� asociado el estado
	 */
	int size();
	/**
	 * @return Si el problema est� fuera de rango y su soluci�n es null
	 */
	default boolean estaFueraDeRango(){
		return false;
	}
	/**
	 * @return Si el problema asociado al estado es un caso base
	 */
	boolean esCasoBase();
	/**
	 * @return Las alternativas admisibles para el problema asociado
	 */
	List<A> getAlternativas();
	/**
	 * <p> Para poder calcular la soluci�n del problema inicial en el estado final es muy recomendable que el estado disponga de una propiedad de tipo List&lt;A&gt; 
	 * que almacena se secuencia de alternativas tomadas. </p>.
	 * 
	 * @pre esCasoBase()
	 * @return Una soluci�n del problema inicial y null si no hay soluci�n para el problema inicial tras la secuencia de alternativas tomadas
	 * @throws IllegalStateException - Si se invoca el m�todo en un estado no final
	 */
	S getSolucion();
	
	/**
	 * @pre El m�todo s�lo es relevante si se usa la t�cnica con filtro
	 * @pos 
	 * <ul>
	 * <li> Si el problema es de minimizaci�n el valor debe ser 
	 * una cota inferior del valor de la propiedad objetivo del problema inicial, 
	 * asumiendo que se ha alcanzado el problema actual y se  va a seguir la alternativa <code> a </code>.
	 * La alternativa puede ser descartada si <code> getObjetivoEstimado(a) &gt; =  AlgoritmoPD.getMejorValor() </code>,
	 * puesto que el valor objetivo de la soluci�n obtnida estar�a por encima del mejor valor obtenido hasta el momento.
	 * Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a) &lt;   AlgoritmoPD.getMejorValor() </code>.
	 * Donde <code> AlgoritmoPD.getMejorValor() </code> es el mejor valor encontrado por el algoritmo hasta ahora.
	 * <li> Si el problema original fuera de maximizaci�n el valor debe ser 
	 * una cota superior del valor de la propiedad objetivo del problema inicial, 
	 * asumiendo que se ha alcanzado el problema actual y se  va a seguir la alternativa <code> a </code>.
	 *  Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a) &gt;   AlgoritmoPD.getMejorValor() </code>.
	 * </ul>
	 * @param a - Alternativa elegida
	 * @return El valor objetivo estimado si seguimos <code> a </code>
	 */
	default Double getObjetivoEstimado(A a){
		return null;
	}
	
}
