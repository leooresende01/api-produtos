package tk.leooresende01.authstateless.infra.service.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.repository.ProdutoRepository;
import tk.leooresende01.authstateless.infra.util.ProdutoUtil;
import tk.leooresende01.authstateless.model.Produto;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	public List<ProdutoDto> pegarListaDeProdutos() {
		List<Produto> listaDeProdutos = this.produtoRepo.findAll();
		return ProdutoDto.mapearListaDeProdutoParaDto(listaDeProdutos);
	}

	public ProdutoDto salvarProduto(ProdutoDto produtoForm) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoForm);
		Produto produto = produtoForm.mapearParaProduto();
		Produto produtoSalvo = this.produtoRepo.save(produto);
		return new ProdutoDto(produtoSalvo);
	}

	public ProdutoDto atualizarProduto(ProdutoDto produtoDto, Integer id) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoDto);
		Produto produto = this.produtoRepo.findById(id).get();
		Produto produtoAtualizado = produtoDto.atualizarProduto(produto);
		Produto produtoSalvo = this.produtoRepo.save(produtoAtualizado);
		return new ProdutoDto(produtoSalvo);
	}

	public void deletarProduto(Integer id) {
		this.produtoRepo.deleteById(id);
	}

	public ProdutoDto buscarProdutoPeloId(Integer id) {
		Produto produto = this.produtoRepo.findById(id).get();
		return new ProdutoDto(produto);
	}
}
