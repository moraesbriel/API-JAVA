package SENAI.Ipiranga.com.blogPessoal.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;
import SENAI.Ipiranga.com.blogPessoal.Model.UserLogin;
import SENAI.Ipiranga.com.blogPessoal.Repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository repository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		
		if (usuario == null) {
			throw new IllegalArgumentException("Os dados não foram informados");
		}
		
		if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
			throw new IllegalArgumentException("A senha deve ser informada");
		}
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return repository.save(usuario);
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		
		if (user == null || user.isEmpty()) {
			return Optional.empty();
		}
		
		UserLogin userLogin = user.get();
		if (userLogin.getUsuarios() == null ||
			userLogin.getUsuarios().isBlank() ||
			userLogin.getSenha() == null ||
			userLogin.getSenha().isBlank()) {
			return Optional.empty();
		}
		
		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(userLogin.getUsuarios());
		if (usuarioEncontrado.isEmpty()) {
			return Optional.empty();
		}
		
		Usuario usuarioBanco = usuarioEncontrado.get();
		if (!encoder.matches(userLogin.getSenha(), usuarioBanco.getSenha())) {
			return Optional.empty();
		}
			
		String auth = userLogin.getUsuarios()+":"+ userLogin.getSenha();
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		String authHeader = "Basic "+ encodedAuth;
		userLogin.setToken(authHeader);
		userLogin.setId(usuarioBanco.getId());
		userLogin.setNome(usuarioBanco.getNome());
		userLogin.setFoto(usuarioBanco.getFoto());
		userLogin.setTipo(usuarioBanco.getTipo());
		return Optional.of(userLogin);
	}
}