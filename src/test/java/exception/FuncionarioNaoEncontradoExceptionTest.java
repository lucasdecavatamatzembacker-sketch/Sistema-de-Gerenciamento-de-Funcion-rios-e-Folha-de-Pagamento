package exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioNaoEncontradoExceptionTest {

    @Test
    void deveGuardarAMensagem() {
        FuncionarioNaoEncontradoException e = new FuncionarioNaoEncontradoException("Não achei");
        assertEquals("Não achei", e.getMessage());
    }

    @Test
    void deveMontarMensagemComOCodigo() {
        FuncionarioNaoEncontradoException e = new FuncionarioNaoEncontradoException(99);
        assertEquals("Funcionário com código 99 não encontrado.", e.getMessage());
    }
}
