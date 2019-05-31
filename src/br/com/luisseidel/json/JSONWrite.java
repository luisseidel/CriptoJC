package br.com.luisseidel.json;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class JSONWrite {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//Cria um objeto JSON
		JSONObject jsonObject = new JSONObject();
		
		FileWriter writeFile = null;
		
		//Armazena dados em um Objeto JSON
		jsonObject.put("nome", "Luis");
		jsonObject.put("sobrenome", "Guilherme");
		jsonObject.put("pais", "Brasil");
		jsonObject.put("estado", "RS");
		
		try {
			//Cria o arquivo com seu respectivo nome
			writeFile = new FileWriter("saida.json");
			
			//Escreve o conteúdo no arquivo e fecha o arquivo
			writeFile.write(jsonObject.toJSONString());
			writeFile.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//imprime o objeto json para visualizar
		System.out.println(jsonObject);
	}
}
