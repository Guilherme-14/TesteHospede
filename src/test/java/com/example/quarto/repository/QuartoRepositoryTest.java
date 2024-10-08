package com.example.quarto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Quarto;
import com.example.repository.QuartoRepository;

@DataJpaTest
class QuartoRepositoryTest {

	@Autowired
	private QuartoRepository quartoRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "33", "Suíte");
		//O id é null porque o banco gera automaticamente
		
		//When / Act
		Quarto saveQuarto = quartoRepository.save(quarto1);
		//salvou os valores do quarto1
		
		//Then / Assert
		assertNotNull(saveQuarto); //verifica se esse valor é nulo 
		assertTrue(saveQuarto.getId() > 0); //verifica se o id é maior que 0, pois se for maior que zero é porque o valor foi salvado
	}

	@DisplayName("Testando o get para todos os quartos")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "33", "Suíte");
		Quarto quarto2 = new Quarto(null, "34", "Luxo");
		
		quartoRepository.save(quarto1);
		quartoRepository.save(quarto2);
		
		//When / Act
		List<Quarto> quartoList = quartoRepository.findAll();
		//pega os valores e transforma numa lista
		
		//Then / Assert
		assertNotNull(quartoList); //se esse valor não foi nulo ele vai salvar em lista
		assertEquals(2, quartoList.size()); //se a quantidade de valores é 2 ele vai verificar se está certo
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "33", "Suíte");
		
		quartoRepository.save(quarto1);
		
		//When / Act
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get(); 
		//ele puxa o valor por id
		
		//Then / Assert
		assertNotNull(saveQuarto); //se esse valor não foi nulo ele vai salvar
		assertEquals(quarto1.getId(), saveQuarto.getId()); //verificando os valores se eles resistem
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testUpdateRepository() {
		//não existe update, ele apenas junta o find by id com o save
		
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "33", "Suíte");
		
		quartoRepository.save(quarto1);
		
		//When / Act
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get(); //chama pelo id
		quarto1.setNum("34"); //altera o valor
		quarto1.setTipo("Luxo"); //altera
		
		Quarto updateQuarto = quartoRepository.save(saveQuarto); //salva
		
		//Then / Assert
		assertNotNull(updateQuarto);
		assertEquals("34", updateQuarto.getNum()); 
		assertEquals("Luxo", updateQuarto.getTipo());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testDeleteRepository() {
		//Given / Arrange
		Quarto quarto1 = new Quarto(null, "33", "Suíte");
		
		quartoRepository.save(quarto1); //primeiro salva os valores
		
		//When / Act
		quartoRepository.deleteById(quarto1.getId()); //deleta por id 
		
		Optional<Quarto> quartoOptional = quartoRepository.findById(quarto1.getId()); 
		//quarto depois de deletado, verifica pelo optional se o valor ainda existe ou não
		
		//Then / Assert
		assertTrue(quartoOptional.isEmpty()); //se estiver vazia ele coloca certo senão, errado
	}


}
