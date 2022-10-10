package tk.leooresende01.authstateless.infra.controller.v1;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.service.v1.ProdutoService;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
	private static final String API_PRODUTO_PATH = "/api/v1/produtos/";
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> buscarTodosOsProdutos() {
		List<ProdutoDto> pegarListaDeProdutos = this.service.pegarListaDeProdutos();
		return ResponseEntity.ok(pegarListaDeProdutos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> registrarProduto(@RequestBody(required = true) ProdutoDto produtoForm, UriComponentsBuilder url) throws URISyntaxException {
		ProdutoDto produtoSalvo = this.service.salvarProduto(produtoForm);
		URI urlLocation = new URI(url.build().toUriString() + API_PRODUTO_PATH + produtoSalvo.getId());
		return ResponseEntity.created(urlLocation).body(produtoSalvo);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizarProduto(@RequestBody ProdutoDto produtoDto, @PathVariable Integer id) {
		ProdutoDto produtoAtualizado = this.service.atualizarProduto(produtoDto, id);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
		this.service.deletarProduto(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> buscarProdutoPeloId(@PathVariable Integer id) {
		ProdutoDto produtoDto = this.service.buscarProdutoPeloId(id);
		return ResponseEntity.ok(produtoDto);
	}
}
