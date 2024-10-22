package com.example.produto.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.entities.Produto;

class ProdutoTest {

	private Produto produto;
	
@BeforeEach
void setUp() {
	//Arrange
	produto = new Produto(1L, "Guilherme", "15,00");
}

	@Test
	@DisplayName("Testando Getter e Setter do Id")
	void testId() {
		produto.setId(2L);
		// Assert
		assertEquals(2L, produto.getId());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Nome")
	void testNome() {
		produto.setNome("Guilherme");
		// Assert
		assertEquals("Guilherme", produto.getNome());
	}

	@Test
	@DisplayName("Testando Getter e Setter do Preco")
	void testEmail() {
		produto.setPreco("R$ 20,00");
		// Assert
		assertEquals("R$ 20,00", produto.getPreco());
	}


	@Test
	@DisplayName("Testando todos os argumentos")
	void testConstrutor() {
		// Act
		Produto novoProduto = new Produto(3L, "Guilherme", "R$ 30,00");
		// Assert
		assertAll("novoProduto", () -> assertEquals(3L, novoProduto.getId()),
				() -> assertEquals("Guilherme", novoProduto.getNome()),
				() -> assertEquals("Guilherme@gmail.com", novoProduto.getPreco()));
	}
}
