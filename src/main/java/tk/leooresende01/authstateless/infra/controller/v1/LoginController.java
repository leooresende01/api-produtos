package tk.leooresende01.authstateless.infra.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.leooresende01.authstateless.infra.controller.v1.dto.LoginForm;
import tk.leooresende01.authstateless.infra.controller.v1.dto.TokenDto;
import tk.leooresende01.authstateless.infra.service.v1.LoginService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
	@Autowired
	private LoginService service;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<TokenDto> getToken(@RequestBody(required = true) LoginForm loginForm) {
		Authentication authentication = this.service.verificaSeASenhaEhValidaEAutentica(loginForm, this.authManager);
		TokenDto tokenDto = this.service.gerarTokenApartirDaAutenticacao(authentication);
		return ResponseEntity.ok(tokenDto);
	}
}
