package SENAI.Ipirianga.com.GMstore.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="veiculo")
public class Veiculo {
	
	@Id
	private long chassi;
	private String modelo, marca, cor;
	
	@ManyToOne
	@JsonIgnoreProperties("veiculo")
	private Comprador comprador;
	
	@ManyToOne
	@JsonIgnoreProperties("veiculo")
	private Estoque estoque;

	public long getChassi() {
		return chassi;
	}

	public void setChassi(long chassi) {
		this.chassi = chassi;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
}