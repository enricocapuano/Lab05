package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AnagrammaDAO {

	
	public HashMap<String, Boolean> anagrammi(String parola) {		
		HashMap<String, Boolean> soluzione = new HashMap<String, Boolean>();
		cerca("", parola, 0, soluzione);
		return soluzione;
	}
	
		
	private void cerca(String soluzioneParziale, String lettere,  int livello, Map<String, Boolean> soluzione) {
		if(lettere.length() == 0) {
			//caso terminale
			soluzione.put(soluzioneParziale, this.isValid(soluzioneParziale));
		} else {
			for(int pos = 0; pos < lettere.length(); pos++) {
				char c = lettere.charAt(pos);
				String nuovaParziale = soluzioneParziale + c;
				String nuovaLettere = lettere.substring(0, pos)+lettere.substring(pos+1);
				cerca(nuovaParziale, nuovaLettere, livello+1, soluzione);
			}
		}
	}
	
	private boolean isValid(String anagramma) {
		
		boolean corretto = false;
		
		String sql = "SELECT * "
				+ "FROM parola "
				+ "WHERE parola.nome = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				corretto = true;
			}
			rs.close();
			st.close();
			conn.close();
			
			return corretto;
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore db", e);
		}
	}

}
