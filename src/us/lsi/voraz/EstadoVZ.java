package us.lsi.voraz;

/**
*<p> Tipo mutable del estado de un problema que se quiere resolver por una t�cnica Voraz. 
 * Cada estado est� asociado un problema, del conjunto de problemas considerado, m�s el camino, secuencia de alternativas, para llegar a �l desde el problema inicial. </p>
 * 
 * <p>La documentaci�n puede encontarse en el: <a href="../../../document/Tema15.pdf" target="_blank">Tema15</a></p>
*
*
* @param <E> Tipo del Estado 
* @param <S> Tipo de la soluci�n del problema
* @param <A> Tipo de las alternativas
*/
public interface EstadoVZ<E extends EstadoVZ<E,S,A>,S,A> {	
	/**
	 * @param a - Alternativa elegida
	 * @return Siguiente estado si se escoge la alternativa a
	 */
	E next(A a);	
	/**
	 * @return La alternativa escogida en el estado actual. 
	 */
	A getAlternativa();
	/**
	 * @return Soluci�n del problema calculada a partir del estado actual en el estado que cumple la condici�n de parada o null si no hay soluci�n
	 */
	S getSolucion();
	/**
	 * La condici�n de parada establece cuando se parar� el algoritmo. Esta condici�n se puede establecer a partir
	 * de la propiedades fijadas para el algoritmo: n�mero de soluciones encontradas, n�mero de iteraciones, etc.
	 * 
	 * @return Si se cumple la condici�n del parada del algortimo.
	 */
	boolean condicionDeParada();
}

