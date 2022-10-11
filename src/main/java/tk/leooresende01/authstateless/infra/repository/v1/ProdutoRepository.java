package tk.leooresende01.authstateless.infra.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.leooresende01.authstateless.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
