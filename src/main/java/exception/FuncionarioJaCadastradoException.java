package exception;

public class FuncionarioJaCadastradoException extends Exception {
    public FuncionarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }

    public FuncionarioJaCadastradoException(int codigo) {
        super("Já existe um funcionário cadastrado com o código " + codigo + ".");
    }
}
