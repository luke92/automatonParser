package afnd;

import java.util.ArrayList;
import java.util.List;

import utils.Triplet;

public class TransformacionAFD {

	public static List<List<String>> tablaDeTransicion(List<Character> alfabetoList, List<String> estadosList,
			List<String> estadosFinalesList, List<Triplet<String, Character, String>> tuplasTransiciones) {

		List<List<String>> tablaDeTransicion = new ArrayList<List<String>>();
		tablaDeTransicion = agregarAlfabetos(alfabetoList);
		tablaDeTransicion = agregarFila(tablaDeTransicion, estadosList, alfabetoList, tuplasTransiciones);

		return tablaDeTransicion;
	}

	// agregamos los alfabetos a la Tabla de transición AFND
	private static List<List<String>> agregarAlfabetos(List<Character> alfabetoList) {
		List<List<String>> tabla = new ArrayList<List<String>>();
		List<String> linea = new ArrayList<>();
		linea.add("0");
		for (Character s : alfabetoList)
			linea.add(Character.toString(s));
		tabla.add(linea);
		return tabla;
	}

	// agrega la nueva fila de la Tabla de transición AFND
	private static List<List<String>> agregarFila(List<List<String>> tabla, List<String> estadosList,
			List<Character> alfabetoList, List<Triplet<String, Character, String>> tuplasTransiciones) {
		for (String e : estadosList) {
			List<String> linea = new ArrayList<>();
			// agrego los estados
			linea.add(e);
			// agrego las lineas de la tabla de transicion AFND
			tabla.add(completarLinea(linea, alfabetoList, tuplasTransiciones));
		}
		return tabla;
	}

	// completa la lines para la fila de la Tabla de transición AFND
	private static List<String> completarLinea(List<String> linea, List<Character> alfabetoList,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		String s = "";
		for (int i = 0; i < alfabetoList.size(); i++) {
			for (Triplet<String, Character, String> transicion : tuplasTransiciones)
				if (linea.get(0).equals(transicion.first)) { // si el estado de la linea es igual al de la tupla
					if (alfabetoList.get(i).equals(transicion.second)) { // si el alfabeto es igual al de la tupla
						s += transicion.third;
					}
				}
			linea.add(s);
			s = "";
		}
		return linea;
	}

	static void imprimirTabla(List<List<String>> tabla) {
		for (List<String> t : tabla)
			System.out.println(t);
	}

	public static List<List<String>> tablaTransformacionAFD(List<List<String>> tablaDeTransicion,
			List<Character> alfabetoList, List<Triplet<String, Character, String>> tuplasTransiciones) {
		List<List<String>> tablaNueva = new ArrayList<List<String>>();
		// agregamos las primeras dos filas de la Tabla de transición AFND
		tablaNueva.add(tablaDeTransicion.get(0));
		tablaNueva.add(tablaDeTransicion.get(1));

		tablaNueva = agregarFilasEnTabla(tablaNueva, alfabetoList, tuplasTransiciones);

		return agregarEstadoTrampa(tablaNueva);
	}

