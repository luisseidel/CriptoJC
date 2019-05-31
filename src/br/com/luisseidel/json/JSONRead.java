package br.com.luisseidel.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
	public static void main(String[] args) {
        
		JSONObject jsonObject;
       
		//Cria o parse de tratamento
        JSONParser parser = new JSONParser();
        
        //Variaveis que irao armazenar os dados do arquivo JSON
        String numeroCasas;
        String token;
        String cifrado;
        String decifrado;
        String resumoCriptografico;
 
        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader("saida.json"));
             
            //Salva nas variaveis os dados retirados do arquivo
            numeroCasas = (String) jsonObject.get("numero_casas");
            token = (String) jsonObject.get("token");
            cifrado = (String) jsonObject.get("cifrado");
            decifrado = (String) jsonObject.get("decifrado");
            resumoCriptografico = (String) jsonObject.get("resumo_criptografico");
 
            System.out.printf(
                    "Número de Casas: %s\nToken: %s\nCifrado: %s\nDecifrado: %s\nResumoCriptográfico: %s\n", numeroCasas, token, cifrado, decifrado, resumoCriptografico);
        } 
        
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
}
