package tk.leooresende01.authstateless.infra.controller.advice.exception;

public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ForbiddenException(String msg) {
		super(msg);
	}
	
	public ForbiddenException() {
	}
}
