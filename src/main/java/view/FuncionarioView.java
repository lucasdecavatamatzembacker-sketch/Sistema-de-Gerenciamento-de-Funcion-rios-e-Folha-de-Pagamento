package view;

import java.util.List;
import java.util.Scanner;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioComissionado;
import model.FuncionarioPJ;
import service.FuncionarioService;

public class FuncionarioView {
    private FuncionarioService service;
    private Scanner scanner;

    public FuncionarioView(FuncionarioService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
                executarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido.");
            } catch (FuncionarioJaCadastradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (FuncionarioNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        System.out.println("Sistema encerrado.");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE FUNCIONÁRIOS =====");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Listar funcionários");
        System.out.println("3 - Buscar funcionário por código");
        System.out.println("4 - Buscar funcionário por CPF");
        System.out.println("5 - Buscar funcionário por nome");
        System.out.println("6 - Remover funcionário");
        System.out.println("7 - Exibir dados completos");
        System.out.println("8 - Calcular remuneração");
        System.out.println("9 - Gerar relatório");
        System.out.println("10 - Total da folha de pagamento");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void executarOpcao(int opcao) throws FuncionarioJaCadastradoException, FuncionarioNaoEncontradoException {
        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                System.out.println(service.buscarFuncionario(lerInt("Código: ")));
                break;
            case 4:
                System.out.println(service.buscarFuncionario(lerTexto("CPF: ")));
                break;
            case 5:
                buscarPorNome();
                break;
            case 6:
                int codigoRemover = lerInt("Código do funcionário a remover: ");
                service.removerFuncionario(codigoRemover);
                System.out.println("Funcionário removido com sucesso!");
                break;
            case 7:
                System.out.println(service.exibirDados(lerInt("Código: ")));
                break;
            case 8:
                double remuneracao = service.calcularRemuneracao(lerInt("Código: "));
                System.out.println("Remuneração: R$ " + String.format("%.2f", remuneracao));
                break;
            case 9:
                System.out.println(service.gerarRelatorio());
                break;
            case 10:
                System.out.println("Total da folha: R$ " + String.format("%.2f", service.calcularTotalFolha()));
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void cadastrar() throws FuncionarioJaCadastradoException {
        System.out.println("Tipo de funcionário: 1 - CLT | 2 - PJ | 3 - Comissionado");
        int tipo = lerInt("Tipo: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido!");
            return;
        }

        int codigo = lerInt("Código: ");
        String nome = lerTexto("Nome: ");
        String cpf = lerTexto("CPF: ");

        Funcionario funcionario;
        if (tipo == 1) {
            double salario = lerDouble("Salário-base: ");
            double beneficios = lerDouble("Benefícios: ");
            funcionario = new FuncionarioCLT(codigo, nome, cpf, salario, beneficios);
        } else if (tipo == 2) {
            double valorProjeto = lerDouble("Valor por projeto: ");
            int quantidade = lerInt("Quantidade de projetos: ");
            funcionario = new FuncionarioPJ(codigo, nome, cpf, valorProjeto, quantidade);
        } else {
            double salario = lerDouble("Salário-base: ");
            double percentual = lerDouble("Percentual de comissão (0 a 100): ");
            double vendas = lerDouble("Total de vendas: ");
            funcionario = new FuncionarioComissionado(codigo, nome, cpf, salario, percentual, vendas);
        }

        service.cadastrar(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listar() {
        List<Funcionario> lista = service.listarFuncionarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : lista) {
            System.out.println(f);
        }
    }

    private void buscarPorNome() throws FuncionarioNaoEncontradoException {
        String nome = lerTexto("Nome: ");
        String resposta = lerTexto("Busca exata? (s/n): ");
        boolean exata = resposta.equalsIgnoreCase("s");
        List<Funcionario> encontrados = service.buscarFuncionario(nome, exata);
        for (Funcionario f : encontrados) {
            System.out.println(f);
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private int lerInt(String mensagem) {
        return Integer.parseInt(lerTexto(mensagem).trim());
    }

    private double lerDouble(String mensagem) {
        return Double.parseDouble(lerTexto(mensagem).trim().replace(",", "."));
    }
}
