package tk.leooresende01.authstateless.infra.controller.v1.dto;

import java.math.BigDecimal;
import java.util.List;

import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.TipoDoProduto;
import tk.leooresende01.authstateless.model.Usuario;

public class ProdutoDto {
	private Integer id;
	private String nome;
	private BigDecimal preco;
	private String donoDoProduto;
	private TipoDoProduto tipo;
	private Boolean vendido;

	public ProdutoDto() {
	}

	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.tipo = produto.getTipo();
		this.setId(produto.getId());
		this.donoDoProduto = produto.getDonoDoProduto().getUsername();
		this.vendido = produto.getVendido();
	}

	public ProdutoDto(String nome, BigDecimal preco, TipoDoProduto tipo, Integer id, Usuario donoDoProduto) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
		this.donoDoProduto = donoDoProduto.getUsername();
		this.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public String getDonoDoProduto() {
		return donoDoProduto;
	}

	public void setDonoDoProduto(String donoDoProduto) {
		this.donoDoProduto = donoDoProduto;
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
		return listaDeProdutos.stream().map(ProdutoDto::new).toList();
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}

	public Produto atualizarProduto(Produto produto) {
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		produto.setTipo(this.tipo);
		return produto;
	}
}
