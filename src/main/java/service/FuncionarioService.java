package service;

import java.util.List;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.Tributavel;
import repository.FuncionarioRepository;

public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("O repository é obrigatório.");
        }
        this.repository = repository;
    }

    public void cadastrar(Funcionario funcionario) throws FuncionarioJaCadastradoException {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não informado.");
        }
        if (repository.buscarPorCodigo(funcionario.getCodigo()) != null) {
            throw new FuncionarioJaCadastradoException(funcionario.getCodigo());
        }
        if (repository.buscarPorCpf(funcionario.getCpf()) != null) {
            throw new FuncionarioJaCadastradoException(
                    "Já existe um funcionário cadastrado com o CPF " + funcionario.getCpf() + ".");
        }
        repository.salvar(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public Funcionario buscarFuncionario(int codigo) throws FuncionarioNaoEncontradoException {
        Funcionario f = repository.buscarPorCodigo(codigo);
        if (f == null) {
            throw new FuncionarioNaoEncontradoException(codigo);
        }
        return f;
    }

    public Funcionario buscarFuncionario(String cpf) throws FuncionarioNaoEncontradoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para a busca.");
        }
        Funcionario f = repository.buscarPorCpf(cpf);
        if (f == null) {
            throw new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado.");
        }
        return f;
    }

    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata)
            throws FuncionarioNaoEncontradoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é obrigatório para a busca.");
        }
        List<Funcionario> encontrados = repository.buscarPorNome(nome, buscaExata);
        if (encontrados.isEmpty()) {
            throw new FuncionarioNaoEncontradoException("Nenhum funcionário encontrado com o nome " + nome + ".");
        }
        return encontrados;
    }

    public void removerFuncionario(int codigo) throws FuncionarioNaoEncontradoException {
        boolean removeu = repository.remover(codigo);
        if (!removeu) {
            throw new FuncionarioNaoEncontradoException(codigo);
        }
    }

    public double calcularRemuneracao(int codigo) throws FuncionarioNaoEncontradoException {
        Funcionario f = buscarFuncionario(codigo);
        return f.calcularRemuneracao();
    }

    public String exibirDados(int codigo) throws FuncionarioNaoEncontradoException {
        Funcionario f = buscarFuncionario(codigo);
        return f.exibirDados();
    }

    public double calcularTotalFolha() {
        double total = 0;
        for (Funcionario f : repository.listarTodos()) {
            total += f.calcularRemuneracao();
        }
        return total;
    }

    public String gerarRelatorio() {
        List<Funcionario> funcionarios = repository.listarTodos();
        if (funcionarios.isEmpty()) {
            return "Nenhum funcionário cadastrado.";
        }

        String relatorio = "===== RELATÓRIO DE FUNCIONÁRIOS =====\n";
        for (Funcionario f : funcionarios) {
            relatorio += f.getCodigo() + " | " + f.getNome() + " | " + f.getTipo()
                    + " | Remuneração: R$ " + String.format("%.2f", f.calcularRemuneracao());

            if (f instanceof Tributavel) {
                Tributavel t = (Tributavel) f;
                relatorio += " | Tributos: R$ " + String.format("%.2f", t.calcularTributos());
            }
            relatorio += "\n";
        }
        relatorio += "Total de funcionários: " + funcionarios.size() + "\n";
        relatorio += "Total da folha: R$ " + String.format("%.2f", calcularTotalFolha());
        return relatorio;
    }
}
