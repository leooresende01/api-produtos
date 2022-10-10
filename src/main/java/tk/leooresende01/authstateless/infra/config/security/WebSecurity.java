package tk.leooresende01.authstateless.infra.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurity {
	
	@Bean
	public SecurityFilterChain configurarEstrategiaDeAutenticacao(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
		http.csrf().disable();
		http.formLogin().disable();
		http.logout().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
}
