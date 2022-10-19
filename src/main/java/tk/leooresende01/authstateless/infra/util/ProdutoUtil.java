package tk.leooresende01.authstateless.infra.util;

import org.springframework.http.converter.HttpMessageNotReadableException;

import tk.leooresende01.authstateless.infra.controller.advice.exception.OfertaException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.ProdutoException;
import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.model.Oferta;
import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.StatusDaOferta;
import tk.leooresende01.authstateless.model.Usuario;

public class ProdutoUtil {

	@SuppressWarnings("deprecation")
	public static void verificaSeOProdutoFormEhValido(ProdutoDto produtoDto) {
		boolean isNull = produtoDto.getNome() == null || produtoDto.getPreco() == null || produtoDto.getTipo() == null;
		boolean isBlank = produtoDto.getNome().isBlank() || produtoDto.getPreco().toString().isBlank()
				|| produtoDto.getTipo().toString().isBlank();
		if (isNull || isBlank) {
			throw new HttpMessageNotReadableException("Formulario invalido");
		}
	}

	public static void verificarPermicoesParaEditarOProduto(Produto produto) {
		Usuario usuarioAutenticado = UsuarioUtil.pegarUsuarioAutenticado();
		String donoDoProduto = produto.getDonoDoProduto().getUsername();
		UsuarioUtil.verificaSeElePodeEditarEsseRecurso(donoDoProduto, usuarioAutenticado);
	}

	public static void validarOferta(Produto produto, Usuario usuario) {
		if (produto.getDonoDoProduto().getUsername().equals(usuario.getUsername())) {
			throw new OfertaException("Não é possivel fazer uma oferta a um produto seu");
		}
		try {
			produto.getOfertas().stream()
					.filter(oferta -> oferta.getComprador().getUsername().equals(usuario.getUsername())).findFirst()
					.get();
		} catch (Exception ex) {
			return;
		}
		throw new OfertaException("Você já fez uma oferta");
	}

	public static Oferta pegarOfertaPeloId(Produto produto, Integer idDaOferta) {
		return produto.getOfertas().stream()
			.filter(oferta -> oferta.getId() == idDaOferta)
			.findFirst()
			.get();
	}

	public static void verificaSeOStatusDaOfertaJaFoiModificado(StatusDaOferta status) {
		if (!status.name().equals(StatusDaOferta.EM_ANDAMENTO.name())) {
			throw new OfertaException("Você não pode mais alterar o status dessa oferta");
		}
	}

	public static void verificaSeOProdutoJaFoiVendido(Produto produto) {
		if (produto.getVendido()) {
			throw new ProdutoException("Esse produto já foi vendido");
		}
	}

	public static void verificaSeACompraDesseUsuarioFoiAprovadaEResetaProduto(Produto produto, Usuario usuarioAutenticado) {
		Oferta ofertaDoUsuario = produto.getOfertas().stream()
			.filter(oferta -> oferta.getComprador().getUsername().equals(usuarioAutenticado.getUsername()))
			.findFirst()
			.get();
		if (!ofertaDoUsuario.getStatus().equals(StatusDaOferta.ACEITA)) {
			throw new OfertaException("Sua oferta não foi aceita");
		}
		produto.setDonoDoProduto(usuarioAutenticado);
		produto.setVendido(false);
	}
}
