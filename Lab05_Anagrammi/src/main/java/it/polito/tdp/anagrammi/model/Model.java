package it.polito.tdp.anagrammi.model;

import java.util.HashMap;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;


public class Model {
	
	AnagrammaDAO a = new AnagrammaDAO();

	public HashMap<String, Boolean> anagrammi(String parola) {	
		return a.anagrammi(parola);
	}
}
