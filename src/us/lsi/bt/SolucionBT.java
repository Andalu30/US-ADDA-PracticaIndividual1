package us.lsi.bt;

public interface SolucionBT extends Comparable<SolucionBT>{
	
	/**
	 * @return La propiedad de la soluci�n con respecto a la cual vamos 
	 * a definir el criterio de optimizaci�n. La soluciones se comparan por esta propiedad
	 */
	Double getObjetivo();
	
	@Override
	default public int compareTo(SolucionBT sl) {
		return this.getObjetivo().compareTo(sl.getObjetivo());
	}
	

}
