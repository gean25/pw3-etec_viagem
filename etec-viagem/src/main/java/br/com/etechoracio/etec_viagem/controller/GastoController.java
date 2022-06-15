package br.com.etechoracio.etec_viagem.controller;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;
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

public class GastoController {

	@Autowired
	private GastoRepository repository;
	
	private List<Gasto> dados = new ArrayList<Gasto>();
	
	@GetMapping
	public List<Gasto> listarTodos(){
		dados = repository.findAll();
		return dados;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorId(@PathVariable Integer id) {
		
		Optional<Gasto> tipo = repository.findById(id);
		if(tipo.isPresent()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tipo.get());
	}
	
	@PostMapping
	public ResponseEntity<Gasto> inserir(@RequestBody Gasto gasto)
	{
		repository.save(gasto);
		return ResponseEntity.ok(gasto);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@PathVariable Integer id, @RequestBody Gasto gasto) 
	{
		boolean existe = repository.existsById(id);
		if(existe)
		{
			repository.save(gasto);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> atualizar(@PathVariable Integer id) 
	{
		 repository.deleteById(id);
		 return ResponseEntity.ok(id);
	}
	
}
