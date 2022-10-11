package tk.leooresende01.authstateless.infra.controller.advice.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UsernameOrPasswordInvalidException() {
		super();
	}
	
	public UsernameOrPasswordInvalidException(String msg) {
		super(msg);
	}
}
