package SENAI.Ipiranga.com.blogPessoal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SENAI.Ipiranga.com.blogPessoal.Model.UserLogin;
import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;
import SENAI.Ipiranga.com.blogPessoal.Repository.UsuarioRepository;
import SENAI.Ipiranga.com.blogPessoal.Service.UsuarioService;

@RestController // indica que a classe é um Controller
@CrossOrigin(origins = "*", allowedHeaders = "*") // permite que aplicações em diferentes portas acessem esta classe
@RequestMapping("/usuario") // identifica qual Controller será direcionado ao FrontEnd
public class UsuarioController {
	
	@Autowired // injeção de dependência: permite acessar um objeto de outra classe
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping // indica que uma busca será feita no banco de dados
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") // indica uma busca específica
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
		return repository.findById(id).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(usuario));
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin>user) {
		return service.Logar(user).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}