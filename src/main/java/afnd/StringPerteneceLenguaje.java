package afnd;

import java.util.List;
import utils.Triplet;

public class StringPerteneceLenguaje {

	public static boolean stringPerteneceLenguaje(String stringDeInput, List<String> elemAlfabetoList,
			List<String> estadosFinalesList, List<Triplet<String, Character, String>> tuplasTransiciones) {
		if (stringDeInput.isEmpty())
			return false;
		if (!perteneceAlAlfabetoDeInput(stringDeInput, elemAlfabetoList))
			return false;
		if (!ultimoElemVaAEstadoFinal(stringDeInput, estadosFinalesList, tuplasTransiciones))
			return false;
		if (!primeroEstadoInicialUno(stringDeInput, tuplasTransiciones))
			return false;
		if (!recorridoTransiciones(stringDeInput, tuplasTransiciones))
			return false;
		return true;
	}

	private static boolean perteneceAlAlfabetoDeInput(String stringDeInput, List<String> elemAlfabetoList) {
		boolean ret = true;
		for (int i = 0; i < stringDeInput.length(); i++) {
			for (int j = 0; j < elemAlfabetoList.size(); j++) {
				if (String.valueOf(stringDeInput.charAt(i)).equals(elemAlfabetoList.get(j))) {
					ret = true;
					break;
				} else {
					ret = false;
					if (j == elemAlfabetoList.size() - 1) {
						System.out.println("perteneceAlAlfabetoDeInput: " + ret);
						return ret;
					}
				}
			}
		}
		System.out.println("perteneceAlAlfabetoDeInput: " + ret);
		return ret;
	}

	private static boolean ultimoElemVaAEstadoFinal(String stringDeInput, List<String> estadosFinalesList,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		boolean ultimoElementVaAUnEstadoFinal = false;
		Character ultimoElementoDelString = stringDeInput.charAt(stringDeInput.length() - 1);
		for (int i = 0; i < tuplasTransiciones.size(); i++) {
			for (int j = 0; j < estadosFinalesList.size(); j++) {
				if (ultimoElementoDelString.equals(tuplasTransiciones.get(i).second)) {
					if (tuplasTransiciones.get(i).third.contains(estadosFinalesList.get(j))) {

						ultimoElementVaAUnEstadoFinal = true;
					}
				}
			}
		}
		System.out.println("ultimoElemVaAEstadoFinal: " + ultimoElementVaAUnEstadoFinal);
		return ultimoElementVaAUnEstadoFinal;
	}

	private static boolean primeroEstadoInicialUno(String stringDeInput,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		for (int j = 0; j < tuplasTransiciones.size(); j++) {
			if (stringDeInput.charAt(0) == tuplasTransiciones.get(j).second) {
				if (tuplasTransiciones.get(j).first.equals("1")) {
					System.out.println("primeroEstadoInicialUno: " + true);
					return true;
				}
			} else if (j == (tuplasTransiciones.size() - 1)) {
				System.out.println("primeroEstadoInicialUno: " + false);
				return false;
			}
		}
		return false;
	}

	// retorna false si recorriendo va a un trampa
	private static boolean recorridoTransiciones(String stringDeInput,
			List<Triplet<String, Character, String>> tuplasTransiciones) {
		String estadoActual = Integer.toString(1); // el primer estado siempre es 1
		int i = 0, j = 0;
		while (i < stringDeInput.length()) {
			while (j < tuplasTransiciones.size() && i < stringDeInput.length()) {

				Triplet<String, Character, String> tuplaActual = tuplasTransiciones.get(j);
				System.out.println("tuplaActual:" + tuplaActual);

				// si el estado_1 es igual al estado actual
				boolean tuplaIgualEstado = tuplaActual.first.equals(estadoActual);
				if (tuplaIgualEstado) {
					String inputActual = Character.toString(stringDeInput.charAt(i));
					System.out.println("inputActual:" + inputActual);

					// si caracter actual del string es igual al actual de la tupla
					boolean inputIgualTupla = inputActual.equals(Character.toString(tuplaActual.second));
					if (!inputIgualTupla) {
						j++;
					} else {
						i++;
						estadoActual = tuplaActual.third;
						System.out.println("estadoActual:" + estadoActual);
						if (estadoActual.equals("T")) {
							System.out.println("recorridoTransiciones: false");
							return false;
						}
						j = 0;
						continue;
					}
				} else {
					j++;
				}
			}
		}
		System.out.println("recorridoTransiciones: true");
		return true;
	}
}
