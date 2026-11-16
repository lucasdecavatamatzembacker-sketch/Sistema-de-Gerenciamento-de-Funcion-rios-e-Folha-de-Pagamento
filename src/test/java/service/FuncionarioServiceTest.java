package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.FuncionarioJaCadastradoException;
import exception.FuncionarioNaoEncontradoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioComissionado;
import model.FuncionarioPJ;
import repository.FuncionarioRepository;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private Funcionario ana;
    private Funcionario bruno;
    private Funcionario carla;

    @BeforeEach
    void setUp() throws Exception {
        service = new FuncionarioService(new FuncionarioRepository());
        ana = new FuncionarioCLT(1, "Ana Souza", "12345678909", 3000, 500);
        bruno = new FuncionarioPJ(2, "Bruno Lima", "98765432100", 2000, 3);
        carla = new FuncionarioComissionado(3, "Carla Souza", "11122233344", 2000, 10, 10000);
        service.cadastrar(ana);
        service.cadastrar(bruno);
        service.cadastrar(carla);
    }

    @Test
    void deveCadastrarEListar() {
        assertEquals(3, service.listarFuncionarios().size());
    }

    @Test
    void naoDeveCadastrarCodigoRepetido() {
        Funcionario outro = new FuncionarioPJ(1, "Outro", "55566677788", 100, 1);
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(outro));
    }

    @Test
    void naoDeveCadastrarCpfRepetido() {
        Funcionario outro = new FuncionarioPJ(10, "Outro", "12345678909", 100, 1);
        assertThrows(FuncionarioJaCadastradoException.class, () -> service.cadastrar(outro));
    }

    @Test
    void deveBuscarPorCodigo() throws Exception {
        assertEquals(bruno, service.buscarFuncionario(2));
    }

    @Test
    void deveBuscarPorCpf() throws Exception {
        assertEquals(carla, service.buscarFuncionario("111.222.333-44"));
    }

    @Test
    void deveBuscarPorNome() throws Exception {
        List<Funcionario> exata = service.buscarFuncionario("Bruno Lima", true);
        List<Funcionario> parcial = service.buscarFuncionario("souza", false);
        assertEquals(1, exata.size());
        assertEquals(2, parcial.size());
    }

    @Test
    void buscarInexistenteDeveLancarExcecao() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario(99));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario("00011122299"));
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.buscarFuncionario("Zé", false));
    }

    @Test
    void deveRemoverFuncionario() throws Exception {
        service.removerFuncionario(1);
        assertEquals(2, service.listarFuncionarios().size());
    }

    @Test
    void removerInexistenteDeveLancarExcecao() {
        assertThrows(FuncionarioNaoEncontradoException.class, () -> service.removerFuncionario(99));
    }

    @Test
    void deveCalcularRemuneracaoDeCadaTipo() throws Exception {
        assertEquals(3500, service.calcularRemuneracao(1), 0.001);
        assertEquals(6000, service.calcularRemuneracao(2), 0.001);
        assertEquals(3000, service.calcularRemuneracao(3), 0.001);
    }

    @Test
    void deveCalcularTotalDaFolha() {
        assertEquals(12500, service.calcularTotalFolha(), 0.001);
    }

    @Test
    void deveExibirDados() throws Exception {
        assertTrue(service.exibirDados(3).contains("Carla Souza"));
    }

    @Test
    void deveGerarRelatorio() {
        String relatorio = service.gerarRelatorio();
        assertTrue(relatorio.contains("Ana Souza"));
        assertTrue(relatorio.contains("Bruno Lima"));
        assertTrue(relatorio.contains("Carla Souza"));
        assertTrue(relatorio.contains("Total da folha"));
    }
}
