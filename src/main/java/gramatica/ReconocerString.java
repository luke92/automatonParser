package gramatica;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class ReconocerString {
	
	private static String regex = "X_\\{\\d*\\}";
	
	private ReconocerString() {
		
	}
	
	public static String reconocerVariable(String linea) {
		String[] cadenas = linea.split("->");
		if(cadenas.length == 2) {
			return cadenas[0].trim();
		}
		return "";
	}

	public static String reconocerBody(String linea) {
		String[] cadenas = linea.split("->");
		if(cadenas.length == 2) {
			return cadenas[1].trim();
		}
		return "";
	}

	public static List<String> dividirBodyEnLista(String body) {
		List<String> lista = new ArrayList<String>();
		for (int i = 0; i < body.length(); i++) {
			if (body.charAt(i) == 'X') {
				String varible = "";
				while (body.charAt(i) != '}') {
					varible += body.charAt(i);
					i++;
				}
				varible += "}";
				lista.add(varible);
			} else
				lista.add(Character.toString(body.charAt(i)));
		}
		return lista;
	}
	
	public static List<String> reconocerNoTerminalesEnBody(String body){
		List<String> lista = new ArrayList<String>();
		
	    Pattern p = Pattern.compile(regex);   // the pattern to search for
	    Matcher m = p.matcher(body);

	    while (m.find()) {
    		if(!lista.contains(m.group()))
    			lista.add(m.group());
    	 }

		return lista;
	}
	
	public static List<Character> reconocerTerminalesEnBody(String body) {
		List<Character> lista = new ArrayList<Character>();
		String newBody = body.toString();		
		
		newBody = newBody.replaceAll(regex, "");
			
		for (Character character : newBody.toCharArray()) {
			if(!lista.contains(character))
				lista.add(character);
		}	    

		return lista;
	}

}
