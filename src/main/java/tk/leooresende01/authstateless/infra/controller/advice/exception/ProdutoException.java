package tk.leooresende01.authstateless.infra.controller.advice.exception;

public class ProdutoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProdutoException() {
	}
	
	public ProdutoException(String msg) {
		super(msg);
	}
}
