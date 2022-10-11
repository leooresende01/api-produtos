package tk.leooresende01.authstateless.infra.service.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.repository.v1.ProdutoRepository;
import tk.leooresende01.authstateless.infra.util.ProdutoUtil;
import tk.leooresende01.authstateless.infra.util.UsuarioUtil;
import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.Usuario;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private UsuarioService userService;

	public List<ProdutoDto> pegarListaDeProdutos() {
		List<Produto> listaDeProdutos = this.produtoRepo.findAll();
		return ProdutoDto.mapearListaDeProdutoParaDto(listaDeProdutos);
	}

	public ProdutoDto salvarProduto(ProdutoDto produtoForm) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoForm);
		Produto produto = produtoForm.mapearParaProduto();

		Usuario usuario = this.userService.pegarUsuarioAutenticado();
		produto.setDonoDoProduto(usuario);

		Produto produtoSalvo = this.produtoRepo.save(produto);
		this.userService.salvarUsuarioNoDBEPegarDTO(usuario);
		return new ProdutoDto(produtoSalvo);
	}

	public ProdutoDto atualizarProduto(ProdutoDto produtoDto, Integer id) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoDto);
		Produto produto = buscarPeloIdNoDB(id);
		
		this.verificarPermicoesParaEditarOProduto(produto);
		
		Produto produtoAtualizado = produtoDto.atualizarProduto(produto);
		Produto produtoSalvo = this.produtoRepo.save(produtoAtualizado);
		return new ProdutoDto(produtoSalvo);
	}

	public void deletarProduto(Integer id) {
		Produto produto = buscarPeloIdNoDB(id);
		this.verificarPermicoesParaEditarOProduto(produto);
		this.produtoRepo.deleteById(id);
	}

	private Produto buscarPeloIdNoDB(Integer id) {
		return this.produtoRepo.findById(id).get();
	}

	public ProdutoDto buscarProdutoPeloId(Integer id) {
		Produto produto = buscarPeloIdNoDB(id);
		return new ProdutoDto(produto);
	}

	private void verificarPermicoesParaEditarOProduto(Produto produto) {
		Usuario usuarioAutenticado = this.userService.pegarUsuarioAutenticado();
		String donoDoProduto = produto.getDonoDoProduto().getUsername();
		UsuarioUtil.verificaSeElePodeEditarEsseRecurso(donoDoProduto, usuarioAutenticado);
	}
}
