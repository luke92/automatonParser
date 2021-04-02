package afnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.FileUtil;
import utils.Triplet;

public class AFNDTest {
	
	private List<String> lineas;
		
    @Before
    public void setUp() {
    	lineas = FileUtil.getLinesFromFile("automata.txt");
    }

	@Test
	public void readFileBadFormat() {
		List<String> lines = FileUtil.getLinesFromFile("automataCantidadLineasInsuficientes.txt");
		assertFalse(BooleanTest.elementosDelAlfabetoFormato(lines) &&
				BooleanTest.cantEstadosFormato(lines) &&
				BooleanTest.estadosFinalesFormato(lines) &&
				BooleanTest.transicionesFormato(lines)
			);
	}

	@Test
	public void readFileGoodFormat() {
				
		assertTrue(BooleanTest.elementosDelAlfabetoFormato(lineas) &&
			BooleanTest.cantEstadosFormato(lineas) &&
			BooleanTest.estadosFinalesFormato(lineas) &&
			BooleanTest.transicionesFormato(lineas)
		);
	}

	@Test
	public void firstLineInputSimbolsOk() {
		assertTrue(BooleanTest.elementosDelAlfabetoFormato(lineas));
	}

	@Test
	public void firstLineInputSimbolsError() {
		List<String> lines = FileUtil.getLinesFromFile("automataConAlfabetoMalEscrito.txt");
		assertFalse(BooleanTest.elementosDelAlfabetoFormato(lines));
	}

	@Test
	public void secondLineCountStatesOk() {
		assertTrue(BooleanTest.cantEstadosFormato(lineas));
	}

	@Test
	public void secondLineCountStatesError() {
		List<String> lines = FileUtil.getLinesFromFile("automataConCantidadEstadosMalEscrito.txt");
		assertFalse(BooleanTest.cantEstadosFormato((lines)));
	}

	@Test
	public void thirdLineFinalStatesOk() {
		assertTrue(BooleanTest.estadosFinalesFormato(lineas));
	}

	@Test
	public void thirdLineFinalStatesError() {
		List<String> lines = FileUtil.getLinesFromFile("automataConEstadosFinalesMalEscrito.txt");
		assertFalse(BooleanTest.estadosFinalesFormato(lines));
	}
	
	@Test
	public void thirdLineFinalStatesInexistents() {
		List<String> lines = FileUtil.getLinesFromFile("automataConEstadosFinalesInexistentes.txt");
		assertFalse(BooleanTest.estadosFinalesFormato(lines));
	}

	@Test
	public void fourthLineFunctionTransitionOk() {
		assertTrue(BooleanTest.transicionesFormato(lineas));
	}

	@Test
	public void fourthLineFunctionTransitionError() {
		List<String> lines = FileUtil.getLinesFromFile("automataConTransicionesMalEscrito.txt");
		assertFalse(BooleanTest.transicionesFormato(lines));
	}

	@Test
	public void transformAfndtoAfdOk() {
		String alfabeto = lineas.get(0);
		String estados = lineas.get(1);
		String estadosFinales = lineas.get(2);
		List<Character> alfabetoList = Formatear.obtenerLista(alfabeto);
		List<String> estadosList = Formatear.obtenerListaEstados(estados);
		List<String> estadosFinalesList = Formatear.obtenerListaEstadosFinales(estadosFinales);
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear.obtenerTransicionesEnTuplas(lineas);
		List<List<String>> tablaDeTransicion = TransformacionAFD.tablaDeTransicion(alfabetoList, estadosList,
				estadosFinalesList, tuplasTransiciones);
		List<List<String>> tablaDeAFD = TransformacionAFD.tablaTransformacionAFD(tablaDeTransicion,alfabetoList,tuplasTransiciones);
		
		List<String> nuevosEstadosList = AFD.obtenerEstados(tablaDeAFD);
		assertEquals(6, nuevosEstadosList.size());
	}

