package tk.leooresende01.authstateless.infra.service.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.controller.v1.dto.UsuarioDto;
import tk.leooresende01.authstateless.infra.controller.v1.dto.UsuarioForm;
import tk.leooresende01.authstateless.infra.repository.v1.UsuarioRepository;
import tk.leooresende01.authstateless.infra.util.UsuarioUtil;
import tk.leooresende01.authstateless.model.Produto;
import tk.leooresende01.authstateless.model.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private PasswordEncoder passEncoder;

	@Autowired
	private UsuarioRepository userRepo;

	public List<UsuarioDto> buscarTodosOsUsuarios() {
		List<Usuario> usuarios = this.userRepo.findAll();
		return UsuarioDto.mapearListaDeUsuariosParaDto(usuarios);
	}

	public UsuarioDto salvarUsuarioNoDb(UsuarioForm usuarioForm) {
		UsuarioUtil.validarFormularioDeUsuario(usuarioForm);
		Usuario usuario = usuarioForm.mapearParaUsuario();
		this.verificarUsername(usuarioForm);
		return this.hashearSenhaSalvarEPegarDTO(usuario);
	}

	public UsuarioDto atualizarUsuario(UsuarioForm usuarioForm, String username) {
		UsuarioUtil.validarFormularioDeUsuario(usuarioForm);
		this.verificarSeOUsuarioTemPemicoesDeEditar(username);
		Usuario usuario = this.buscarPeloUsernameNoDB(username);
		this.verificaSeOUsernameNaoMudou(usuarioForm);
		Usuario usuarioAtualizado = usuarioForm.atualizarUsuario(usuario);
		return this.hashearSenhaSalvarEPegarDTO(usuarioAtualizado);
	}

	public void deletarUsuario(String username) {
		this.verificarSeOUsuarioTemPemicoesDeEditar(username);
		this.userRepo.deleteByUsername(username);
	}

	public UsuarioDto buscarUsuarioPeloUsername(String username) {
		Usuario usuario = buscarPeloUsernameNoDB(username);
		return new UsuarioDto(usuario);
	}

	public List<ProdutoDto> buscarProdutosDoUsuario(String username) {
		Usuario usuario = buscarPeloUsernameNoDB(username);
		return ProdutoDto.mapearListaDeProdutoParaDto(usuario.getProdutos());
	}

	public ProdutoDto buscarProdutoDoUsuarioPeloId(String username, String idDoProduto) {
		Usuario usuario = buscarPeloUsernameNoDB(username);
		Produto produtoDoUsuario = usuario.getProdutos().stream()
				.filter(produto -> produto.getId() == Integer.valueOf(idDoProduto)).findFirst().get();
		return new ProdutoDto(produtoDoUsuario);
	}
	
	public UsuarioDto hashearSenhaSalvarEPegarDTO(Usuario usuario) {
		this.hashearSenha(usuario);
		return this.salvarUsuarioNoDBEPegarDTO(usuario);
	}
	
	public UsuarioDto salvarUsuarioNoDBEPegarDTO(Usuario usuario) {
		Usuario usuarioSalvo = this.userRepo.save(usuario);
		return new UsuarioDto(usuarioSalvo);
	}

	private Usuario buscarPeloUsernameNoDB(String username) {
		return this.userRepo.findByUsername(username).get();
	}

	private void hashearSenha(Usuario usuario) {
		usuario.setPassword(passEncoder.encode(usuario.getPassword()));
	}

	public Usuario pegarUsuarioAutenticado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private void verificarSeOUsuarioTemPemicoesDeEditar(String username) {
		Usuario usuarioAutenticado = this.pegarUsuarioAutenticado();
		UsuarioUtil.verificaSeElePodeEditarEsseRecurso(username, usuarioAutenticado);
	}

	private void verificarUsername(UsuarioForm usuarioForm) {
		Optional<Usuario> optional = this.userRepo.findByUsername(usuarioForm.getUsername());
		UsuarioUtil.verificaSeOUsernameJaEstaSendoUsado(optional);
	}

	private void verificaSeOUsernameNaoMudou(UsuarioForm usuarioForm) {
		Usuario usuarioAutenticado = this.pegarUsuarioAutenticado();
		if (!usuarioForm.getUsername().equals(usuarioAutenticado.getUsername()))
			this.verificarUsername(usuarioForm);
	}
}
