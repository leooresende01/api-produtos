package tk.leooresende01.authstateless.infra.util;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.context.SecurityContextHolder;

import tk.leooresende01.authstateless.infra.controller.advice.exception.ForbiddenException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.UsernameOrPasswordInvalidException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.UsuarioJaExisteException;
import tk.leooresende01.authstateless.infra.controller.v1.dto.UsuarioForm;
import tk.leooresende01.authstateless.model.Role;
import tk.leooresende01.authstateless.model.Usuario;

public class UsuarioUtil {

	public static void verificaSeElePodeEditarEsseRecurso(String username, Usuario usuarioAutenticado) {
		if (username.equals(usuarioAutenticado.getUsername())) {
			return;
		}
		UsuarioUtil.verificarSeOUsuarioTemARole(usuarioAutenticado, Role.ROLE_ADMIN);
	}

	public static void verificarSeOUsuarioTemARole(Usuario usuarioAutenticado, Role role) {
		try {
			usuarioAutenticado.getAuthorities().stream().filter(roles -> roles.getAuthority() == role.name())
					.findFirst().get();
			return;
		} catch (Exception ex) {
		}
		throw new ForbiddenException();

	}

	public static void verificaSeOUsernameJaEstaSendoUsado(Optional<Usuario> optional) {
		try {
			optional.get();
			throw new UsuarioJaExisteException("Esse nome de usuario já está sendo usado");
		} catch (NoSuchElementException ex) {

		}
	}

	public static void validarFormularioDeUsuario(UsuarioForm usuarioForm) {
		UsuarioUtil.verificaSeOUsuarioFormEhValido(usuarioForm);
		UsuarioUtil.verificaQuantidadeDeCaracteres(usuarioForm.getUsername().length());
		UsuarioUtil.verificaQuantidadeDeCaracteres(usuarioForm.getPassword().length());
	}

	@SuppressWarnings("deprecation")
	private static void verificaSeOUsuarioFormEhValido(UsuarioForm usuarioForm) {
		boolean ehNulo = usuarioForm.getUsername() == null || usuarioForm.getPassword() == null
				|| usuarioForm.getIdade() == null;
		if (ehNulo)
			throw new HttpMessageNotReadableException("Formulario invalido!");

		boolean ehBlank = usuarioForm.getUsername().isBlank() || usuarioForm.getPassword().toString().isBlank()
				|| usuarioForm.getIdade().toString().isBlank();
		if (ehBlank)
			throw new HttpMessageNotReadableException("Formulario invalido!");
	}

	private static void verificaQuantidadeDeCaracteres(Integer quantidade) {
		if (quantidade > 20 || quantidade < 8) {
			throw new UsernameOrPasswordInvalidException(
					"O usuario e senha devem ter no minimo 8 caracteres e no maximo 20");
		}
	}
	
	public static void verificarSeOUsuarioTemPemicoesDeEditar(String username) {
		Usuario usuarioAutenticado = UsuarioUtil.pegarUsuarioAutenticado();
		UsuarioUtil.verificaSeElePodeEditarEsseRecurso(username, usuarioAutenticado);
	}
	
	public static Usuario pegarUsuarioAutenticado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
