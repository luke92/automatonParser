package afnd;

import java.util.ArrayList;
import java.util.List;

import utils.Triplet;

public class AFD {

	public static List<String> obtenerAlfabeto(List<List<String>> tablaDeAFD) {
		List<String> ret = new ArrayList<>();
		List<String> linea = tablaDeAFD.get(0);
		for (String s : linea)
			if (!s.equals(String.valueOf('0')))
				ret.add(s);
		return ret;
	}

	public static List<String> obtenerEstados(List<List<String>> tablaDeAFD) {
		List<String> ret = new ArrayList<>();
		for (List<String> t : tablaDeAFD)
			if(!t.isEmpty() && !t.get(0).equals("0"))
				ret.add(t.get(0));
		return ret;
	}

	public static List<String> obtenerEstadosFinales(List<String> estadosList, List<String> estadosFinalesList) {
		List<String> ret = new ArrayList<>();
		for (String estados : estadosList)
			for (String estadoFinal : estadosFinalesList)
				if (estados.contains(estadoFinal))
					if (!ret.contains(estadoFinal))
						ret.add(estadoFinal);
		return ret;
	}

	public static List<Triplet<String, Character, String>> obtenerFuncionDeTransicion(List<List<String>> tablaDeAFD) {
		List<Triplet<String, Character, String>> ret = new ArrayList<>();
		for (int linea = 1; linea < tablaDeAFD.size(); linea++) { //filas
			for (int alfabeto = 1; alfabeto < tablaDeAFD.get(0).size(); alfabeto++) { //recorro columnas de la fila
				if(!tablaDeAFD.get(linea).isEmpty()) {
					String fromState = tablaDeAFD.get(linea).get(0);
					String character = tablaDeAFD.get(0).get(alfabeto);
					String toState = tablaDeAFD.get(linea).get(alfabeto);
					Triplet<String, Character, String> tri = Triplet.of(fromState, character.charAt(0), toState);
					ret.add(tri);
				}			

			}
		}
		return ret;
	}

}
