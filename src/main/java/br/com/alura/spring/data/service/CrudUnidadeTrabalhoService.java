package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.model.UnidadeTrabalho;
import br.com.alura.spring.data.respository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	public Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void iniciar(Scanner scanner) {
		while (system) {
			System.out.println("QUAL AÇÃO DE UNIDADE DESEJA REALIZAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - SALVAR");
			System.out.println("2 - ATUALIZAR");
			System.out.println("3 - VISUALIZAR");
			System.out.println("4 - DELETAR");

			int acao = scanner.nextInt();

			switch (acao) {

			case 1:
				salvar(scanner);
				break;

			case 2:
				atualizar(scanner);
				break;

			case 3:
				visualizar();
				break;

			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
	}

	public void salvar(Scanner scanner) {
		System.out.println("Informe a descricao");
		String descricao = scanner.next();
		System.out.println("Informe o endereço");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}

	public void atualizar(Scanner scanner) {
		System.out.println("INFORME O ID DO CARGO QUE VOCÊ DESEJA ATUALIZAR");
		Integer id = scanner.nextInt();
		
		System.out.println("Informe a descricao");
		String descricao = scanner.nextLine();
		
		System.out.println("Informe o endereço");
		String endereco = scanner.nextLine();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Unidade de trabalho atualizada");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidades));
	}

	private void deletar(Scanner scanner) {
		System.out.println("INFORME O ID DO CARGO QUE VOCÊ DESEJA DELETAR");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Arquivo deletado");
	}
}
