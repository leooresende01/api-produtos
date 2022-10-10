package tk.leooresende01.authstateless.infra.controller.v1.dto;

import java.math.BigDecimal;
import java.util.List;

import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.TipoDoProduto;

public class ProdutoDto {
	private Integer id;
	private String nome;
	private BigDecimal preco;
	private TipoDoProduto tipo;

	public ProdutoDto() {
	}

	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.tipo = produto.getTipo();
		this.setId(produto.getId());
	}

	public ProdutoDto(String nome, BigDecimal preco, TipoDoProduto tipo, Integer id) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
		this.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public TipoDoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoDoProduto tipo) {
		this.tipo = tipo;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Produto mapearParaProduto() {
		return new Produto(this.nome, this.preco, this.tipo);
	}
	
	public static List<ProdutoDto> mapearListaDeProdutoParaDto(List<Produto> listaDeProdutos) {
		return listaDeProdutos.stream()
				.map(ProdutoDto::new).toList();
	}

	public Produto atualizarProduto(Produto produto) {
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		produto.setTipo(this.tipo);
		return produto;
	}
}
