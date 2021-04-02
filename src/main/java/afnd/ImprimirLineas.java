package afnd;

import java.util.List;

public class ImprimirLineas {

	public static void imprimirLineas(List<String> lineas, List<Character> alfabetoList, List<String> estadosList,
			List<String> estadosFinalesList, List<String> transiciones) {
		System.out.println("Elementos del alfabeto de input: " + alfabetoList);
		System.out.println("Estados: " + estadosList);
		System.out.println("Conjunto de estados finales: " + estadosFinalesList);
		System.out.println("Funcion de transicion: ");
		imprimirLista(obtenerTransiciones(lineas, transiciones));
	}

	private static List<String> obtenerTransiciones(List<String> lineas, List<String> transiciones) {
		for (int i = 3; i < lineas.size(); i++)
			transiciones.add(lineas.get(i));
		return transiciones;
	}

	private static void imprimirLista(List<String> lista) {
		for (String l : lista)
			System.out.println(l);
	}

}
