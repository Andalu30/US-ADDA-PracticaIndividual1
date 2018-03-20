package us.lsi.stream;

import java.util.*;

/**
 * Es un tipo que asocia claves de tipo K1 con valores de tipo R. Una versi�n compacta de un SortedMap&lt;K1,R&gt;.
 * 
 * @author Miguel Toro
 *
 * @param <K1> Tipo de la clave
 * @param <R> Tipo de los valores
 */

class Group1Sort<K1, R> extends Group1<K1, R>{

	private static final long serialVersionUID = 1L;
	
	public static <K1, R> Group1Sort<K1, R> create(
			SortedMap<K1, ? extends R> m) {
		return new Group1Sort<K1, R>(m);
	}
	
	@Override
	public String toString(){
		return toString(0);
	}
	
	@Override
	public R getGroup(K1 x){
		return this.get(x);
	}

	private Group1Sort(SortedMap<K1, ? extends R> m) {
		super(m);
	}
		
}
