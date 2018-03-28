package us.lsi.pd;
import java.util.*;

import us.lsi.pd.AlgoritmoPD.Sp;


/**
 * <p>Interface que debe implementar un problema para ser resuelto por la t�cnica de Programaci�n Din�mica </p>
 * 
 * <p>La documentaci�n puede encontarse en el: <a href="../../../document/Tema14.pdf" target="_blank">Tema14</a></p>
 * 
 * @author Miguel Toro
 *
 * @param <S> El tipo de la soluci�n del problema
 * @param <A> El tipo de las alternativas
 * @see AlgoritmoPD.Sp
 */
public interface ProblemaPD<S,A>  {	
	
	public enum Tipo{Min,Max,Otro}
	
	
	/**
	 * @return El tipo del problema: minimiza, maximiza o busca un valor que involucra a todas las alternativas
	 *
	 * 
	 **/
	Tipo getTipo();	
	/**
	 * @return El tama�o del problema
	 */
	int size();
	
	/**
	 * @return Si el problema est� fuera de rango y su soluci�n es null
	 */
	default boolean estaFueraDeRango(){
		return false;
	}
	/**
	 * @return Si el problema es un caso base
	 * 
	 */
	boolean esCasoBase();
	/**
	 * @pos 
	 * <ul>
	 *	<li> Si el problema no tiene soluci�n se debe devolver null
	 *	<li> Si el problema tiene soluci�n asociada a una alternativa esta ser� de la forma (a,p).Siendo a la alternativa y p la propiedad correspondiente.
	 *	<li> Si el problema tiene soluci�n pero no asociada a una alternativa esta ser� de la forma (null,p).Siendo p la propiedad correspondiente.
	 *	</ul> 
	 * @return  La soluci�n del caso base
	 */
	Sp<A> getSolucionParcialCasoBase();
	/**
	 * @pre ls!=null, ls no est� vac�a, ls no contiene null, ls no contiene valores de la forma <code> (a,null) </code>
	 * 
	 * @pos 
	 * <ul>
	 *  <li> Si ls es vac�a tras eliminar los valores <code> (a,null) </code> la soluci�n devuelta es null. El problema no tiene soluci�n.
	 *	<li> Si el problema tiene soluci�n asociada a una alternativa, esta ser� de la forma <code>(a,p)</code>. Siendo <code> a </code>la alternativa y <code> p </code> la propiedad correspondiente. 
	 *	<li> Si el problema tiene soluci�n pero no asociada a una alternativa esta ser� de la forma <code> (null,p)</code>. Siendo <code> p </code> la propiedad correspondiente.
	 *	</ul> 
	 * @param ls - Soluciones existentes alcanzados tras tomar las diferentes alternativas
	 * @return La soluci�n del problema
	 */
	Sp<A> getSolucionParcial(List<Sp<A>> ls);
	/**
	 * @pre 0 &lt; = i &lt; getNumeroSubProblemas(a)
	 * @param a - Alternativa escogida
	 * @param np - N�mero del subproblema
	 * @return Subproblema
	 */
	ProblemaPD<S,A> getSubProblema(A a, int np);
	/**
	 * @pre <code> ls.size() == getNumeroSubProblemas(a) </code>, a est� incluida en <code> getAlternativas() </code> y
	 * ls no contiene null
	 * @param a - Alternativa escogida
	 * @param ls - Soluciones de los subproblemas
	 * @return La soluci�n del problema alcanzado siguiendo la alternativa <code> a </code> 
	 * y las soluciones parciales de los subproblemas del mismo est�n en ls 
	 */
	Sp<A> getSolucionParcialPorAlternativa(A a , List<Sp<A>> ls);	
	/**
	 * @pos Si un problema no tiene soluci�n el conjunto de alternativas es vac�o 
	 * @return Las alternativas disponibles para el problema
	 */
	List<A> getAlternativas();
	/**
	 * @param a - Alternativa escogida
	 * @return N�mero de subproblemas para una alternativa dada
	 */
	int getNumeroSubProblemas(A a);
	/**
	 * @pre El problema es un caso base
	 * @pos 
	 * <ul>
	 *  <li> Si sp es de la forma <code> (a,p) </code> la soluci�n se reconstruye a partir de esos dos valores. 
	 *  <li> Si es de la forma <code> (null,p) </code> s�lo a partir de <code> p </code>.
	 * </ul> 
	 * @param sp - Soluci�n parcial del caso base
	 * @return La soluci�n para un caso base si la soluci�n parcial es sp. 
	 */
	S getSolucionReconstruidaCasoBase(Sp<A> sp);	
	/**
	 * @pre 
	 * <p> El problema no es un caso base, <code> ls !=null </code>, <code> ls.size() &gt; 0 </code> y ls no contiene null.</p>
	 * <p> Si el tipo es Min o Max entonces sp = (a,p) </p>
	 * <p> Si tipo es Otro entonces sp es (null,p) y S debe ser Double. </p>
	 * @pos
	 * <ul>
	 *  <li> Si <code> sp = (a,p) </code> la soluci�n se reconstruye a partir de <code> a, p, ls </code> estando en <code> ls </code> las 
	 *  soluciones de los subproblemas que se alcanzan siguiendo la alternativa <code> sp.alternativa </code> 
	 *  <li> Si <code> sp = (null,p) </code> la soluci�n es igual a <code> sp.propiedad </code>.
	 * </ul> 
	 * @param sp - Soluci�n parcial del problema
	 * @param ls - 
	 * <ul>
	 *  <li> Si <code> sp = (a,p)  </code> La soluci�n de los subproblemas que se alcanzan 
	 *  siguiendo la alternativa <code> sp.alternativa </code>. 
	 *  <li> Si <code> sp = (null,p) </code> entonces <code> ls </code> es irrelevante. 
	 * </ul>
	 * @return La soluci�n para el problema. 
	 */
	S getSolucionReconstruidaCasoRecursivo(Sp<A> sp, List<S> ls);	
	
