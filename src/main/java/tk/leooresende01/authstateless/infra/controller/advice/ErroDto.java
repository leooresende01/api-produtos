package tk.leooresende01.authstateless.infra.controller.advice;

public class ErroDto {
	private String mensagem;
	private Integer status;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ErroDto(String mensagem, Integer status) {
		this.mensagem = mensagem;
		this.status = status;
	}

	public ErroDto() {
	}

}
