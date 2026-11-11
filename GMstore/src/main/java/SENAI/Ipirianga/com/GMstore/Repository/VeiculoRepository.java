package SENAI.Ipirianga.com.GMstore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.Ipirianga.com.GMstore.Model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
	public List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
	public List<Veiculo> findByMarcaContainingIgnoreCase(String marca);
	public List<Veiculo> findByCorContainingIgnoreCase(String cor);
	public List<Veiculo> findByPreco(double preco);
	public List<Veiculo> findByAno(int ano);
	public List<Veiculo> findById(long id);
}