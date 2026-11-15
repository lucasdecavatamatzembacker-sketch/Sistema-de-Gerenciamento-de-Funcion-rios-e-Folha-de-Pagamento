package repository;

import java.util.ArrayList;
import java.util.List;

import model.Funcionario;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void salvar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.");
        }
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    public Funcionario buscarPorCodigo(int codigo) {
        for (Funcionario f : funcionarios) {
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    public Funcionario buscarPorCpf(String cpf) {
        String cpfLimpo = Funcionario.limparCpf(cpf);
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpfLimpo)) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> buscarPorNome(String nome, boolean buscaExata) {
        List<Funcionario> encontrados = new ArrayList<>();
        if (nome == null) {
            return encontrados;
        }
        String procurado = nome.trim().toLowerCase();
        for (Funcionario f : funcionarios) {
            String nomeFuncionario = f.getNome().toLowerCase();
            if (buscaExata && nomeFuncionario.equals(procurado)) {
                encontrados.add(f);
            } else if (!buscaExata && nomeFuncionario.contains(procurado)) {
                encontrados.add(f);
            }
        }
        return encontrados;
    }

    public boolean remover(int codigo) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCodigo() == codigo) {
                funcionarios.remove(i);
                return true;
            }
        }
        return false;
    }

    public int quantidade() {
        return funcionarios.size();
    }
}
