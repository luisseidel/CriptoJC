package br.com.luisseidel.Decifrador;
import org.json.JSONObject;

public class DecifradorMain {
	public static void main(String[] args) {
		
		String numero_casas = "10";
		String token = "25f95d6af0cbac6104b8a3dce578b8090e09ba42";
		String cifrado = "ofobi lsq mywzedsxq nsckcdob rkc mywo pbyw dkusxq dyy wkxi snokc kxn zeddsxq drow sx yxo zvkmo. qybnyx lovv";
		String decifrado = Decifrador.decifrar(Integer.parseInt(numero_casas), cifrado);
		String resumo_criptografico = "";
		
		JSONObject my_obj = new JSONObject();
		my_obj.put("numero_casas", numero_casas);
		my_obj.put("token", token);
		my_obj.put("cifrado", cifrado);
		my_obj.put("decifrado", decifrado);
		my_obj.put("resumo_criptografico", resumo_criptografico);
		
		//System.out.println(Decifrador.decifrar(10, "ofobi lsq mywzedsxq nsckcdob rkc mywo pbyw dkusxq dyy wkxi snokc kxn zeddsxq drow sx yxo zvkmo. qybnyx lovv"));
	}
}
