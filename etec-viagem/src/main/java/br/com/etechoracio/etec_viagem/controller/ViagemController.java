package br.com.etechoracio.etec_viagem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;

public class ViagemController {

	@Autowired
	private ViagemRepository repository;
	
	private List<Viagem> dados = new ArrayList<Viagem>();
	
	@GetMapping
	public List<Viagem> listarTodos(){
		dados = repository.findAll();
		return dados;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Viagem> buscarPorId(@PathVariable Integer id) {
		
		Optional<Viagem> tipo = repository.findById(id);
		if(tipo.isPresent()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tipo.get());
	}
	
	@PostMapping
	public ResponseEntity<Viagem> inserir(@RequestBody Viagem viagem)
	{
		repository.save(viagem);
		return ResponseEntity.ok(viagem);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Integer id, @RequestBody Viagem viagem) 
	{
		boolean existe = repository.existsById(id);
		if(existe)
		{
			repository.save(viagem);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> atualizar(@PathVariable Integer id) 
	{
		 repository.deleteById(id);
		 return ResponseEntity.ok(id);
	}
}
