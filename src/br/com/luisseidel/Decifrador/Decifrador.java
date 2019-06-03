package br.com.luisseidel.Decifrador;

import java.util.Arrays;
import java.util.List;

public class Decifrador {

	public static String decifrar(String numeroCasas, String textoCifrado) {
		List<String> listaNEP = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", ".");
		StringBuffer resultado = new StringBuffer();
		textoCifrado = textoCifrado.toLowerCase();
		
		for(int i = 0; i < textoCifrado.length(); i++) {
			if(listaNEP.contains(Character.toString(textoCifrado.charAt(i)))) {
				resultado.append(textoCifrado.charAt(i));
			} else {
				resultado.append(buscaLetras(Character.toString(textoCifrado.charAt(i)), Integer.parseInt(numeroCasas)));
			}
		}
		return resultado.toString();
	}
	
	//verificar a qual index do alfabeto o caractere do texto cifrado se refere e remover 10 casas
	//retornando o caractere verdadeiro
	
	private static String buscaLetras(String s, int numeroCasas) {
		List<String> listaLetras = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
		int idx = 0;
		if(listaLetras.contains(s)) {
			if((listaLetras.indexOf(s) - numeroCasas) >= 0) {
				idx = listaLetras.indexOf(s) - numeroCasas;
			} else {
				idx = listaLetras.size() - (numeroCasas - listaLetras.indexOf(s));
			}
		}
		return listaLetras.get(idx);
	}
}
