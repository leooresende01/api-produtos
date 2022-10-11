package tk.leooresende01.authstateless.infra.controller.v1.dto;

import tk.leooresende01.authstateless.infra.util.LoginUtil;

public class TokenDto {
	private String token;
	private String authType;
	private Long expireTime;

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

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
		this.expireTime = LoginUtil.EXPIRE_TIME;
	}

	public TokenDto() {
	}

}
