package tk.leooresende01.authstateless.infra.controller.advice.exception;

public class UsuarioJaExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioJaExisteException() {
	}
	
	public UsuarioJaExisteException(String msg) {
		super(msg);
	}
}
