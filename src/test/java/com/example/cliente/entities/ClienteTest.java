package com.example.cliente.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.entities.Cliente;

class ClienteTest {

	private Cliente cliente;
	
@BeforeEach
void setUp() {
	//Arrange
	cliente = new Cliente(1L, "Guilherme", "(15)99988-8557", "462989880843", "57175072-2");
}

	@Test
	@DisplayName("Testando Getter e Setter do Id")
	void testId() {
		cliente.setId(2L);
		// Assert
		assertEquals(2L, cliente.getId());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Nome")
	void testNome() {
		cliente.setNome("Guilherme");
		// Assert
		assertEquals("Guilherme", cliente.getNome());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Telefone")
	void testTelefone() {
		cliente.setTelefone("(15)99988-8557");
		// Assert
		assertEquals("(15)99988-8557", cliente.getTelefone());
	}
	
	@Test
	@DisplayName("Testando Getter e Setter do Cpf")
	void testCpf() {
		cliente.setCpf("57448485945");
		// Assert
		assertEquals("57448485945", cliente.getCpf());
	}
	
	@Test
	@DisplayName("Testando Getter e Setter do Rg")
	void testRg() {
		cliente.setRg("57175072-2");
		// Assert
		assertEquals("57175072-2", cliente.getRg());
	}	

	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		// Act
		Cliente novoCliente = new Cliente(3L, "Guilherme", "(15)99988-8557", "75896968523", "57175072-2");
		// Assert
		assertAll("novoCliente", () -> assertEquals(3L, novoCliente.getId()),
				() -> assertEquals("Guilherme", novoCliente.getNome()),
				() -> assertEquals("(15)99988-8557", novoCliente.getTelefone()),
				() -> assertEquals("75896968523", novoCliente.getCpf()),
				() -> assertEquals("57175072-2", novoCliente.getRg()));
	}
}
