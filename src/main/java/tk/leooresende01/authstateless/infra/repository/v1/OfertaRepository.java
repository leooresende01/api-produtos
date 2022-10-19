package tk.leooresende01.authstateless.infra.repository.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.leooresende01.authstateless.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
	
}
