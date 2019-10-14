package br.com.core.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.core.enums.MensagensEnum;
import br.com.core.exceptions.NegocioException;
import br.com.core.services.ArquivoService;

@Controller
@RequestMapping("/api/importacao")
public class ImportacaoArquivoController {

	@Autowired
	private ArquivoService arquivoService;
	
	@GetMapping
	public ResponseEntity<String> importarArquivo(@PathParam("path") @Validated String path){
		try {
			
			if(path.isEmpty()) {
				throw new NegocioException(MensagensEnum.PATH_INVALIDO.getCode());
			}
			
			return new ResponseEntity<String>(arquivoService.recuperarArquivosParaImportacao(path), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
