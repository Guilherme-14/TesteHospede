package com.example.cliente.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Cliente;
import com.example.repository.ClienteRepository;

@DataJpaTest
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@DisplayName("Testando o save")
	@Test
	void testSalvarRepository() {
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Guilherme", "(15)998021979" , "57698746367", "57175072-2");
		
		//When / Act
		Cliente saveCliente = clienteRepository.save(cliente1);
		
		//Then / Assert
		assertNotNull(saveCliente);
		assertTrue(saveCliente.getId() > 0);
	}

	@DisplayName("Testando o get para todos os clientes")
	@Test
	void testGetAllRepository() {
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Guilherme", "(15)998021979" , "57698746367", "57175072-2");
		Cliente cliente2 = new Cliente(null, "Maria","(15)998021979" , "57698746367", "57175072-2");
		
		clienteRepository.save(cliente1);
		clienteRepository.save(cliente2);
		
		//When / Act
		List<Cliente> clienteList = clienteRepository.findAll();
		
		//Then / Assert
		assertNotNull(clienteList); 
		assertEquals(2, clienteList.size());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testGetByIdRepository() {
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Guilherme", "(15)998021979" , "57698746367", "57175072-2");
		
		clienteRepository.save(cliente1);
		
		//When / Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get(); 
		
		//Then / Assert
		assertNotNull(saveCliente);
		assertEquals(cliente1.getId(), saveCliente.getId());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testUpdateRepository() {
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Guilherme","(15)998021979" , "57698746367", "57175072-2");
		clienteRepository.save(cliente1);
		
		//When / Act
		Cliente saveCliente = clienteRepository.findById(cliente1.getId()).get(); 
		cliente1.setNome("Leonardo");
		cliente1.setTelefone("R$ 20,00");
		cliente1.setCpf("57698746367");
		cliente1.setRg("57175072-2");
		
		Cliente updateCliente = clienteRepository.save(saveCliente);
		
		//Then / Assert
		assertNotNull(updateCliente);
		assertEquals("Leonardo", updateCliente.getNome()); 
		assertEquals("(15)998021979", updateCliente.getTelefone());
		assertEquals("57698746367", updateCliente.getCpf());
		assertEquals("57175072-2", updateCliente.getRg());
	}
	
	@DisplayName("Testando o Get By Id")
	@Test
	void testDeleteRepository() {
		//Given / Arrange
		Cliente cliente1 = new Cliente(null, "Guilherme", "(15)998021979" , "57698746367", "57175072-2");
		
		clienteRepository.save(cliente1);
		
		//When / Act
		clienteRepository.deleteById(cliente1.getId());
		Optional<Cliente> clienteOptional = clienteRepository.findById(cliente1.getId()); 
		
		//Then / Assert
		assertTrue(clienteOptional.isEmpty());
	}
}
