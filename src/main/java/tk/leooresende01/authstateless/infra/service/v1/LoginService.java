package tk.leooresende01.authstateless.infra.service.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.controller.v1.dto.LoginForm;
import tk.leooresende01.authstateless.infra.controller.v1.dto.TokenDto;
import tk.leooresende01.authstateless.infra.repository.v1.UsuarioRepository;
import tk.leooresende01.authstateless.infra.util.LoginUtil;
import tk.leooresende01.authstateless.model.Usuario;

@Service
public class LoginService {
	private static final String AUTH_TYPE = "Bearer";
	@Autowired
	private UsuarioRepository userRepo;

	public Authentication verificaSeASenhaEhValidaEAutentica(LoginForm loginForm, AuthenticationManager authManager) {
		return authManager.authenticate(loginForm.pegarComoAuthentication());
	}

	public TokenDto gerarTokenApartirDaAutenticacao(Authentication authentication) {
		String tokenJWT = LoginUtil.criarToken(authentication);
		return new TokenDto(tokenJWT, AUTH_TYPE);
	}

	public Usuario pegarDonoDoToken(String token) {
		String username = LoginUtil.verificaSeOTokenEhValido(token);
		try {
			return this.userRepo.findByUsername(username).get();			
		} catch (Exception ex) {
			return null;
		}
	}
}
