package tk.leooresende01.authstateless.infra.controller.v1;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import tk.leooresende01.authstateless.infra.controller.v1.dto.UsuarioDto;
import tk.leooresende01.authstateless.infra.controller.v1.dto.UsuarioForm;
import tk.leooresende01.authstateless.infra.service.v1.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	private static final String API_CAMINHO = "/api/v1/usuarios/";

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> buscarListaDeUsuario() {
		List<UsuarioDto> usuariosDto = this.service.buscarTodosOsUsuarios();
		return ResponseEntity.ok(usuariosDto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody(required = true) UsuarioForm usuarioForm,
			UriComponentsBuilder fornecerEnderecoApi) throws URISyntaxException {
		UsuarioDto usuarioSalvo = this.service.salvarUsuarioNoDb(usuarioForm);
		String url = fornecerEnderecoApi.build().toUriString();
		return ResponseEntity.created(new URI(url + API_CAMINHO + usuarioSalvo.getUsername())).body(usuarioSalvo);
	}
	
	@PutMapping("/{username}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizarInformacoesUsuario(@RequestBody(required = true) UsuarioForm usuarioForm, @PathVariable String username) {
		UsuarioDto usuarioAtualizado = this.service.atualizarUsuario(usuarioForm, username);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@DeleteMapping("/{username}")
	@Transactional
	public ResponseEntity<Void> deletarUsuario(@PathVariable String username) {
		this.service.deletarUsuario(username);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UsuarioDto> buscarUsuarioPeloUsername(@PathVariable String username) {
		UsuarioDto usuarioDto = this.service.buscarUsuarioPeloUsername(username);
		return ResponseEntity.ok(usuarioDto);
	}
	
	@GetMapping("/{username}/produtos")
	public ResponseEntity<List<ProdutoDto>> buscarProdutosDoUsuario(@PathVariable String username) {
		List<ProdutoDto> produtosDto = this.service.buscarProdutosDoUsuario(username);
		return ResponseEntity.ok(produtosDto);
	}
	
	@GetMapping("/{username}/produtos/{idDoProduto}")
	public ResponseEntity<ProdutoDto> buscarProdutoDoUsuarioPeloId(@PathVariable String username, @PathVariable String idDoProduto) {
		ProdutoDto produtoDto = this.service.buscarProdutoDoUsuarioPeloId(username, idDoProduto);
		return ResponseEntity.ok(produtoDto);
	}
}
