package tk.leooresende01.authstateless.infra.util;

import java.util.Date;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tk.leooresende01.authstateless.model.Usuario;

public class LoginUtil {
	private static final String TOKEN_KEY = "IOEHOIOH*()(E((E((E(EU(E(U@";
	public static final Long EXPIRE_TIME = 3600000l;

	public static String criarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date data = new Date();
		return Jwts.builder().setIssuer("Produtos").setSubject(usuario.getUsername())
				.setIssuedAt(new Date(data.getTime())).setExpiration(new Date(data.getTime() + EXPIRE_TIME))
				.signWith(SignatureAlgorithm.HS256, TOKEN_KEY).compact();
	}

	public static String verificaSeOTokenFoiEnviadoEPegaEle(String authBearer) {
		if (authBearer != null && authBearer.contains("Bearer")) {
			return authBearer.split(" ")[1];
		}
		if (authBearer == null) {
			return null;
		}
		throw new IllegalArgumentException();
	}

	public static String verificaSeOTokenEhValido(String token) {
		try {
			return Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception ex) {
			return null;
		}
	}

}
