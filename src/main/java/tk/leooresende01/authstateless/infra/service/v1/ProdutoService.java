package tk.leooresende01.authstateless.infra.service.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.controller.v1.dto.OfertaDto;
import tk.leooresende01.authstateless.infra.controller.v1.dto.OfertaForm;
import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.repository.v1.OfertaRepository;
import tk.leooresende01.authstateless.infra.repository.v1.ProdutoRepository;
import tk.leooresende01.authstateless.infra.util.ProdutoUtil;
import tk.leooresende01.authstateless.infra.util.UsuarioUtil;
import tk.leooresende01.authstateless.model.Oferta;
import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.StatusDaOferta;
import tk.leooresende01.authstateless.model.Usuario;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private UsuarioService userService;

	@Autowired
	private OfertaRepository ofertaRepo;

	public List<ProdutoDto> pegarListaDeProdutos() {
		List<Produto> listaDeProdutos = this.produtoRepo.findAll();
		return ProdutoDto.mapearListaDeProdutoParaDto(listaDeProdutos);
	}

	public ProdutoDto salvarProduto(ProdutoDto produtoForm) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoForm);
		Produto produto = produtoForm.mapearParaProduto();

		Usuario usuario = UsuarioUtil.pegarUsuarioAutenticado();
		produto.setDonoDoProduto(usuario);

		Produto produtoSalvo = this.produtoRepo.save(produto);
		this.userService.salvarUsuarioNoDBEPegarDTO(usuario);
		return new ProdutoDto(produtoSalvo);
	}

	public ProdutoDto atualizarProduto(ProdutoDto produtoDto, Integer id) {
		ProdutoUtil.verificaSeOProdutoFormEhValido(produtoDto);
		Produto produto = buscarPeloIdNoDB(id);

		ProdutoUtil.verificarPermicoesParaEditarOProduto(produto);
		ProdutoUtil.verificaSeOProdutoJaFoiVendido(produto);

		Produto produtoAtualizado = produtoDto.atualizarProduto(produto);
		Produto produtoSalvo = this.produtoRepo.save(produtoAtualizado);
		return new ProdutoDto(produtoSalvo);
	}

	public void deletarProduto(Integer id) {
		Produto produto = buscarPeloIdNoDB(id);

		ProdutoUtil.verificarPermicoesParaEditarOProduto(produto);
		ProdutoUtil.verificaSeOProdutoJaFoiVendido(produto);

		this.produtoRepo.deleteById(id);
	}

	public List<OfertaDto> buscarOfertasDoProduto(Integer id) {
		Produto produto = this.buscarPeloIdNoDB(id);
		ProdutoUtil.verificarPermicoesParaEditarOProduto(produto);
		List<Oferta> ofertas = produto.getOfertas();
		return OfertaDto.mapearListaDeOfertaParaDto(ofertas);
	}

	public OfertaDto criarOfertaParaUmProduto(Integer id) {
		Usuario usuario = UsuarioUtil.pegarUsuarioAutenticado();
		Produto produto = this.buscarPeloIdNoDB(id);

		ProdutoUtil.validarOferta(produto, usuario);
		ProdutoUtil.verificaSeOProdutoJaFoiVendido(produto);

		Oferta oferta = new Oferta(produto, usuario, StatusDaOferta.EM_ANDAMENTO);
		Oferta ofertaSalva = this.ofertaRepo.save(oferta);
		return new OfertaDto(ofertaSalva);
	}

	public OfertaDto atualizarStatusDaOferta(Integer id, Integer idDaOferta, OfertaForm ofertaForm) {
		Produto produto = this.buscarPeloIdNoDB(id);
		Oferta oferta = ProdutoUtil.pegarOfertaPeloId(produto, idDaOferta);

		UsuarioUtil.verificarSeOUsuarioTemPemicoesDeEditar(produto.getDonoDoProduto().getUsername());
		ProdutoUtil.verificaSeOStatusDaOfertaJaFoiModificado(oferta.getStatus());
		ProdutoUtil.verificaSeOProdutoJaFoiVendido(produto);

		List<Oferta> ofertas = OfertaDto.atualizarStatusDasOfertasDoProduto(produto, oferta, ofertaForm);
		this.ofertaRepo.saveAll(ofertas);
		this.produtoRepo.save(produto);
		return OfertaDto.adicionarIdAOfertaEPegarDto(oferta, idDaOferta);
	}

	public void cancelarOfertaDoProduto(Integer id, Integer idDaOferta) {
		Produto produto = this.buscarPeloIdNoDB(id);
		Oferta oferta = ProdutoUtil.pegarOfertaPeloId(produto, idDaOferta);
		UsuarioUtil.verificarSeOUsuarioTemPemicoesDeEditar(oferta.getComprador().getUsername());
		this.ofertaRepo.deleteById(idDaOferta);
	}

	public OfertaDto buscarProdutoPeloId(Integer id, Integer idDaOferta) {
		Produto produto = this.buscarPeloIdNoDB(id);
		Oferta oferta = ProdutoUtil.pegarOfertaPeloId(produto, idDaOferta);
		UsuarioUtil.verificarSeOUsuarioTemPemicoesDeEditar(oferta.getComprador().getUsername());
		return new OfertaDto(oferta);
	}

	public ProdutoDto receberProduto(Integer id) {
		Produto produto = this.buscarPeloIdNoDB(id);
		Usuario usuarioAutenticado = UsuarioUtil.pegarUsuarioAutenticado();
		
		ProdutoUtil.verificaSeACompraDesseUsuarioFoiAprovadaEResetaProduto(produto, usuarioAutenticado);
		this.produtoRepo.deleteById(produto.getId());
		
		produto.setOfertas(new ArrayList<Oferta>());
		Produto produtoSalvo = this.produtoRepo.save(produto);
		return new ProdutoDto(produtoSalvo);
	}

	public ProdutoDto buscarProdutoPeloId(Integer id) {
		Produto produto = buscarPeloIdNoDB(id);
		return new ProdutoDto(produto);
	}

	private Produto buscarPeloIdNoDB(Integer id) {
		return this.produtoRepo.findById(id).get();
	}

}
