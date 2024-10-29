package com.example.hospede.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Hospede;
import com.example.repository.HospedeRepository;
import com.example.service.HospedeService;

import jakarta.transaction.Transactional;

@SpringBootTest //Carrega o contexto completo do Spring para testes
@Transactional // Garante que as operações no banco de dados serão revertidas após cada teste 
class HospedeServiceTest {

	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@BeforeEach 
	void setUp() {
		hospedeRepository.deleteAll(); //Limpa o banco de dados antes de cada teste
	}
	
	@DisplayName("Testando salvar Hóspede")
	@Test
	void testSalvarHospede() {
		Hospede hospede = new Hospede (null, "Gui", "Gui@gmail.com", "(00)00000-0000");
		
		Hospede resultado = hospedeService.salvarHospede(hospede);
		
		assertNotNull(resultado);
		assertEquals("Gui", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}
	
	@DisplayName("Testando listar todos os Hóspedes")
	@Test
	void testListarTodosHospede() {
		Hospede hospede1 = new Hospede (null, "Gui", "Gui@gmail.com", "(00)00000-0000");
		Hospede hospede2 = new Hospede (null, "Mel", "Mel@gmail.com", "(11)11111-1111");
		
		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);
		
		List<Hospede> resultado = hospedeService.listarTodos();
		
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
	}
	
	@DisplayName("Testando Hóspedes pelo Id")
	@Test
	void testBuscarPorId() {
		Hospede hospede = new Hospede (null, "Gui", "Gui@gmail.com", "(00)00000-0000");
		
		Hospede salvo = hospedeService.salvarHospede(hospede);
		Optional<Hospede> resultado = hospedeService.buscarPorId(salvo.getId());
		
		assertTrue(resultado.isPresent());
		assertEquals("Gui" , resultado.get().getNome());
	}
	
	@DisplayName("Testando atualizar Hóspedes")
	@Test
	void testAtualizarHospede() {
		Hospede hospede = new Hospede (null, "Mel", "Mel@gmail.com", "(11)11111-1111");
		Hospede salvo = hospedeService.salvarHospede(hospede);
		
		salvo.setNome("Mel");
		salvo.setEmail("Mel@gmail.com");
		
		Hospede atualizado = hospedeService.atualizarHospede(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Mel", atualizado.getNome());
		assertEquals("Mel@gmail.com" , atualizado.getEmail());
	}
	
	@DisplayName("Testando deletar Hóspedes")
	@Test
	void testDeletarHospede() {
		Hospede hospede = new Hospede (null, "Gui", "Gui@gmail.com", "(00)00000-0000");
		
		Hospede salvo = hospedeService.salvarHospede(hospede);
		hospedeService.deletarHospede(salvo.getId());
		
		Optional<Hospede> resultado = hospedeService.buscarPorId(salvo.getId());

		assertTrue(resultado.isEmpty());
	}

}
