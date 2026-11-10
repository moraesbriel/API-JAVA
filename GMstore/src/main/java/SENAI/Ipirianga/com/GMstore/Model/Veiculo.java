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
	private double preco;
	private int quantidade, ano;
	private String modelo, marca, cor, descricao;
	
	@ManyToOne
	@JsonIgnoreProperties("veiculo")
	private Comprador comprador;
	
	@ManyToOne
	@JsonIgnoreProperties("veiculo")
	private Frabricante fabricante;

	public long getChassi() {
		return chassi;
	}

	public void setChassi(long chassi) {
		this.chassi = chassi;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Frabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Frabricante fabricante) {
		this.fabricante = fabricante;
	}
}