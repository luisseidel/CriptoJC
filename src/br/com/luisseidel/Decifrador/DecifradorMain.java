package br.com.luisseidel.Decifrador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
			objDec.setNumeroCasas((String) my_obj.get("numero_casas"));
			objDec.setToken((String) my_obj.get("token"));
			objDec.setTextoCifrado((String) my_obj.get("cifrado"));
			objDec.setTextoDecifrado(Decifrador.decifrar(
					(String) my_obj.get("numero_casas"), objDec.getTextoCifrado()));
			objDec.setResumoCriptografico(
					ResumoCriptograficoSHA1.stringHexa(
							ResumoCriptograficoSHA1.gerarHash(objDec.getTextoDecifrado(), "SHA-1")).toUpperCase());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		
		//Escrever Objeto JSON
/*		JSONObject objJson = new JSONObject();
		objJson.put("numero_casas", objDec.getNumeroCasas());
		objJson.put("token", objDec.getToken());
		objJson.put("cifrado", objDec.getTextoCifrado());
		objJson.put("decifrado", objDec.getTextoDecifrado());
		objJson.put("resumo_criptografico", objDec.getResumoCriptografico());*/
		
		FileWriter writeFile = null;
		String stringJson = "{\n"
				+ "\"numero_casas\":" + "\"" + objDec.getNumeroCasas() + "\",\n"
					+ "\"token\":" + "\"" + objDec.getToken() + "\",\n"
						+ "\"cifrado\":" + "\"" + objDec.getTextoCifrado() + "\",\n"
								+ "\"decifrado\":" + "\"" + objDec.getTextoDecifrado() + "\",\n"
										+ "\"resumo_criptografico\":" + "\"" + objDec.getResumoCriptografico() + "\"\n"
										+ "}";

		
		System.out.println(stringJson);
		
		try {
			writeFile = new FileWriter("answer.json");
			writeFile.write(stringJson);
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("---- ENVIANDO ARQUIVO JSON ----\n");
		
		/*try {
			System.out.println(post(stringJson));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		//Enviar Objeto JSON via POST
		//https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42
		//URL teste ==> https://httpbin.org/post
		
		/*
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpPost request = new HttpPost("https://httpbin.org/post");
            request.addHeader("Content-Type", "application/json");
            request.addHeader("file", "answer");
            request.setEntity(new StringEntity(stringJson));

            HttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            System.out.println(builder);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        */
	}
	/*
	public static String post(String json) throws IOException {
		 
        URL url = new URL("https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42");
 
        HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
 
        connection.setRequestMethod("POST");
 
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
 
        connection.setDoOutput(true);
 
 
        PrintStream printStream =
            new PrintStream(connection.getOutputStream());
        printStream.println(json);
 
        connection.connect();
 
        String jsonDeResposta =
            new Scanner(connection.getInputStream()).next();
 
        return connection.getResponseMessage();
    }
*/
}

