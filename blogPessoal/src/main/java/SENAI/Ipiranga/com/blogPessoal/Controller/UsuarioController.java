package SENAI.Ipiranga.com.blogPessoal.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que a classe é um Controller
@CrossOrigin(origins = "*", allowedHeaders = "*") // permite que aplicações em diferentes portas acessem esta classe
@RequestMapping("/usuario") // identifica qual Controller será direcionado
public class UsuarioController {
	
}
