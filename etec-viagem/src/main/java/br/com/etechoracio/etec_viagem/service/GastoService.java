package br.com.etechoracio.etec_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;

public class GastoService {
	
	@Autowired
	private GastoRepository gastorepository;
	
	public List<Gasto> listarTodos() {
		return gastorepository.findAll();
	} 
	
	public Optional<Gasto> buscarPorId(Long id){
		return gastorepository.findById(id);
	}
	
	public Gasto inserir(Gasto obj) {
		return gastorepository.save(obj);
	}
	
	public Optional<Gasto> atualizar(Long id, Gasto gasto){
		boolean existe = gastorepository.existsById(id);
		if(!existe) {
			return Optional.empty();
		}
		
		return Optional.of(gastorepository.save(gasto));
	}
}
