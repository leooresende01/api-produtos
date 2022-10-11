package tk.leooresende01.authstateless.infra.controller.v1.dto;

import java.util.ArrayList;
import java.util.List;

import tk.leooresende01.authstateless.model.Role;
import tk.leooresende01.authstateless.model.Roles;
import tk.leooresende01.authstateless.model.Usuario;

public class UsuarioForm {
	private String username;
	private String password;
	private Integer idade;
	private static final Role ROLE_DEFAULT = Role.ROLE_USUARIO;

	public UsuarioForm(String username, String password, Integer idade) {
		this.username = username;
		this.idade = idade;
		this.password = password;
	}

	public UsuarioForm() {
	}

	public String getUsername() {
		return username;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usuario mapearParaUsuario() {
		Roles roleDefault = new Roles(UsuarioForm.ROLE_DEFAULT);
		List<Roles> listaDeRoles = new ArrayList<>(List.of(roleDefault));
		return new Usuario(this.username, this.password, this.idade, listaDeRoles);
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		usuario.setUsername(this.username);
		usuario.setPassword(this.password);
		usuario.setIdade(this.idade);
		return usuario;
	}

}
