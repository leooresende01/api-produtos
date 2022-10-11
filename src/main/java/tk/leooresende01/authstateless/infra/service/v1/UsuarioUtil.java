package tk.leooresende01.authstateless.infra.service.v1;

import java.util.NoSuchElementException;
import java.util.Optional;

import tk.leooresende01.authstateless.infra.controller.advice.exception.ForbiddenException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.UsuarioJaExisteException;
import tk.leooresende01.authstateless.model.Role;
import tk.leooresende01.authstateless.model.Usuario;

public class UsuarioUtil {

	@SuppressWarnings("unlikely-arg-type")
	public static void verificaSeElePodeEditarEsseRecurso(String username, Usuario usuarioAutenticado) {
		if (username.equals(usuarioAutenticado.getUsername()) || usuarioAutenticado.getAuthorities().contains(Role.ROLE_ADMIN)) {
			return;
		}
		throw new ForbiddenException();
	}

	public static void verificaSeOUsuarioJaExiste(Optional<Usuario> optional) {
		try {
			optional.get();
			throw new UsuarioJaExisteException();
		} catch (NoSuchElementException ex) {
			
		}
	}

}
