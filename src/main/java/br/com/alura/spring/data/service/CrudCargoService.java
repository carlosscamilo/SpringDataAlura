package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.model.Cargo;
import br.com.alura.spring.data.respository.CargoRepository;

@Service
public class CrudCargoService {
	
	public Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void iniciar(Scanner scanner) {
		while(system) {
			System.out.println("QUAL AÇÃO DE CARGO DESEJA REALIZAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - SALVAR");
			System.out.println("2 - ATUALIZAR");
			System.out.println("3 - VISUALIZAR");
			System.out.println("4 - DELETAR");
			
			int acao = scanner.nextInt();
			
			switch (acao) {
			
			case 1: salvar(scanner);		
					break;
			
			case 2: atualizar(scanner);
					break;
			
			case 3: visualizar();
			break;
			
			case 4: deletar(scanner);
			break;
			
			default: 
					system = false;
					break;
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		System.out.println("Qual é a descricao do cargo?");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}
	
	public void atualizar(Scanner scanner) {
		System.out.println("INFORME O ID DO CARGO QUE VOCÊ DESEJA ATUALIZAR");
		int id = scanner.nextInt();
		System.out.println("INFORME A DESCRIÇÃO NOVA");
		String decricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(decricao);
		cargoRepository.save(cargo);
		System.out.println("Arquivo atualizado");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID DO CARGO QUE VOCÊ DESEJA DELETAR");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Arquivo deletado");
	}
}
