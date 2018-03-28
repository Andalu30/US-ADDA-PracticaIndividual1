package us.lsi.pd.jarras;

import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;
import us.lsi.tiposrecursivos.Tree;

public class Test {

	

	public static void main(String[] args) {
		Accion.iniciaAcciones(AbstractAlgoritmo.raiz+"acciones.txt");
		Accion.acciones.forEach(x->System.out.println(x));
		ProblemaJarrasPD.capacidadJarra1 = 4;
		ProblemaJarrasPD.capacidadJarra2 = 3;
		ProblemaJarrasPD.cantidadFinalEnJarra1 = 2;
		ProblemaJarrasPD.cantidadFinalEnJarra2 = 0;
		ProblemaJarrasPD.numMaxAcciones = 10;
		AbstractAlgoritmo.setFile("resultadoPD.txt");
		AbstractAlgoritmo.calculaMetricas();
		System.out.println("------");
		ProblemaJarrasPD p = ProblemaJarrasPD.create();
		AlgoritmoPD.conFiltro = true;
		AlgoritmoPD<SolucionJarras, Accion> a = Algoritmos.createPD(p);
		a.ejecuta();
			
		System.out.println(a.getSolucion(p));
		System.out.println(AbstractAlgoritmo.metricas);
		Tree<Accion> t = a.getAlternativasDeSolucion(p);
		t.toDOT("ficheros/jarras.gv", "Jarras");
	}

}
