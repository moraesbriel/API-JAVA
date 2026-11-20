package SENAI.Ipiranga.com.blogPessoal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.Ipiranga.com.blogPessoal.Model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	public List<Tema> findByDescricaoContainingIgnoreCase(String descricao);
	public Optional<Tema> findById(long id);
}
