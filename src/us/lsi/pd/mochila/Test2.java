package us.lsi.pd.mochila;

import com.google.common.collect.Multiset;

import us.lsi.algoritmos.AbstractAlgoritmo;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;

public class Test2 {

	public static String ruta = "C:\\Users\\Boss\\Desktop\\Ficheros\\";

	public static void main(String[] args) {
		
		ProblemaMochilaPD  p = ProblemaMochilaPD.create(AbstractAlgoritmo.raiz+"objetosmochila.txt", 78);		
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = true;
		AbstractAlgoritmo.calculaMetricas();
		System.out.println(ProblemaMochilaPD.objetos+"\n"+ProblemaMochilaPD.multiplicidadesMaximas);
		System.out.println("Problema Inicial ="+p);
		AlgoritmoPD<Multiset<ObjetoMochila>, Integer> a = Algoritmos.createPD(p);
		a.ejecuta();
//		a.solucionesParciales.put(ProblemaMochila.createPDF(3, 0, 0), null);
//		System.out.println(Metricas.getMetricas().toString());
//		System.out.println(p1.getCotaValor(61));
//		System.out.println(p1.getCotaValor(60));	
		a.showAllGraph(ruta+"pruebaMochilaSinFiltro.gv","Mochila",p);
		System.out.println("Solucion= "+SolucionMochila.create(a.getSolucion(p)));
		System.out.println(AbstractAlgoritmo.metricas);
	}

}
