package tk.leooresende01.authstateless.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@JoinColumn(name = "usuario_id")
	private Usuario donoDoProduto;
	@Enumerated(EnumType.STRING)
	private TipoDoProduto tipo;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "produto", fetch = FetchType.LAZY)
	private List<Oferta> ofertas = new ArrayList<>();
	private boolean vendido = false;

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

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}
}
