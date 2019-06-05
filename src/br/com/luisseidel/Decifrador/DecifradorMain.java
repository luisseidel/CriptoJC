package br.com.luisseidel.Decifrador;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DecifradorMain {
	public static void main(String[] args) throws Exception {
		
		//objetos
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		FileReader file = null;
		
		//variaveis
		Long numeroCasas = 0L;
		String token, textoCifrado, textoDecifrado, resumoCriptografico;
		token = textoCifrado = textoDecifrado = resumoCriptografico = "";
		
		System.out.println("---- LENDO ARQUIVO JSON ----");
		//Ler o arquivo json e guardar em memoria os valores
		try {
			file = new FileReader("answer.json");
			json = (JSONObject) parser.parse(file);
			
			numeroCasas = (Long) json.get("numero_casas");
			token = (String) json.get("token");
			textoCifrado = (String) json.get("cifrado");
			textoDecifrado = Decifrador.decifrar(Long.toString(numeroCasas), textoCifrado);
			resumoCriptografico = ResumoCriptograficoSHA1.stringHexa(
					ResumoCriptograficoSHA1.gerarHash(textoDecifrado, "SHA-1")).toUpperCase();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			

		//Escreve em uma string os valores formatados
		String stringJson = "{\n"
				+ "\"numero_casas\":" + numeroCasas + ",\n"
					+ "\"token\":" + "\"" + token + "\",\n"
						+ "\"cifrado\":" + "\"" + textoCifrado + "\",\n"
								+ "\"decifrado\":" + "\"" + textoDecifrado + "\",\n"
										+ "\"resumo_criptografico\":" + "\"" + resumoCriptografico + "\"\n"
										+ "}";
		
		
		System.out.println(stringJson);
		System.out.println("--- ESCREVENDO NO ARQUIVO answer.json ----");
		
		//Escreve no arquivo json
		FileWriter jsonFile = null;
		try {
			jsonFile = new FileWriter("answer.json");
			jsonFile.write(stringJson);
			jsonFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Realizar conexão e enviar
		//https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42
		//URL teste ==> https://httpbin.org/post
/*		
		final String USER_AGENT = "Mozilla/5.0";
		String url = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=25f95d6af0cbac6104b8a3dce578b8090e09ba42";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		//-------------
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("numero_casas", numeroCasas));
		urlParameters.add(new BasicNameValuePair("token", token));
		urlParameters.add(new BasicNameValuePair("cifrado", textoCifrado));
		urlParameters.add(new BasicNameValuePair("decifrado", textoDecifrado));
		urlParameters.add(new BasicNameValuePair("resumo_criptografico", resumoCriptografico));
		//-------------
		
		post.setHeader("User-Agent", USER_AGENT);
		post.setHeader("Content-Type", "multipart/form-data");
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		HttpResponse response = client.execute(post);
		System.out.println("Enviando POST para a url: " + url);
		System.out.println("Parametros do POST: " + post.getEntity());
		System.out.println("Codigo de resposta: " + response.getStatusLine().getStatusCode());
		
		BufferedReader brd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		
		while((line = brd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
		*/
	}
}

