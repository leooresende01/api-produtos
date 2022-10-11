package tk.leooresende01.authstateless.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private BigDecimal preco;
	@ManyToOne
	private Usuario donoDoProduto;
	@Enumerated(EnumType.STRING)
	private TipoDoProduto tipo;

	public Produto(String nome, BigDecimal preco, TipoDoProduto tipo) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
	}

	public Produto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Usuario getDonoDoProduto() {
		return donoDoProduto;
	}

	public void setDonoDoProduto(Usuario donoDoProduto) {
		this.donoDoProduto = donoDoProduto;
	}

}
