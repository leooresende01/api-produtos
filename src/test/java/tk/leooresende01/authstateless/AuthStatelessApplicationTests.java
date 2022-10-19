package tk.leooresende01.authstateless;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tk.leooresende01.authstateless.infra.controller.v1.dto.ProdutoDto;
import tk.leooresende01.authstateless.infra.repository.v1.ProdutoRepository;
import tk.leooresende01.authstateless.infra.service.v1.ProdutoService;
import tk.leooresende01.authstateless.model.Produto;

@SpringBootTest
class AuthStatelessApplicationTests {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Test
	void oNomeDoProdutoNoRepositorioDeveSerOMesmoDoDtoNoMesmoIndice() {
		List<ProdutoDto> listaDeProdutosDto = this.produtoService.pegarListaDeProdutos();
		List<Produto> listaDeProdutos = this.produtoRepo.findAll();

		Random random = new Random();
		int index = random.nextInt(0, listaDeProdutos.size() - 1);

		assertEquals(listaDeProdutos.get(0).getNome(), listaDeProdutosDto.get(index).getNome());
	}
}
