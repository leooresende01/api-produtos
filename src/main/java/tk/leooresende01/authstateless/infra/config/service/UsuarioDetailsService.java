package tk.leooresende01.authstateless.infra.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tk.leooresende01.authstateless.infra.repository.v1.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepo.findByUsername(username).get();
	}

}
