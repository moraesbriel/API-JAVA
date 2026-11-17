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
		
		if (usuario==null)
		{
			throw new IllegalArgumentException("Os dados não foram informados");
		}
		
		if(usuario.getSenha() ==null || usuario.getSenha().isBlank()) {
			throw new IllegalArgumentException("A senha deve ser informada");
		}
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return repository.save(usuario);
	
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		
		if(user==null || user.isEmpty() ) {
			return Optional.empty();
		}
		UserLogin usuarioLogin = user.get();
		if(usuarioLogin.getUsuarios()==null||
				usuarioLogin.getUsuarios().isBlank()||
				usuarioLogin.getSenha()==null||
				usuarioLogin.getSenha().isBlank()) {
			return Optional.empty();
		}
		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(usuarioLogin.getUsuarios());
		if(usuarioEncontrado.isEmpty()) {
			return Optional.empty();
			
		}
		Usuario usuarioBanco = usuarioEncontrado.get();
		if(!encoder.matches(usuarioLogin.getSenha(), usuarioBanco.getSenha())) {
			return Optional.empty();
		}
			String auth = usuarioLogin.getUsuarios()+":"+ usuarioLogin.getSenha();
			String encodedAuth = Base64.getEncoder()
					.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
			String authHeader = "Basic "+ encodedAuth;
			usuarioLogin.setToken(authHeader);
			usuarioLogin.setId(usuarioBanco.getId());
			usuarioLogin.setNome(usuarioBanco.getNome());
			usuarioLogin.setFoto(usuarioBanco.getFoto());
			usuarioLogin.setTipo(usuarioBanco.getTipo());
			
			return Optional.of(usuarioLogin);
			
		}
	}