package tk.leooresende01.authstateless.infra.controller.v1.dto;

import java.util.List;

import tk.leooresende01.authstateless.model.Usuario;

public class UsuarioDto {
	private String username;
	private Integer idade;
	private List<ProdutoDto> produtos;

	public UsuarioDto(Usuario usuario) {
		this.username = usuario.getUsername();
		this.idade = usuario.getIdade();
		this.produtos = ProdutoDto.mapearListaDeProdutoParaDto(usuario.getProdutos());
	}

	public UsuarioDto(String username, Integer idade, List<ProdutoDto> produtos) {
		this.username = username;
		this.idade = idade;
		this.produtos = produtos;
	}

	public UsuarioDto() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public List<ProdutoDto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoDto> produtos) {
		this.produtos = produtos;
	}

	public static List<UsuarioDto> mapearListaDeUsuariosParaDto(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).toList();
	}
}
