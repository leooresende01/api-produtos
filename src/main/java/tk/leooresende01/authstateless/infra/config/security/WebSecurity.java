package tk.leooresende01.authstateless.infra.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tk.leooresende01.authstateless.infra.config.filter.AuthenticationFilter;
import tk.leooresende01.authstateless.infra.service.v1.LoginService;

@Configuration
public class WebSecurity {
	@Autowired
	private LoginService loginService;
	@Bean
	public SecurityFilterChain configurarEstrategiaDeAutenticacao(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/usuarios").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf().disable();
		http.formLogin().disable();
		http.logout().disable();
		http.exceptionHandling()
	    	.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
		http.addFilterBefore(new AuthenticationFilter(loginService), UsernamePasswordAuthenticationFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager getAuthManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder getPassEncoder() {
		return new BCryptPasswordEncoder();
	}
}
