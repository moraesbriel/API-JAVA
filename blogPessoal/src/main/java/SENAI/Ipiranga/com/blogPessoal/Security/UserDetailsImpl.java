package SENAI.Ipiranga.com.blogPessoal.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public userDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
		this.authorities = new ArrayList<GrantedAuthority>();
		
		if (user.getTipo()!= null && !user.getTipo().isBlank()) {
			String role = user.getTipo().trim().toUpperCase();
			
			if (!role.startsWith("ROLE_")) {
				 role = "ROLE_" + role;
			}
			
			this.authorities.add(new SimpleGrantedAuthority(role));
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
