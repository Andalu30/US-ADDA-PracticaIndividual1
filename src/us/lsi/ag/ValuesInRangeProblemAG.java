package us.lsi.ag;

/**
 * @author Miguel Toro
 *
 * @param <E> El tipo de los elementos del cromosoma. El cromosoma es del tipo List&lt;E&gt;
 * @param <S> El tipo de soluci�n del problema
 */
public interface ValuesInRangeProblemAG<E,S> extends ProblemAG {

	/**
	 * @return N�mero de variables.
	 */
	Integer getVariableNumber();
	
	/**
	 * @pre 0 &le; i &lt; 
	 * @param i Un entero getVariableNumber()
	 * @return El m�ximo valor del rango de valores de la variable i
	 */
	E getMax(Integer i);
	/**
	 * @pre 0 &le; i &lt; 
	 * @param i Un entero getVariableNumber()
	 * @return El m�nimo valor del rango de valores de la variable i
	 */
	E getMin(Integer i);
	
	/**
	 * @param cr Un cromosoma
	 * @return La funci�n de fitness del cromosoma
	 */
	
	Double fitnessFunction(ValuesInRangeChromosome<E> cr);
	
	/**
	 * @param cr Un cromosoma
	 * @return La soluci�n definida por el cromosoma
	 */
	S getSolucion(ValuesInRangeChromosome<E> cr);
}
