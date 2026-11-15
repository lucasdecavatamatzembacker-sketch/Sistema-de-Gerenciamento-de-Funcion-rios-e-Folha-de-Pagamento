package exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioJaCadastradoExceptionTest {

    @Test
    void deveGuardarAMensagem() {
        FuncionarioJaCadastradoException e = new FuncionarioJaCadastradoException("Já existe");
        assertEquals("Já existe", e.getMessage());
    }

    @Test
    void deveMontarMensagemComOCodigo() {
        FuncionarioJaCadastradoException e = new FuncionarioJaCadastradoException(7);
        assertEquals("Já existe um funcionário cadastrado com o código 7.", e.getMessage());
    }
}
