package tk.leooresende01.authstateless.infra.util;

import org.springframework.http.converter.HttpMessageNotReadableException;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;

public class ProdutoUtil {

	@SuppressWarnings("deprecation")
	public static void verificaSeOProdutoFormEhValido(ProdutoDto produtoDto) {
		if (produtoDto.getNome() == null || produtoDto.getPreco() == null || produtoDto.getTipo() == null) {
			throw new HttpMessageNotReadableException("");
		}
		
		if (produtoDto.getNome().isBlank() || produtoDto.getPreco().toString().isBlank() || produtoDto.getTipo().toString().isBlank()) {
			throw new HttpMessageNotReadableException("");
		}
	}

}
