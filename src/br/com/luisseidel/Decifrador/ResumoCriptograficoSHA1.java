package br.com.luisseidel.Decifrador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ResumoCriptograficoSHA1 {

	
	public static byte[] gerarHash(String frase, String algoritmo) {
		
		try {
			
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(frase.getBytes());
			return md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unused")
	public static String stringHexa(byte[] bytes) {
		
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			
			if(parteAlta == 0) {
				s.append("0");
			}
			
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
		
	}
}
