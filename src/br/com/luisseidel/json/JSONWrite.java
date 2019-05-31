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
		jsonObject.put("numero_casas", "10");
		jsonObject.put("token", "25f95d6af0cbac6104b8a3dce578b8090e09ba42");
		jsonObject.put("cifrado", "ofobi lsq mywzedsxq nsckcdob rkc mywo pbyw dkusxq dyy wkxi snokc kxn zeddsxq drow sx yxo zvkmo. qybnyx lovv");
		jsonObject.put("decifrado", //chamar metodo para decifrar);
		jsonObject.put("resumo_criptografico", "");
		
		try {
			//Cria o arquivo com seu respectivo nome
			writeFile = new FileWriter("answer.json");
			
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