	/**
	 * @pre El uso del m�todo es relevante si usamos la t�cnica con filtro. Si no la usamos el m�todo es irrelevante.
	 * @pos 
	 * <ul>
	 * <li> Si el problema es de minimizaci�n el valor debe ser 
	 * una cota inferior del valor de la propiedad objetivo del problema inicial, 
	 * asumiendo que estamos en el problema actual y se  va a seguir la alternativa <code> a </code>.
	 * La alternativa puede ser descartada si <code> getObjetivoEstimado(a) &gt; =  AlgoritmoPD.getMejorValor() </code>,
	 * puesto que el valor objetivo de la soluci�n obtenida estar�a por encima del mejor valor obtenido hasta el momento.
	 * Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a)() &lt;   AlgoritmoPD.getMejorValor() </code>.
	 * Donde <code> AlgoritmoPD.getMejorValor() </code> es el mejor valor encontrado por el algoritmo hasta ahora.
	 * <li> Si el problema original fuera de maximizaci�n el valor debe ser 
	 * una cota superior del valor de la propiedad objetivo del problema actual, 
	 * asumiendo que estamos en el problema actual y se  va a seguir la alternativa <code> a </code>.
	 *  Es decir nos quedamos con las alternativas que cumplen 
	 * <code> getObjetivoEstimado(a) &gt;   AlgoritmoPD.getMejorValor() </code>.
	 * </ul>
	 * @param a - Alternativa elegida
	 * @return Una cota para valor objetivo si seguimos <code> a </code>
	 */
	default Double getObjetivoEstimado(A a){
		return null;
	}
	/**
	 * 
	 * @pre El uso del m�todo es relevante si usamos la t�cnica con filtro. Si no la usamos el m�todo es irrelevante.
	 * @return Valor de la propiedad objetivo del problema inicial si es posible calcularlo asumiendo
	 * que estamos en el problema actual. Si no es posible null.
	 */
	default Double getObjetivo() {
		return null;
	}
	
}