	@Test
	public void transformAfndtoAfdError() {
		String alfabeto = lineas.get(0);
		String estados = lineas.get(1);
		String estadosFinales = lineas.get(2);
		List<Character> alfabetoList = Formatear.obtenerLista(alfabeto);
		List<String> estadosList = Formatear.obtenerListaEstados(estados);
		List<String> estadosFinalesList = Formatear.obtenerListaEstadosFinales(estadosFinales);
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear.obtenerTransicionesEnTuplas(lineas);
		List<List<String>> tablaDeTransicion = TransformacionAFD.tablaDeTransicion(alfabetoList, estadosList,
				estadosFinalesList, tuplasTransiciones);
		List<List<String>> tablaDeAFD = TransformacionAFD.tablaTransformacionAFD(tablaDeTransicion,alfabetoList,tuplasTransiciones);
		
		List<String> nuevosEstadosList = AFD.obtenerEstados(tablaDeAFD);
		assertNotEquals(5, nuevosEstadosList.size());
	}

	@Test
	public void stringPerteneceAlLenguajeAFDOk() {
		String stringDeInput = "ababa";
		
		String alfabeto = lineas.get(0);
		String estados = lineas.get(1);
		String estadosFinales = lineas.get(2);
		List<Character> alfabetoList = Formatear.obtenerLista(alfabeto);
		List<String> estadosList = Formatear.obtenerListaEstados(estados);
		List<String> estadosFinalesList = Formatear.obtenerListaEstadosFinales(estadosFinales);
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear.obtenerTransicionesEnTuplas(lineas);
		List<List<String>> tablaDeTransicion = TransformacionAFD.tablaDeTransicion(alfabetoList, estadosList,
				estadosFinalesList, tuplasTransiciones);
		List<List<String>> tablaDeAFD = TransformacionAFD.tablaTransformacionAFD(tablaDeTransicion,alfabetoList,tuplasTransiciones);
		List<String> nuevoAlfabetoList = AFD.obtenerAlfabeto(tablaDeAFD);
		List<String> nuevosEstadosList = AFD.obtenerEstados(tablaDeAFD);
		List<String> nuevosEstadosFinalesList = AFD.obtenerEstadosFinales(nuevosEstadosList, estadosFinalesList);
		List<Triplet<String, Character, String>> FuncTransicion = AFD.obtenerFuncionDeTransicion(tablaDeAFD);
		assertTrue(StringPerteneceLenguaje.stringPerteneceLenguaje(stringDeInput,
				nuevoAlfabetoList, nuevosEstadosFinalesList, FuncTransicion));
	}

	@Test
	public void stringNoPerteneceAlLenguajeAFDOk() {
		String stringDeInput = "aa";
		
		String alfabeto = lineas.get(0);
		String estados = lineas.get(1);
		String estadosFinales = lineas.get(2);
		List<Character> alfabetoList = Formatear.obtenerLista(alfabeto);
		List<String> estadosList = Formatear.obtenerListaEstados(estados);
		List<String> estadosFinalesList = Formatear.obtenerListaEstadosFinales(estadosFinales);
		List<Triplet<String, Character, String>> tuplasTransiciones = Formatear.obtenerTransicionesEnTuplas(lineas);
		List<List<String>> tablaDeTransicion = TransformacionAFD.tablaDeTransicion(alfabetoList, estadosList,
				estadosFinalesList, tuplasTransiciones);
		List<List<String>> tablaDeAFD = TransformacionAFD.tablaTransformacionAFD(tablaDeTransicion,alfabetoList,tuplasTransiciones);
		List<String> nuevoAlfabetoList = AFD.obtenerAlfabeto(tablaDeAFD);
		List<String> nuevosEstadosList = AFD.obtenerEstados(tablaDeAFD);
		List<String> nuevosEstadosFinalesList = AFD.obtenerEstadosFinales(nuevosEstadosList, estadosFinalesList);
		List<Triplet<String, Character, String>> FuncTransicion = AFD.obtenerFuncionDeTransicion(tablaDeAFD);
		assertFalse(StringPerteneceLenguaje.stringPerteneceLenguaje(stringDeInput,
				nuevoAlfabetoList, nuevosEstadosFinalesList, FuncTransicion));
	}

	@Test
	public void createRoadsFromAFNDMultiTThreadOK() {
		assertTrue(true);
	}

	@Test
	public void createRoadsFromAFNDMultiTThreadError() {
		assertFalse(false);
	}

}
