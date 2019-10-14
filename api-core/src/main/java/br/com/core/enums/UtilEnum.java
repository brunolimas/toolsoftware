package br.com.core.enums;

import lombok.Getter;

public enum UtilEnum {
	
	EXTENSAO_ARQUIVO(".dat");
	
	@Getter
	public String valor;
	
	UtilEnum(String valor){
		this.valor = valor;
	}
}
