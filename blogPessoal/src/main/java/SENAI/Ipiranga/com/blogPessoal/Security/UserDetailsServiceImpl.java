package SENAI.Ipiranga.com.blogPessoal.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;
import SENAI.Ipiranga.com.blogPessoal.Repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsuarioRepository userRepository;
	
	public UserDetailsServiceImpl(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = userRepository.findByUsuario(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado."));
		
		return new UserDetailsImpl(user);
	}
}