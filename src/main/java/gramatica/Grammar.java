package gramatica;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
	
	private List<String> noTerminals;
	
	private List<Character> terminals; 
	
	public Grammar(List<String> lines) {
		
		this.noTerminals = new ArrayList<String>();
		this.terminals = new ArrayList<Character>();
		
		for (String line : lines) {
			
			addNoTerminal(ReconocerString.reconocerVariable(line));
						
			String body = ReconocerString.reconocerBody(line);
			addNoTerminals(ReconocerString.reconocerNoTerminalesEnBody(body));
			addTerminals(ReconocerString.reconocerTerminalesEnBody(body));

		}
	}
	
	

	private void addNoTerminal(String noTerminal) {
		if(!noTerminal.isEmpty() && !noTerminals.contains(noTerminal)) {
			noTerminals.add(noTerminal);				
		}
	}
	
	private void addNoTerminals(List<String> noTerminals) {
		for (String noTerminal : noTerminals) {
			addNoTerminal(noTerminal);
		}
	}
	
	private void addTerminal(Character terminal) {
		if(!terminals.contains(terminal)) {
			terminals.add(terminal);				
		}
	}
	
	private void addTerminals(List<Character> reconocerTerminalesEnBody) {
		for (Character terminal : reconocerTerminalesEnBody) {
			addTerminal(terminal);
		}
		
	}
	
	public List<String> getNoTerminals(){
		return this.noTerminals;
	}
	
	public List<Character> getTerminals(){
		return this.terminals;
	}
}
