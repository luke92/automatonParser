package afnd;

import java.util.ArrayList;
import java.util.List;

import utils.FormatterUtil;
import utils.Triplet;

public class Formatear {

	public static List<Triplet<String, Character, String>> obtenerTransicionesEnTuplas(List<String> lineas) {
		List<Triplet<String, Character, String>> ret = new ArrayList<>();
		
		//Leo desde la 4ta linea del archivo
		for (int i = 3; i < lineas.size(); i++) {
			String fromState = getFromState(lineas.get(i));
			Character character = getCharacter(lineas.get(i));
			String toState = getToState(lineas.get(i));
			
			if(fromState != null && character != null && toState != null) {
				Triplet<String, Character, String> tri = Triplet.of(fromState, character, toState);
				ret.add(tri);
			}
			else {
				return new ArrayList<>();
			}
		}

		return ret;
	}
	
	public static String getFromState(String linea) {
		String[] cadenas = linea.split(",");
		if(cadenas.length == 2)
			return cadenas[0].trim();
		else return null;
	}
	
	public static Character getCharacter(String linea) {
		String[] cadenas = linea.split(",");
		if(cadenas.length == 2)
			return cadenas[1].trim().charAt(0);
		else return null;
	}
	
	public static String getToState(String linea) {
		String[] cadenas = linea.split("->");
		if(cadenas.length == 2)
			return cadenas[1].trim();
		else return null;
	}
	

	public static List<Character> obtenerLista(String s) {
		List<Character> lista = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ' && s.charAt(i) != ',') {
				if(!lista.contains(s.charAt(i)))
					lista.add(s.charAt(i));
			}
		}
		return lista;
	}
	

	public static List<String> obtenerListaEstados(String cantEstados) {
		List<String> lista = new ArrayList<String>();
		
		if(FormatterUtil.tryParseInt(cantEstados)) {
			for (int i = 1; i <= Integer.valueOf(cantEstados); i++) {
				lista.add(Integer.toString(i));
			}			
		}
		return lista;		
	}
	
	public static List<String> obtenerListaEstadosFinales(String s) {
		List<String> estadosFinales = new ArrayList<>();
		String cadenas = s.replace(" ", "");
		String[] estados = cadenas.split(",");
		
		for (int i = 0; i < estados.length; i++) {
			if(FormatterUtil.tryParseInt(estados[i])) {
				if(!estadosFinales.contains(estados[i])) {
					estadosFinales.add(estados[i]);
				}
			}
			else return new ArrayList<>();				
			
		}		
		
		return estadosFinales;
	}
	
	

}
