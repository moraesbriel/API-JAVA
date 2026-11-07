package SENAI.Ipirianga.com.GMstore.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="comprador")
public class Comprador {
	
	@Id
	private long cpf;
	private String nome, telefone, email, senha;
	
	@OneToMany(mappedBy="comprador", cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("comprador")
	private List<Veiculo> veiculo;

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Veiculos> getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(List<Veiculos> veiculo) {
		this.veiculo = veiculo;
	}
}	