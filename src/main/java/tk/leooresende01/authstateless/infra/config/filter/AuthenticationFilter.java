package tk.leooresende01.authstateless.infra.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import tk.leooresende01.authstateless.infra.service.v1.LoginService;
import tk.leooresende01.authstateless.infra.util.LoginUtil;
import tk.leooresende01.authstateless.model.Usuario;

public class AuthenticationFilter extends OncePerRequestFilter {
	private static final String AUTHORIZATION = "Authorization";

	private final LoginService service;

	public AuthenticationFilter(LoginService service) {
		this.service = service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authBearer = request.getHeader(AUTHORIZATION);
		String token = LoginUtil.verificaSeOTokenFoiEnviadoEPegaEle(authBearer);
		Usuario usuario = this.service.pegarDonoDoToken(token);
		this.autenticaOUsuario(usuario);
		filterChain.doFilter(request, response);
	}

	private void autenticaOUsuario(Usuario usuario) {
		if (usuario != null) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(usuario,
					usuario.getPassword(), usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}
}
