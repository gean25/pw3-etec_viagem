package br.com.etechoracio.etec_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.etechoracio.etec_viagem.entity.Gasto;
import br.com.etechoracio.etec_viagem.entity.Viagem;
import br.com.etechoracio.etec_viagem.repository.GastoRepository;
import br.com.etechoracio.etec_viagem.repository.ViagemRepository;

public class GastoService {
	
	@Autowired
	private GastoRepository gastorepository;
	
	@Autowired
	private ViagemRepository viagemrepository;
	
	public List<Gasto> listarTodos() {
		return gastorepository.findAll();
	} 
	
	public Optional<Gasto> buscarPorId(Long id){
		return gastorepository.findById(id);
	}
	
	public Gasto inserir(Gasto obj) {
		Optional<Viagem> existe = viagemrepository.findById(obj.getViagem().getId());
		if(!existe.isPresent()) {
			throw new RuntimeException("Viagem não encontrada.");
		}
		 if(existe.get().getChegada().isAfter(obj.getData()) || existe.get().getSaida().isBefore(obj.getData())) 
		 {
			throw new RuntimeException("Data Inválida");
		 }   
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
