package tk.leooresende01.authstateless.infra.controller.v1.dto;

import tk.leooresende01.authstateless.model.StatusDaOferta;

public class OfertaForm {
	private StatusDaOferta status;

	public OfertaForm(StatusDaOferta status) {
		this.status = status;
	}

	public OfertaForm() {
	}

	public StatusDaOferta getStatus() {
		return status;
	}

	public void setStatus(StatusDaOferta status) {
		this.status = status;
	}

}
