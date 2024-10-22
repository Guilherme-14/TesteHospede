package com.example.veiculo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Veiculo;
import com.example.repository.VeiculoRepository;

@DataJpaTest
class VeiculoRepositoryTest {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Ferrari", "La Ferrari", "2023", "Vermelho");
		
		//When / Act
		Veiculo saveVeiculo = veiculoRepository.save(veiculo1);
		
		//Then / Assert
		assertNotNull(saveVeiculo);
		assertTrue(saveVeiculo.getId() > 0);
	}

	@DisplayName("Testando o get para todos os veiculos")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null,"Ferrari", "La Ferrari", "2023", "Vermelho");
		Veiculo veiculo2 = new Veiculo(null,"Ford", "Mustang", "2023", "Preto");
		
		veiculoRepository.save(veiculo1);
		veiculoRepository.save(veiculo2);
		
		//When / Act
		List<Veiculo> veiculoList = veiculoRepository.findAll();
		
		//Then / Assert
		assertNotNull(veiculoList); 
		assertEquals(2, veiculoList.size());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Ferrari", "La Ferrari", "2023", "Vermelho");
		
		veiculoRepository.save(veiculo1);
		
		//When / Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get(); 
		
		//Then / Assert
		assertNotNull(saveVeiculo);
		assertEquals(veiculo1.getId(), saveVeiculo.getId());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testUpdateRepository() {
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Ferrari", "La Ferrari", "2023", "Vermelho");
		veiculoRepository.save(veiculo1);
		
		//When / Act
		Veiculo saveVeiculo = veiculoRepository.findById(veiculo1.getId()).get(); 
		veiculo1.setMarca("Lamborghini");
		veiculo1.setModelo("Urus");
		veiculo1.setAno("2024");
		veiculo1.setCor("Roxo");
		
		
		Veiculo updateVeiculo = veiculoRepository.save(saveVeiculo);
		
		//Then / Assert
		assertNotNull(updateVeiculo);
		assertEquals("Lamborghini", updateVeiculo.getMarca()); 
		assertEquals("Urus", updateVeiculo.getModelo());
		assertEquals("2024", updateVeiculo.getAno());
		assertEquals("Roxo", updateVeiculo.getCor());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testDeleteRepository() {
		//Given / Arrange
		Veiculo veiculo1 = new Veiculo(null, "Ferrari", "La Ferrari", "2023", "Vermelho");
		
		veiculoRepository.save(veiculo1);
		
		//When / Act
		veiculoRepository.deleteById(veiculo1.getId());
		Optional<Veiculo> veiculoOptional = veiculoRepository.findById(veiculo1.getId()); 
		
		//Then / Assert
		assertTrue(veiculoOptional.isEmpty());
	}
}
