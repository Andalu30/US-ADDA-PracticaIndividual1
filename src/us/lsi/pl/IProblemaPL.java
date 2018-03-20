package us.lsi.pl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;

import us.lsi.pl.ProblemaPL.TipoDeOptimizacion;
import us.lsi.pl.ProblemaPL.TipoDeVariable;

public interface IProblemaPL {

	/**
	 * @return El tipo de la optimizaci�n: Max, Min 
	 */
	
	public TipoDeOptimizacion getTipo();

	/**
	 * @return La funci�n objetivo del problema. 
	 */
	public LinearObjectiveFunction getObjectiveFunction();

	/**
	 * @param objectiveFunction Es la nueva funci�n objetivo
	 */
	public void setObjectiveFunction(LinearObjectiveFunction objectiveFunction);

	/**
	 * @param coefficients Son los coeficientes de las variables en la funci�n objetivo
	 * @param constantTerm Es el t�rmino constante de la funci�n objetivo
	 */
	public void setObjectiveFunction(double[] coefficients, double constantTerm);

	/**
	 * @return La colecci�n de restricciones entre variables del problema
	 */
	public Collection<LinearConstraint> getConstraints();

	/**
	 * @param lc La restricci�n a�adida
	 */
	public void addConstraint(LinearConstraint lc);

	/**
	 * @param coefficients Los coeficientes de las variables en la restricci�n
	 * @param relationship El operador relacional de la restrcci�n se�n se define en 
	 * <a href="http://commons.apache.org/proper/commons-math/apidocs/org/apache/commons/math3/optim/linear/Relationship.html" 
	 * target="_blank">  Relationship </a>
	 *  
	 * 
	 * @param value La parte derecha de la restricci�n
	 */
	public void addConstraint(double[] coefficients, Relationship relationship,
			double value);

	/**
	 * <p>A�ade una restricci�n de tipo Sos. Es decir una restricci�n entre en conjunto de variables s�lo algunas 
	 * de las cuales pueden ser distintas de cero </p>
	 * 
	 * 
	 * @param ls Las variables involucradas
	 * @param nv El n�mero de variables que ser� distintas de cero.
	 */
	public void addSosConstraint(List<Integer> ls, Integer nv);

	/**
	 * @return El n�mero de variables de problema
	 */
	public Integer getNumOfVariables();

	/**
	 * @param e El n�mero de la variable
	 * @param tipo El tipo de la misma
	 */
	public void setTipoDeVariable(int e, TipoDeVariable tipo);

	/**
	 * @param tipo Fija todas las variables a ese tipo
	 */
	public void setTipoDeTodasLasVariables(TipoDeVariable tipo);

	/**
	 * @param e Fija la variable como libre. Una variable libre puede tomar valores positivos y negativos
	 */
	public void setVariableLibre(int e);

	/**
	 * @param e Fija la variable como semicontinua. Una variable semicontinua puede tomar simepre
	 * el cero como un valor posible
	 */
	public void setVariableSemicontinua(int e);

	/**
	 * @return Las variables enteras del problema
	 */
	public List<Integer> getVariablesEnteras();

	/**
	 * @return Las variables binarias del problema
	 */
	public List<Integer> getVariablesBinarias();

	/**
	 * Fija un identificador para la variable
	 * 
	 * @param e La variable
	 * @param s Su identificador
	 */
	public void setNombre(Integer e, String s);

	/**
	 * @param e Una variable
	 * @return Su identificador si lo tiene o el identificador por defecto "xi". D�nde i es el n�mero interno de la variable
	 */
	public String getNombre(Integer e);

	/**
	 * @return Las variables libres del problema
	 */
	public List<Integer> getVariablesLibres();

	/**
	 * @return Las variables semicontinuas del problema
	 */
	public List<Integer> getVariablesSemicontinuas();

	/**
	 * @return Una representaci�n textual de las restricciones
	 */
	public String toStringConstraints();

	/**
	 * Guarda las restrcciones en un fichero que puede ser le�do por el algoritmo 
	 * {@link us.lsi.pl.AlgoritmoPLI AlgoritmoPLI}
	 * 
	 * @param fichero Fichero d�nde se guardan las restricciones
	 */
	public void toStringConstraints(String fichero);

}