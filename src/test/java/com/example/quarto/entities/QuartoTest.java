package com.example.quarto.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.entities.Quarto;

class QuartoTest {

	private Quarto quarto;
	
	@BeforeEach
	void setUp() {
		//Arrange
		quarto = new Quarto(1L, "33", "Suíte");
	}

		@Test
		@DisplayName("Testando Getter e Setter do Id")
		void testId() {
			quarto.setId(2L);
			// Assert
			assertEquals(2L, quarto.getId());
		}

		@Test
		@DisplayName("Testando Getter e Setter do Num")
		void testNum() {
			quarto.setNum("33");
			// Assert
			assertEquals("33", quarto.getNum());
		}

		@Test
		@DisplayName("Testando Getter e Setter do Tipo")
		void testTipo() {
			quarto.setTipo("Suíte");
			// Assert
			assertEquals("Suíte", quarto.getTipo());
		}


		@Test
		@DisplayName("Testando todos os argumentos")
		void testConstrutor() {
			// Act
			Quarto novoQuarto = new Quarto(3L, "34", "Suíte");
			// Assert
			assertAll("novoQuarto", () -> assertEquals(3L, novoQuarto.getId()),
					() -> assertEquals("34", novoQuarto.getNum()),
					() -> assertEquals("Suíte", novoQuarto.getTipo()));
		}

}
