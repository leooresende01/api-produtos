package tk.leooresende01.authstateless.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Roles implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String username;

	public Roles() {
	}

	public Roles(Role role, String username) {
		this.role = role;
		this.username = username;
	}

	@Override
	public String getAuthority() {
		return this.role.name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
