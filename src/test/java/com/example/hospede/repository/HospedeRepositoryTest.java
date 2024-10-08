package com.example.hospede.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Hospede;
import com.example.repository.HospedeRepository;

@DataJpaTest
//Esse é o comando que permite testar os dados do JPA da tabela feita no entities
class HospedeRepositoryTest {

	@Autowired
	private HospedeRepository hospedeRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		//O id é null porque o banco gera automaticamente
		
		//When / Act
		Hospede saveHospede = hospedeRepository.save(hospede1);
		//salvou os valores do hospede1
		
		//Then / Assert
		assertNotNull(saveHospede); //verifica se esse valor é nulo 
		assertTrue(saveHospede.getId() > 0); //verifica se o id é maior que 0, pois se for maior que zero é porque o valor foi salvado
	}

	@DisplayName("Testando o get para todos os hospedes")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		Hospede hospede2 = new Hospede(null, "Maria", "maria@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1);
		hospedeRepository.save(hospede2);
		
		//When / Act
		List<Hospede> hospedeList = hospedeRepository.findAll();
		//pega os valores e transforma numa lista
		
		//Then / Assert
		assertNotNull(hospedeList); //se esse valor não foi nulo ele vai salvar em lista
		assertEquals(2, hospedeList.size()); //se a quantidade de valores é 2 ele vai verificar se está certo
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1);
		
		//When / Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get(); 
		//ele puxa o valor por id
		
		//Then / Assert
		assertNotNull(saveHospede); //se esse valor não foi nulo ele vai salvar
		assertEquals(hospede1.getId(), saveHospede.getId()); //verificando os valores se eles resistem
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testUpdateRepository() {
		//não existe update, ele apenas junta o find by id com o save
		
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1);
		
		//When / Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get(); //chama pelo id
		hospede1.setNome("Leonardo"); //altera o valor
		hospede1.setEmail("leonardo@gmail.com"); //altera
		
		Hospede updateHospede = hospedeRepository.save(saveHospede); //salva
		
		//Then / Assert
		assertNotNull(updateHospede);
		assertEquals("Leonardo", updateHospede.getNome()); 
		assertEquals("leonardo@gmail.com", updateHospede.getEmail());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testDeleteRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1); //primeiro salva os valores
		
		//When / Act
		hospedeRepository.deleteById(hospede1.getId()); //deleta por id 
		
		Optional<Hospede> hospedeOptional = hospedeRepository.findById(hospede1.getId()); 
		//hospede depois de deletado, verifica pelo optional se o valor ainda existe ou não
		
		//Then / Assert
		assertTrue(hospedeOptional.isEmpty()); //se estiver vazia ele coloca certo senão, errado
	}
}
