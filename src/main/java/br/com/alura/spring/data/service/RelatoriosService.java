package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.model.Funcionario;
import br.com.alura.spring.data.model.FuncionarioProjecao;
import br.com.alura.spring.data.respository.FuncionarioRepository;
import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class RelatoriosService {

	public Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void iniciar(Scanner scanner) {
		while (system) {
			System.out.println("QUAL AÇÃO DE CARGO DESEJA REALIZAR?");
			System.out.println("0 - SAIR");
			System.out.println("1 - BUSCA FUNCIONARIO POR NOME");
			System.out.println("2 - BUSCA FUNCIONARIO POR NOME, DATA DE CONTRATAÇÃO E SALÁRIO MAIOR");
			System.out.println("3 - BUSCA FUNCIONARIO POR DATA DE CONTRATAÇÃO");
			System.out.println("4 - PESQUISA FUNCIONÁRIO POR SALÁRIO");

			int acao = scanner.nextInt();

			switch (acao) {

			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;	
			default:
				system = false;
				break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	};

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		System.out.println("Qual a data deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		System.out.println("Qual o salario deseja pesquisar?");
		Double salario = scanner.nextDouble();
		List<Funcionario> list = funcionarioRepository.findNomeDataContratacaoSalarioMaior(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() 
		+ " | nome: " + f.getNome() + " | salario: " + f.getSalario() ));
	};
}
