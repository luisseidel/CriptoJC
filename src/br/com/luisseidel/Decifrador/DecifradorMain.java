package br.com.luisseidel.Decifrador;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DecifradorMain {
	public static void main(String[] args) {
			
		JSONObject my_obj;
		JSONParser parser = new JSONParser();
		ObjetoDecifrar objDec = new ObjetoDecifrar();
		
		try {
			my_obj = (JSONObject) parser.parse(new FileReader("answer.json"));
			objDec.setNumeroCasas(Long.toString((long) my_obj.get("numero_casas")));
			objDec.setToken((String) my_obj.get("token"));
			objDec.setTextoCifrado((String) my_obj.get("cifrado"));
			objDec.setTextoDecifrado(Decifrador.decifrar(Long.toString((long) my_obj.get("numero_casas")), objDec.getTextoCifrado()));
			objDec.setResumoCriptografico(ResumoCriptograficoSHA1.stringHexa(ResumoCriptograficoSHA1.gerarHash(objDec.getTextoDecifrado(), "SHA-1")));
		
			System.out.println("VALROES RECUPERADOS DO JSON");
			System.out.println(objDec.toString());
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}
