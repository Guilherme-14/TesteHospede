package com.example.hospede.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entities.Hospede;
import com.example.repository.HospedeRepository;
import com.example.service.HospedeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HospedeControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private HospedeService hospedeService;

	@BeforeEach
	void SetUp() {
		hospedeRepository.deleteAll();
	}

	@Test
	@DisplayName("Teste de criação de Hóspede")
	void testCriarHospede() {
		Hospede hospede = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(00)00000-0000");

		ResponseEntity<Hospede> response = restTemplate.postForEntity("/api/hospedes", hospede, Hospede.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Guilherme", response.getBody().getNome());
	}

	@Test
	@DisplayName("Teste de listagem de todos os Hóspede")
	void testListarTodosHospede() {
		// Arrange
		Hospede hospede1 = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(00)00000-0000");
		Hospede hospede2 = new Hospede(null, "Mel", "mel@gmail.com", "(11)11111-1111");

		hospedeService.salvarHospede(hospede1);
		hospedeService.salvarHospede(hospede2);

		// Act
		ResponseEntity<Hospede[]> response = restTemplate.getForEntity("/api/hospedes", Hospede[].class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode(), "A resposta deveria ser 200 OK.");
		assertNotNull(response.getBody(), "O corpo da resposta não deveria ser nulo");
		assertEquals(2, response.getBody().length, "A quantidade de hóspedes retornada deveria ser 2.");
	}

	@Test
	@DisplayName("Teste de buscar Hóspede por ID")
	void testBuscarHospedePorId() {
		Hospede hospede = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(00)00000-0000");
		Hospede hospedeSalvo = hospedeRepository.save(hospede);
		ResponseEntity<Hospede> response = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(),
				Hospede.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Guilherme", response.getBody().getNome());
	}

	@Test
	@DisplayName("Teste de atualizar Hóspede por ID")
	void testAtualizarHospede() {
		Hospede hospedeSalvo = hospedeRepository
				.save(new Hospede(null, "Guilherme", "guilherme@gmail.com", "(00)00000-0000"));
		
		Hospede hospedeAtualizado = new Hospede(hospedeSalvo.getId(), "Guilherme Celestino", "guilhermeCelestino@gmail.com",
				"(00)00000-0000");

		HttpEntity<Hospede> requestUpdate = new HttpEntity<>(hospedeAtualizado);
		ResponseEntity<Hospede> response = restTemplate.exchange("/api/hospedes" + hospedeSalvo.getId(), HttpMethod.PUT,
				requestUpdate, Hospede.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Guilherme Celestino", response.getBody().getNome());
		assertEquals("guilhermeCelestino@gmail.com", response.getBody().getEmail());
	}

	@Test
	@DisplayName("Teste de deletar Hóspede")
	void testDeletarHospede() {
		// Arrange: Cria e salva um hospede
		Hospede hospede = new Hospede(null, "Guilherme", "guilherme@gmail.com", "(00)00000-0000");
		Hospede hospedeSalvo = hospedeService.salvarHospede(hospede);

		// Act: Tenta deletar o hospede
		ResponseEntity<Void> response = restTemplate.exchange("/api/hospedes/" + hospedeSalvo.getId(),
				HttpMethod.DELETE, null, Void.class);

		// Assert: Verifica se o Status da resposta é 204 No Content
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), "A resposta deveria ser 204 No Content");

		// Verifica se o hospede realmente foi deletado
		ResponseEntity<Hospede> checkDeleted = restTemplate.getForEntity("/api/hospedes/" + hospedeSalvo.getId(),
				Hospede.class);

		assertEquals(HttpStatus.NOT_FOUND, checkDeleted.getStatusCode(),
				"Após o DELETE o hóspede não deveria ser encontrado.");
	}
}
