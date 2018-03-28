package us.lsi.ag;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Preconditions;

import us.lsi.common.Lists2;
/**
 * @author Miguel Toro
 *
 * @param <S> Tipo de la soluci�n
 * 
 * 
 * <p> Se parte de un conjunto de <code> n </code> objetos distintos y unas multiplicidades m�ximas para cada uno de ellos
 * A partir de esa informaci�n se construye la secuencia normal que asumimos de tama�o <code> r </code>. </p>
 * 
 * <p> La secuencia normal asociada al problema. Siendo <code> n </code> el n�mero de objetos la secuencia normal est� formado por la concatenaci�n de  
 * <code> n </code> sublistas <code> L(i) </code>. Cada <code> L(i) </code> est� formada por <code> getMax(i) </code> copias del entero <code> i </code>.
 * con <code> i </code> en el rango <code> 0..n-1 </code>. </p>
 *  
 * <p> Los problemas adecuados para ser modelados con este tipo son aquellos cuya soluci�n 
 * es un multiconjunto o una lista, posiblemente con repetici�n, de los objetos dados. </p>
 */
public interface IndexProblemAG<S> extends ProblemAG {	
	
	
	/**
	 * @return Numero de objetos distintos
	 */
	Integer getObjectsNumber();
	
	/**
	 * @pre <code> 0 &le; index &lt; getObjetos().size() </code>
	 * @param index Indice en la lista de objetos disponibles
	 * @return La multiplicidad m�xima del objeto de �ndice <code> index </code>. 
	 * La multiplicidad m�xima del objeto <code> i </code> estar� en el rango <code> 0..getMax(i) </code>
	 */
	
	default Integer getMaxMultiplicity(int index){
		Preconditions.checkElementIndex(index, this.getObjectsNumber());
		return 1;
	}
	
	
	
	/**
	 * @param cr Un cromosoma
	 * @return La funci�n de fitnes del cromosoma
	 */
	Double fitnessFunction(IndexChromosome cr);
	
	/**
	 * @param cr Un cromosoma
	 * @return La soluci�n definida por el cromosoma
	 */
	S getSolucion(IndexChromosome cr);
	
	
	
    /**
     * @return La secuencia normal asociada al problema. Siendo <code> n </code> el n�mero de objetos la secuencia normal est� formado por la concatenaci�n de  
     * <code> n </code> sublistas <code> L(i) </code>. Cada <code> L(i) </code> est� formada por <code> getMax(i) </code> copias del entero <code> i </code>.
     * con <code> i </code> en el rango <code> 0..n-1 </code>.
     */
    default List<Integer> getNormalSequence() {
		return IntStream.range(0,getObjectsNumber())
				.boxed()
				.map(x->Lists2.nCopias(getMaxMultiplicity(x),x).stream())
				.flatMap(x->x)
				.collect(Collectors.toList());
	}
}
