package tk.leooresende01.authstateless.infra.controller.v1.dto;

import java.util.List;

import tk.leooresende01.authstateless.model.Oferta;
import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.StatusDaOferta;

public class OfertaDto {
	private Integer id;
	private StatusDaOferta status;
	private String comprador;
	private ProdutoDto produto;

	public OfertaDto() {
	}

	public OfertaDto(Integer id, UsuarioDto comprador, ProdutoDto produto, StatusDaOferta status) {
		this.id = id;
		this.comprador = comprador.getUsername();
		this.produto = produto;
		this.status = status;
	}

	public OfertaDto(Oferta oferta) {
		this.id = oferta.getId();
		this.comprador = oferta.getComprador().getUsername();
		this.produto = new ProdutoDto(oferta.getProduto());
		this.status = oferta.getStatus();
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public StatusDaOferta getStatus() {
		return status;
	}

	public void setStatus(StatusDaOferta status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static List<OfertaDto> mapearListaDeOfertaParaDto(List<Oferta> ofertas) {
		return ofertas.stream().map(oferta -> new OfertaDto(oferta)).toList();
	}

	public static List<Oferta> atualizarStatusDasOfertasDoProduto(Produto produto, Oferta oferta, OfertaForm ofertaForm) {
		List<Oferta> ofertas = produto.getOfertas();
		StatusDaOferta status = ofertaForm.getStatus();
		return ofertas.stream().map(ofertaMap -> {
			Integer id = ofertaMap.getId();
			if (status.equals(StatusDaOferta.ACEITA)) {
				if (id == oferta.getId()) {
					produto.setVendido(true);
					ofertaMap.setStatus(status);
				} else {
					ofertaMap.setStatus(StatusDaOferta.REJEITADA);
				}
				return ofertaMap;
			}
			if (id == oferta.getId()) {
				ofertaMap.setStatus(status);
			}
			return ofertaMap;
		}).toList();
	}

	public static OfertaDto adicionarIdAOfertaEPegarDto(Oferta oferta, Integer id) {
		oferta.setId(id);
		return new OfertaDto(oferta);
	}
}
