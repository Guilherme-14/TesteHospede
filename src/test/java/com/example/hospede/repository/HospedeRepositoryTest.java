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
class HospedeRepositoryTest {

	@Autowired
	private HospedeRepository hospedeRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		//When / Act
		Hospede saveHospede = hospedeRepository.save(hospede1);
		
		//Then / Assert
		assertNotNull(saveHospede);
		assertTrue(saveHospede.getId() > 0);
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
		
		//Then / Assert
		assertNotNull(hospedeList);
		assertEquals(2, hospedeList.size());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1);
		
		//When / Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		
		//Then / Assert
		assertNotNull(saveHospede);
		assertEquals(hospede1.getId(), saveHospede.getId());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testUpdateRepository() {
		//Given / Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(15)999999999");
		
		hospedeRepository.save(hospede1);
		
		//When / Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		hospede1.setNome("Leonardo");
		hospede1.setEmail("leonardo@gmail.com");
		
		Hospede updateHospede = hospedeRepository.save(saveHospede);
		
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
		
		hospedeRepository.save(hospede1);
		
		//When / Act
		hospedeRepository.deleteById(hospede1.getId());
		
		Optional<Hospede> hospedeOptional = hospedeRepository.findById(hospede1.getId()); 
		
		//Then / Assert
		assertTrue(hospedeOptional.isEmpty());
	}
}
