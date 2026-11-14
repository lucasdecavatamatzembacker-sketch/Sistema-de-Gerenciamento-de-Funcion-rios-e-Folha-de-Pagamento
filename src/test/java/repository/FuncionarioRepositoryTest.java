package repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioPJ;

class FuncionarioRepositoryTest {

    private FuncionarioRepository repository;
    private Funcionario ana;
    private Funcionario bruno;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
        ana = new FuncionarioCLT(1, "Ana Souza", "12345678909", 3000, 500);
        bruno = new FuncionarioPJ(2, "Bruno Lima", "98765432100", 2000, 3);
    }

    @Test
    void deveSalvarEListar() {
        repository.salvar(ana);
        repository.salvar(bruno);
        assertEquals(2, repository.quantidade());
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveBuscarPorCodigo() {
        repository.salvar(ana);
        repository.salvar(bruno);
        assertEquals(bruno, repository.buscarPorCodigo(2));
        assertNull(repository.buscarPorCodigo(50));
    }

    @Test
    void deveBuscarPorCpf() {
        repository.salvar(ana);
        assertEquals(ana, repository.buscarPorCpf("123.456.789-09"));
        assertNull(repository.buscarPorCpf("00011122233"));
    }

    @Test
    void deveBuscarPorNome() {
        repository.salvar(ana);
        repository.salvar(bruno);
        assertEquals(1, repository.buscarPorNome("ana souza", true).size());
        assertEquals(1, repository.buscarPorNome("Lima", false).size());
        assertEquals(0, repository.buscarPorNome("Ana", true).size());
    }

    @Test
    void deveRemover() {
        repository.salvar(ana);
        assertTrue(repository.remover(1));
        assertEquals(0, repository.quantidade());
    }

    @Test
    void removerInexistenteDeveRetornarFalse() {
        assertFalse(repository.remover(99));
    }
}
