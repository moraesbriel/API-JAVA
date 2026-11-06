package SENAI.Ipiranga.com.blogPessoal.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // criando uma tabela
@Table(name="usuario") // nomeando a tabela
public class Usuario {
	
	@Id // criando a chave primária
	@GeneratedValue(strategy=GenerationType.IDENTITY) // preenchendo os próximos valores acrescidos com números inteiros depois de 1
	private long id;
	private String nome, usuario, foto, tipo, senha;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.REMOVE) // indicando cardinalidade 1:N e chave estrangeira "usuario"
	@JsonIgnoreProperties("usuario") // 
	private List<Postagem> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}