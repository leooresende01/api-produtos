package tk.leooresende01.authstateless.infra.controller.v1.dto;

public class TokenDto {
	private String token;
	private String authType;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public TokenDto(String token, String authType) {
		this.token = token;
		this.authType = authType;
	}

	public TokenDto() {
	}

}
