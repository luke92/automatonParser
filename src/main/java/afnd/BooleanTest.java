package afnd;

import java.util.List;

import utils.FormatterUtil;
import utils.Triplet;

public class BooleanTest {

	public static boolean elementosDelAlfabetoFormato(List<String> lineas) {
		
		if(!cantidadLineasOk(lineas)) return false;
		
		String alfabeto = lineas.get(0).replace(" ", "");
		String[] caracteres = alfabeto.split(",");
		for (int i = 0; i < caracteres.length; i++) {
			if (caracteres[i].length() > 1) {
				return false;
			}
		}
		
		return true;
	}

	public static boolean cantEstadosFormato(List<String> lineas) {
		if(!cantidadLineasOk(lineas)) return false;
		return FormatterUtil.tryParseInt(lineas.get(1));		
	}

	public static boolean estadosFinalesFormato(List<String> lineas) {
		if(!cantidadLineasOk(lineas)) return false;
		if(!cantEstadosFormato(lineas)) return false;
		Integer cantidadEstados = Integer.parseInt(lineas.get(1));
		List<String> estados = Formatear.obtenerListaEstadosFinales(lineas.get(2));
		if(estados.isEmpty()) return false;
		for(String estado : estados) {
			if(!FormatterUtil.tryParseInt(estado)) return false;
			if(Integer.parseInt(estado) > cantidadEstados) return false;
		}
		
		return true;
	}

	public static boolean transicionesFormato(List<String> lineas) {
		if(!cantidadLineasOk(lineas)) return false;
		if(!cantEstadosFormato(lineas)) return false;
		if(!estadosFinalesFormato(lineas)) return false;
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear
				.obtenerTransicionesEnTuplas(lineas);
		return !tuplasTransiciones.isEmpty();
	}
	
	public static boolean cantidadLineasOk(List<String> lineas) {
		return lineas.size() >= 4;
	}

}
