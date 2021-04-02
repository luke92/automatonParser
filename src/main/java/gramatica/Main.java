package gramatica;

import java.util.List;

import utils.FileUtil;

public class Main {

	public static void main(String[] args) {
		List<String> lineas = FileUtil.getLinesFromFile("gramatica2.txt");
		System.out.println("Contenido del archivo");
		for (String linea : lineas) {
			System.out.println(linea);
		}	
		
		Grammar fgm = new Grammar(lineas);
		
		System.out.println("Terminales: " + fgm.getTerminals());
		System.out.println("No Terminales " + fgm.getNoTerminals());
		
		
		// DETECTAR la Gramatica
		
		//TODO Crear tabla de parsing
		
		//HACER metodo FIRST y FOLLOW
		
		//TODO Implementar algoritmo visto en clase
	}
}
