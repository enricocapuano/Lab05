package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model m = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnagramma;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private Button btnReset;

    @FXML
    void doCalcola(ActionEvent event) {
    	txtErrati.clear();
    	txtCorretti.clear();
    	
    	
    	String parola = txtAnagramma.getText();
    	    	
    	if(parola.matches("[a-zA-Z]*") && parola.length() != 0) {
    		HashMap<String, Boolean> anagrammi = m.anagrammi(parola);
        	ArrayList<String> corrette = new ArrayList<String>();
        	ArrayList<String> errate = new ArrayList<String>();
    		for(String p : anagrammi.keySet()) {
    			if(anagrammi.get(p) == false) {
    				errate.add(p);
    			}
    			else {
    				corrette.add(p);
    			}
    		}
    		String corr = "";
    		for(String c : corrette) {
    			corr += c+"\n";
    		}
    		String err = "";
    		for(String e : errate) {
    			err += e+"\n";
    		}
    		
    		txtCorretti.setText(corr);
    		txtErrati.setText(err);
    	}
    	else {
    		txtCorretti.setText("Errore");
    		txtErrati.setText("Errore");
    		return;
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtAnagramma.clear();
    	txtCorretti.clear();
    	txtErrati.clear();
    }
    
    public void setModel(Model m) {
		this.m = m;	
	}

    @FXML
    void initialize() {
        assert txtAnagramma != null : "fx:id=\"txtAnagramma\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	
}
