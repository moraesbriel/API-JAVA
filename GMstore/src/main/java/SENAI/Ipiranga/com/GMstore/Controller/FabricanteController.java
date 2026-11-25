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

import SENAI.Ipiranga.com.GMstore.Model.Fabricante;
import SENAI.Ipiranga.com.GMstore.Repository.FabricanteRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/fabricante")
public class FabricanteController {
	
	@Autowired
	private FabricanteRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Fabricante>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Fabricante>> getAllByNomeCOntainingIgnoreCase(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Fabricante>> getById(@PathVariable long id) {
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Fabricante> post(@RequestBody Fabricante fabricante) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(fabricante));
	}
	
	@PutMapping
	public ResponseEntity<Fabricante> put(@RequestBody Fabricante fabricante) {
		return ResponseEntity.ok(repository.save(fabricante));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}