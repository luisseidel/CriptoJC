package br.com.luisseidel.Decifrador;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DecifradorMain {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//Ler Objeto JSON
		JSONObject my_obj;
		JSONParser parser = new JSONParser();
		ObjetoDecifrar objDec = new ObjetoDecifrar();
		
		try {
			my_obj = (JSONObject) parser.parse(new FileReader("answer.json"));
			objDec.setNumeroCasas((String) my_obj.get("numeroCasas"));
			objDec.setToken((String) my_obj.get("token"));
			objDec.setTextoCifrado((String) my_obj.get("textoCifrado"));
			objDec.setTextoDecifrado(Decifrador.decifrar((String) my_obj.get("numeroCasas"), objDec.getTextoCifrado()));
			objDec.setResumoCriptografico(ResumoCriptograficoSHA1.stringHexa(ResumoCriptograficoSHA1.gerarHash(objDec.getTextoDecifrado(), "SHA-1")));
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		
		//Escrever Objeto JSON
		FileWriter writeFile = null;
		JSONObject objJson = new JSONObject();
		
		objJson.put("numeroCasas", objDec.getNumeroCasas());
		objJson.put("token", objDec.getToken());
		objJson.put("textoCifrado", objDec.getTextoCifrado());
		objJson.put("textoDecifrado", objDec.getTextoDecifrado());
		objJson.put("resumoCriptografico", objDec.getResumoCriptografico());
		
		System.out.println(objJson.toJSONString());
		
		try {
			writeFile = new FileWriter("answer.json");
			writeFile.write(objJson.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Enviar Objeto JSON via POST
		//https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42
		
		URL url;
		
		try {
			url =new URL("https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42");
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			byte[] out = objJson.toString().getBytes(StandardCharsets.UTF_8);
			int leng = out.length;
			
			http.setFixedLengthStreamingMode(leng);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(out);
		} catch (Exception e) {

		}
	}
}

