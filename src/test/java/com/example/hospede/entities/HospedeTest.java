package com.example.hospede.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.entities.Hospede;

class HospedeTest {

	private Hospede hospede;
	
@BeforeEach
void setUp() {
	//Arrange
	hospede = new Hospede(1L, "Guilherme", "Guilherme@gmail.com", "(15)99988-8557");
}

	@Test
	@DisplayName("Testando Getter e Setter do Id")
	void testId() {
		hospede.setId(2L);
		// Assert
		assertEquals(2L, hospede.getId());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Nome")
	void testNome() {
		hospede.setNome("Guilherme");
		// Assert
		assertEquals("Guilherme", hospede.getNome());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Email")
	void testEmail() {
		hospede.setEmail("Guilherme@gmail.com");
		// Assert
		assertEquals("Guilherme@gmail.com", hospede.getEmail());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Telefone")
	void testTelefone() {
		hospede.setTelefone("(15)99988-8557");
		// Assert
		assertEquals("(15)99988-8557", hospede.getTelefone());
	}

	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		// Act
		Hospede novoHospede = new Hospede(3L, "Guilherme", "Guilherme@gmail.com", "(15)99988-8557");
		// Assert
		assertAll("novoHospede", () -> assertEquals(3L, novoHospede.getId()),
				() -> assertEquals("Guilherme", novoHospede.getNome()),
				() -> assertEquals("Guilherme@gmail.com", novoHospede.getEmail()),
				() -> assertEquals("(15)99988-8557", novoHospede.getTelefone()));
	}
}
