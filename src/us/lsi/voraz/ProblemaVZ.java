package us.lsi.voraz;


/**
*
* @param <E> Tipo del estado
* @param <S> Tipo de la soluci�n
* @param <A> Tipo de las alternativas
*/
public interface ProblemaVZ<E extends EstadoVZ<E,S,A>,S,A> {
	/**
	 * @return El estado inicial
	 */
	E getEstadoInicial();
}

