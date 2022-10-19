package tk.leooresende01.authstateless.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ofertas")
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario comprador;
	@Enumerated(EnumType.STRING)
	private StatusDaOferta status;

	public Oferta(Produto produto, Usuario comprador, StatusDaOferta status) {
		this.produto = produto;
		this.comprador = comprador;
		this.status = status;
	}

	public Oferta() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public StatusDaOferta getStatus() {
		return status;
	}

	public void setStatus(StatusDaOferta status) {
		this.status = status;
	}
}
