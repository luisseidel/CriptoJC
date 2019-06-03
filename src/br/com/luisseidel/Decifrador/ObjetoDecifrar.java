package br.com.luisseidel.Decifrador;

public class ObjetoDecifrar {

	private String numeroCasas, token, textoCifrado, textoDecifrado, resumoCriptografico;

	public ObjetoDecifrar() {
		
	}
	
	public ObjetoDecifrar(String numeroCasas, String token, String textoCifrado, String textoDecifrado,
			String resumoCriptografico) {
		super();
		this.numeroCasas = numeroCasas;
		this.token = token;
		this.textoCifrado = textoCifrado;
		this.textoDecifrado = textoDecifrado;
		this.resumoCriptografico = resumoCriptografico;
	}
	
	//------ Getters and Setters

	public String getNumeroCasas() {
		return numeroCasas;
	}

	public void setNumeroCasas(String numeroCasas) {
		this.numeroCasas = numeroCasas;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTextoCifrado() {
		return textoCifrado;
	}

	public void setTextoCifrado(String textoCifrado) {
		this.textoCifrado = textoCifrado;
	}

	public String getTextoDecifrado() {
		return textoDecifrado;
	}

	public void setTextoDecifrado(String textoDecifrado) {
		this.textoDecifrado = textoDecifrado;
	}

	public String getResumoCriptografico() {
		return resumoCriptografico;
	}

	public void setResumoCriptografico(String resumoCriptografico) {
		this.resumoCriptografico = resumoCriptografico;
	}
	
	@Override
	public String toString() {
		return "Objeto JSON: ( numeroCasas: " + numeroCasas + ", token: " + token + ", textoCifrado: " + textoCifrado + ", textoDecifrado: " + textoDecifrado + ", resumoCriptografico: " + resumoCriptografico;
	}
	
}
