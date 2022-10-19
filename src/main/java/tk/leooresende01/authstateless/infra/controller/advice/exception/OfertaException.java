package tk.leooresende01.authstateless.infra.controller.advice.exception;

public class OfertaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OfertaException(String msg) {
		super(msg);
	}
	
	public OfertaException() {
		super();
	} 
}
