package br.com.core.enums;


public enum MensagensEnum {

	OBJETO_NAO_ENCONTRADO("objeto.nao.encontrado"),
	
	ARQUIVO_PROCESSADO("Arquivo importado e gerado com Sucesso!"), 
	
	PATH_INVALIDO("Path do Arquivo Inválido!"), 
	
	PATH_INEXISTENTE("Path informado não existe!");

	private String code;

	MensagensEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}