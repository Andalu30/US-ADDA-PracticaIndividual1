package us.lsi.dyv;

import java.util.List;


/**
 * @author Miguel Toro
 *
 * @param <S> El tipo de la soluci�n
 * @param <E> El tipo de las soluciones intermedias
 */
public interface ProblemaDyV<S,E> {	
	/**
	 * @constraint El tama�o de un problema debe ser mayor que el de cada uno de sus subproblemas
	 * @return El tama�o de un problema
	 */
	int size();
	/**
	 * @return Si el problema es un caso base
	 */
	boolean esCasoBase();
	/**
	 * @return La soluci�n del caso base
	 */
	E getSolucionCasoBase();
	/**
	 * @constraint Si todos los valores en ls son null la soluci�n devuelta es null
	 * @pre ls!=null
	 * @param ls - Soluciones de los subproblemas alcanzados tras tomar las diferentes alterantivas
	 * @return La soluci�n del problema
	 */
	E combina(List<E> ls);
	/**
	 * @pre 0 &lt; = i &lt; getNumeroSubProblemas(a)
	 * @param i - N�mero del subproblema
	 * @return Subproblema
	 */
	ProblemaDyV<S,E> getSubProblema(int i);
	/**
	 * @return N�mero de subproblemas
	 */
	int getNumeroDeSubProblemas();	
	/**
	 * @param e - Soluci�n parcial
	 * @return Soluci�n construida a partir de la soluci�n parcial
	 */
	S getSolucion(E e);
}
