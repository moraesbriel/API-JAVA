package SENAI.Ipiranga.com.blogPessoal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;

@Repository // criando um repositório
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
// tornando a classe UsuarioRepository filha de JpaRepository
	public List<Usuario> findByNomeContainingIgnoreCase(String nome);
	// findByNome (faz uma busca em um campo específico, nesse caso, no campo Nome)
	// Containing (filtra por caracteres)
	// IgnoreCase (ignora caracteres maiúsculos e minúsculos ao fazer a busca)
	public Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);
	// Optional (faz uma busca opcional, não precisa necessariamente encontrar algo)
	public Optional<Usuario> findByUsuario(String usuario);
}