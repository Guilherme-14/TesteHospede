package com.example.veiculo.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.entities.Veiculo;

class VeiculoTest {

	private Veiculo veiculo;
	
@BeforeEach
void setUp() {
	//Arrange
	veiculo = new Veiculo(1L, "Lamborghini", "Urus", "2020" , "cor");
}

	@Test
	@DisplayName("Testando Getter e Setter do Id")
	void testId() {
		veiculo.setId(2L);
		// Assert
		assertEquals(2L, veiculo.getId());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Marca")
	void testMarca() {
		veiculo.setMarca("Ford");
		// Assert
		assertEquals("Ford", veiculo.getMarca());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Modelo")
	void testEmail() {
		veiculo.setModelo("Mustang");
		// Assert
		assertEquals("Mustang", veiculo.getModelo());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Ano")
	void testAno() {
		veiculo.setAno("2022");
		// Assert
		assertEquals("2022", veiculo.getAno());
	}
	
	@Test
	@DisplayName("Testando Getter e Setter do Cor")
	void testCor() {
		veiculo.setCor("Preto");
		// Assert
		assertEquals("Preto", veiculo.getCor());
	}

	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		// Act
		Veiculo novoVeiculo = new Veiculo(3L, "Ferrari", "La Ferrari", "2023" , "Vermelho");
		// Assert
		assertAll("novoVeiculo", () -> assertEquals(3L, novoVeiculo.getId()),
				() -> assertEquals("Ferrari", novoVeiculo.getMarca()),
				() -> assertEquals("La Ferrari", novoVeiculo.getModelo()),
				() -> assertEquals("2023", novoVeiculo.getAno()),
				() -> assertEquals("Vermelho", novoVeiculo.getCor()));
	}
}
