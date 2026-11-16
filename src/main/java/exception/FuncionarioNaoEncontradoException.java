package exception;

public class FuncionarioNaoEncontradoException extends Exception {
    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FuncionarioNaoEncontradoException(int codigo) {
        super("Funcionário com código " + codigo + " não encontrado.");
    }
}
