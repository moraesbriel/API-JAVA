package SENAI.Ipiranga.com.GMstore.Controller;

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

import SENAI.Ipiranga.com.GMstore.Model.Comprador;
import SENAI.Ipiranga.com.GMstore.Model.UserLogin;
import SENAI.Ipiranga.com.GMstore.Repository.CompradorRepository;
import SENAI.Ipiranga.com.GMstore.Service.CompradorService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comprador")
public class CompradorController {
	
	@Autowired
	private CompradorRepository repository;
	
	@Autowired
	private CompradorService service;
	
	@GetMapping
	public ResponseEntity<List<Comprador>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Comprador> getById(@PathVariable long cpf) {
		return repository.findById(cpf).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/cadastar")
	public ResponseEntity<Comprador> post(@RequestBody Comprador comprador) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarComprador(comprador));
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autentication(@RequestBody Optional<UserLogin>user) {
		return service.Logar(user).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping
	public ResponseEntity<Comprador> put(@RequestBody Comprador comprador) {
		return ResponseEntity.ok(repository.save(comprador));
	}
	
	@DeleteMapping("/{cpf}")
	public void delete(@PathVariable long cpf) {
		repository.deleteById(cpf);
	}
}