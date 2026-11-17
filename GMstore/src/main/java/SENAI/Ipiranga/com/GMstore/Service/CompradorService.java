package SENAI.Ipiranga.com.GMstore.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import SENAI.Ipiranga.com.GMstore.Model.Comprador;
import SENAI.Ipiranga.com.GMstore.Model.UserLogin;
import SENAI.Ipiranga.com.GMstore.Repository.CompradorRepository;

@Service
public class CompradorService {
	
	private CompradorRepository repository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public CompradorService(CompradorRepository repository) {
		this.repository = repository;
	}
	
	public Comprador cadastrarComprador(Comprador comprador) {
		
		if (comprador == null) {
			throw new IllegalArgumentException("Os dados não foram informados.");
		}
		
		if (comprador.getSenha() == null || comprador.getSenha().isBlank()) {
			throw new IllegalArgumentException("A senha deve ser informada.");
		}
		
		String senhaEncoder = encoder.encode(comprador.getSenha());
		comprador.setSenha(senhaEncoder);
		return repository.save(comprador);
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
		
		Optional<Comprador> compradorEncontrado = repository.findByEmail(userLogin.getUsuarios());
		if (compradorEncontrado.isEmpty()) {
			return Optional.empty();
		}
		
		Comprador compradorBanco = compradorEncontrado.get();
		if (!encoder.matches(userLogin.getSenha(), compradorBanco.getSenha())) {
			return Optional.empty();
		}
		
		String auth = userLogin.getUsuarios() + ":" + userLogin.getSenha();
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		String authHeader = "Basic " + encodedAuth;
		userLogin.setToken(authHeader);
		userLogin.setId(compradorBanco.getCpf());
		userLogin.setNome(compradorBanco.getNome());
		return Optional.of(userLogin);
	}
}