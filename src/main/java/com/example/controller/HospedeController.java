package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Hospede;
import com.example.service.HospedeService;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {
	private final HospedeService hospedeService;

	@Autowired
	public HospedeController (HospedeService hospedeService) {
		this.hospedeService = hospedeService;
	}
	
	@PostMapping
	public ResponseEntity<Hospede> salvarHospede(@RequestBody Hospede hospede){
		Hospede novoHospede = hospedeService.salvarHospede(hospede);
		return new ResponseEntity<>(novoHospede, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Hospede>> listarTodos(){
		List<Hospede> hospedes = hospedeService.listarTodos();
		return new ResponseEntity<>(hospedes,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> buscarPorId(@PathVariable Long id){
		Optional <Hospede> hospede = hospedeService.buscarPorId(id);
		return hospede.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hospede> atualizarHospede (@PathVariable Long id, @RequestBody Hospede hospede){
		if(!hospedeService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hospede.setId(id);
		Hospede hospedeAtualizado = hospedeService.atualizarHospede(hospede);
		return new ResponseEntity<>(hospedeAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaeHospede(@PathVariable Long id){
		if(!hospedeService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hospedeService.deletarHospede(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
