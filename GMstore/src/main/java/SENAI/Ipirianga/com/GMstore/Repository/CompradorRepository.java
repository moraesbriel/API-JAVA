package SENAI.Ipirianga.com.GMstore.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.Ipirianga.com.GMstore.Model.Comprador;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {
	
	public List<Comprador> findByNomeContainingIgnoreCase(String nome);
	public Optional<Comprador> findByCPFAndEmailAndSenha(long cpf, String email, String senha);
	public Optional<Comprador> findByCPFAndEmail(long cpf, String email);
}