	private static List<List<String>> agregarFilasEnTabla(List<List<String>> tablaNueva, List<Character> alfabetoList,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		List<String> fila = new ArrayList<String>();
		for (int i = 1; i < tablaNueva.get(0).size() - 1; i++) {
			for (int j = 1; j < tablaNueva.size() + 1; j++) {
				if (i < tablaNueva.size() && j < tablaNueva.get(i).size())
					if (!tablaNueva.get(i).get(j).equals("")) {
						// Si no esta vacio completa la linea
						fila.addAll(completarLineaAFD(tablaNueva.get(i).get(j), alfabetoList, tuplasTransiciones));
					}
			}
		}

		// si no se encuentran estados nuevos se agrega la tabla
		if (!hayEstadosNuevos(tablaNueva, fila))
			return tablaNueva;

		// se divide la Fila con los estados nuevos
		List<String> nuevaFila1 = new ArrayList<String>();
		List<String> nuevaFila2 = new ArrayList<String>();
		for (int i = 0; i < fila.size(); i++) {
			if (i < tablaNueva.get(0).size()) {
				nuevaFila1.add(fila.get(i));
			}
			if (i >= tablaNueva.get(0).size() && i < (tablaNueva.get(0).size() * 2)) {
				nuevaFila2.add(fila.get(i));
			}
		}

		// se agrega a la tabla nueva las nuevas filas
		if (!tablaNueva.contains(nuevaFila1))
			tablaNueva.add(nuevaFila1);
		if (!tablaNueva.contains(nuevaFila2) && nuevaFila2.size() != 0) {
			tablaNueva.add(nuevaFila2);
		}

		// recursividad para los nuevos estados que se encuentren
		while (true) {
			List<String> nuevosEstados = new ArrayList<String>();
			for (int i = 0; i < fila.size(); i++) {
				boolean estadoEncontrado = false;
				for (int j = 0; j < tablaNueva.size(); j++) {
					if (!tablaNueva.get(j).isEmpty())
						if (fila.get(i).equals(tablaNueva.get(j).get(0))) {
							estadoEncontrado = true;
						}
				}
				if (!estadoEncontrado)
					if (!nuevosEstados.contains(fila.get(i)) && !fila.get(i).isEmpty())
						nuevosEstados.add(fila.get(i));
			}

			if (nuevosEstados.isEmpty())
				break;

			// se agrega la nueva fila del estado nuevo
			for (String estado : nuevosEstados) {
				List<String> nuevaFila = completarLineaAFD(estado, alfabetoList, tuplasTransiciones);
				tablaNueva.add(nuevaFila);
				fila = nuevaFila;
			}
		}

		return agregarFilasEnTabla(tablaNueva, alfabetoList, tuplasTransiciones);
	}

	// consulta si hay estados nuevos
	private static boolean hayEstadosNuevos(List<List<String>> tablaNueva, List<String> fila) {
		List<String> nuevosEstados = new ArrayList<String>();
		for (int i = 0; i < fila.size(); i++) {
			boolean estadoEncontrado = false;
			for (int j = 0; j < tablaNueva.size(); j++) {
				if (!tablaNueva.get(j).isEmpty())
					if (fila.get(i).equals(tablaNueva.get(j).get(0))) {
						estadoEncontrado = true;
					}
			}
			if (!estadoEncontrado)
				if (!nuevosEstados.contains(fila.get(i)) && !fila.get(i).isEmpty())
					nuevosEstados.add(fila.get(i));
		}
		return !nuevosEstados.isEmpty();
	}

	// completa la linea de la tabla a partir del nuevo estado
	private static List<String> completarLineaAFD(String string, List<Character> alfabetoList,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		String s = "";
		List<String> linea = new ArrayList<String>();
		linea.add(string);
		for (int i = 0; i < alfabetoList.size(); i++) {
			for (Triplet<String, Character, String> transicion : tuplasTransiciones) {
				for (int j = 0; j < string.length(); j++) {
					// si el estado de la linea es igual al de la tupla
					if (Character.toString(string.charAt(j)).equals(transicion.first)) {
						// si el alfabeto es igual al de la tupla
						if (alfabetoList.get(i).equals(transicion.second)) {
							if (!s.contains(transicion.third))
								s += transicion.third;
						}
					}
				}
			}
			linea.add(s);
			s = "";
		}
		return linea;
	}

	// se completa con vacios para la Tabla de transición AFND
	public static List<String> completarConVacios(List<String> fila, int cantidadVacios) {
		for (int i = 0; i < cantidadVacios; i++)
			fila.add(" ");
		return fila;
	}

	// los vacios los transformamos a un estado T que es el estado trampa
	public static List<List<String>> agregarEstadoTrampa(List<List<String>> tabla) {
		List<List<String>> tablaNueva = new ArrayList<List<String>>();
		for (List<String> listas : tabla) {
			List<String> nuevaFila = new ArrayList<String>();
			for (String elemento : listas) {
				if (elemento.trim().equals("")) {
					nuevaFila.add("T");
				} else
					nuevaFila.add(elemento);
			}
			tablaNueva.add(nuevaFila);
		}

		List<String> filaTrampa = new ArrayList<String>();
		for (int i = 0; i < tabla.get(0).size(); i++)
			filaTrampa.add("T");
		tablaNueva.add(filaTrampa);

		return tablaNueva;
	}
}
