package br.com.core.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.core.entity.Cliente;
import br.com.core.entity.Venda;
import br.com.core.entity.Vendedor;
import br.com.core.enums.MensagensEnum;
import br.com.core.enums.UtilEnum;
import br.com.core.exceptions.NegocioException;

@Service
public class ArquivoService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendasService vendasService;

	@Autowired
	private VendedoresService vendedoresService;

	public String recuperarArquivosParaImportacao(String pathImportacao) throws IOException {

		// VERIFICAR SE O PATH DE IMPORTACAO EXISTE
		if (isPathExists(pathImportacao)) {

			// RECUPERAR ARQUIVOS DA PASTA PARA REALIZAR AS DEVIDAS IMPORTACOES
			File[] arquivosEncontrados = recuperarArquivos(pathImportacao);

			for (int i = 0; i < arquivosEncontrados.length; i++) {

				File arquivoLeitura = new File(pathImportacao, arquivosEncontrados[i].getName());
				BufferedReader arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoLeitura)));

				for (String linhaArquivo = arquivo.readLine(); linhaArquivo != null; linhaArquivo = arquivo
						.readLine()) {

					String tipoRegistro = linhaArquivo.substring(0, 3);

					// REALIZAR IMPORTACAO DOS REGISTROS POR TIPO
					importarArquivoPorTipo(tipoRegistro, linhaArquivo);
				}

			}
			gerarRelatorio(pathImportacao);
		} else {
			throw new NegocioException(MensagensEnum.PATH_INEXISTENTE.getCode());
		}

		return MensagensEnum.ARQUIVO_PROCESSADO.getCode();
	}

	private void gerarRelatorio(String pathImportacao) throws IOException {
		
		File pathExportacao = new File(pathImportacao.replace("/in", "/out"));
		
		if(!pathExportacao.exists()) {
			pathExportacao.mkdirs();
		}
		
		File arquivoRelatorio = new File(pathExportacao, "relatorio.dat.proc");

		arquivoRelatorio.getParentFile().mkdirs();

		BufferedWriter arquivo = new BufferedWriter(new FileWriter(arquivoRelatorio, true));
		
		arquivo.write("---- Desafio ----");
		arquivo.newLine();
		arquivo.write("Relatório gerado dia:" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		arquivo.newLine();
		arquivo.write("Quantidade de Clientes: " + clienteService.quantidadeClientes());
		arquivo.newLine();
		arquivo.write("Quantidade de Vendedores: " + vendedoresService.quantidadeVendedores());
		arquivo.newLine();
		arquivo.write("ID da Venda de valor mais alto: " + vendasService.recuperarVendaValorMaior());
		arquivo.newLine();
		arquivo.write("Nome do Vendedor que menos vendeu: " + vendasService.recuperarVendedorMenorNumeroVenda());
		
		arquivo.flush();
		arquivo.close();
	}

	private void importarArquivoPorTipo(String tipoRegistro, String linhaArquivo) {
		switch (tipoRegistro) {
		case "001":
			importarVendedores(linhaArquivo);
			break;

		case "002":
			importarClientes(linhaArquivo);
			break;

		case "003":
			importarVendas(linhaArquivo);
			break;
		}
	}

	private void importarVendas(String linhaArquivo) {
		String[] itens = linhaArquivo.split(";");

		if (itens.length != 0) {
			Venda venda = new Venda();

			venda.setIdVenda((itens[1] == null) ? "" : itens[1]);
			venda.setIdItem((itens[2] == null) ? "" : itens[2]);
			venda.setQtdItem((itens[3] == null) ? "" : itens[3]);
			venda.setValorItem((itens[4] == null) ? "" : itens[4]);
			venda.setNomeVendedor((itens[5] == null) ? "" : itens[5]);

			vendasService.importar(venda);
		}
	}

	private void importarVendedores(String linhaArquivo) {
		String[] itens = linhaArquivo.split(";");

		if (itens.length != 0) {

			Vendedor vendedor = new Vendedor();

			vendedor.setCpf((itens[1] == null) ? "" : itens[1]);
			vendedor.setNome((itens[2] == null) ? "" : itens[2]);
			vendedor.setSalario((itens[3] == null) ? "" : itens[3]);

			vendedoresService.importar(vendedor);
		}
	}

	private void importarClientes(String linhaArquivo) {
		String[] itens = linhaArquivo.split(";");

		if (itens.length != 0) {
			Cliente cliente = new Cliente();

			cliente.setCnpj((itens[1] == null) ? "" : itens[1]);
			cliente.setNome((itens[2] == null) ? "" : itens[2]);
			cliente.setRamoAtividade((itens[3] == null) ? "" : itens[3]);

			clienteService.importar(cliente);
		}
	}

	private File[] recuperarArquivos(String pathImportacao) {
		return new File(pathImportacao).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(UtilEnum.EXTENSAO_ARQUIVO.getValor());
			}
		});
	}

	private boolean isPathExists(String pathImportacao) {
		return new File(pathImportacao).exists();
	}
}
