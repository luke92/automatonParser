package afnd;

import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;
import utils.GetInputFromUser;
import utils.Triplet;

public class Main {

	public static void main(String[] args) {
		List<String> lineas = FileUtil.getLinesFromFile("automata3.txt");
		String alfabeto = lineas.get(0);
		String estados = lineas.get(1);
		String estadosFinales = lineas.get(2);
		List<String> transiciones = new ArrayList<String>();		

		List<Character> alfabetoList = Formatear.obtenerLista(alfabeto);

		List<String> estadosList = Formatear.obtenerListaEstados(estados);

		List<String> estadosFinalesList = Formatear.obtenerListaEstadosFinales(estadosFinales);
		
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear.obtenerTransicionesEnTuplas(lineas);
		
		ImprimirLineas.imprimirLineas(lineas, alfabetoList, estadosList, estadosFinalesList, transiciones);		

		List<List<String>> tablaDeTransicion = TransformacionAFD.tablaDeTransicion(alfabetoList, estadosList,
				estadosFinalesList, tuplasTransiciones);
		System.out.println("\nTabla de transición AFND: Estado | caracter 1 del alfabeto .. | caracter n del alfabeto");
		TransformacionAFD.imprimirTabla(tablaDeTransicion);

		List<List<String>> tablaDeAFD = TransformacionAFD.tablaTransformacionAFD(tablaDeTransicion,alfabetoList,tuplasTransiciones);
		System.out.println("\nTabla de transición AFD: Estado | caracter 1 del alfabeto .. | caracter n del alfabeto");
		TransformacionAFD.imprimirTabla(tablaDeAFD);

		List<String> nuevoAlfabetoList = AFD.obtenerAlfabeto(tablaDeAFD);
		System.out.println("\nnuevoAlfabeto : " + nuevoAlfabetoList);
		List<String> nuevosEstadosList = AFD.obtenerEstados(tablaDeAFD);
		System.out.println("nuevosEstados : " + nuevosEstadosList);
		List<String> nuevosEstadosFinalesList = AFD.obtenerEstadosFinales(nuevosEstadosList, estadosFinalesList);
		System.out.println("estadosFinales: " + nuevosEstadosFinalesList);
		List<Triplet<String, Character, String>> FuncTransicion = AFD.obtenerFuncionDeTransicion(tablaDeAFD);
		System.out.println("FuncTransicion: " + FuncTransicion);
		
		while(true) {
			System.out.println("\nIngrese el string de input del Automata (Si no ingresa nada el programa se detiene): ");
			String stringDeInput = GetInputFromUser.Get();
			System.out.println();
			boolean stringPerteneceLenguaje = StringPerteneceLenguaje.stringPerteneceLenguaje(stringDeInput,
					nuevoAlfabetoList, nuevosEstadosFinalesList, FuncTransicion);
			System.out.println("\nstringPerteneceLenguaje: " + stringPerteneceLenguaje);
			System.out.println();
			
			if(stringDeInput.isEmpty()) break;
		}
		
		
		//TODO hacer multithread del AFND para detectar si el input pertenece al lenguaje (para cada camino)
	}

}
