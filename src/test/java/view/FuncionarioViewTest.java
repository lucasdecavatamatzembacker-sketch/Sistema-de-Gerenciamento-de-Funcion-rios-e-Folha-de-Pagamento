package view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.FuncionarioCLT;
import repository.FuncionarioRepository;
import service.FuncionarioService;

class FuncionarioViewTest {

    private FuncionarioService service;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService(new FuncionarioRepository());
    }

    @Test
    void deveCadastrarPeloMenu() {
        Scanner entrada = new Scanner("1\n1\n1\nAna\n12345678909\n3000\n500\n0\n");
        FuncionarioView view = new FuncionarioView(service, entrada);
        view.iniciar();
        assertEquals(1, service.listarFuncionarios().size());
    }

    @Test
    void naoDeveCadastrarComCpfInvalido() {
        Scanner entrada = new Scanner("1\n1\n1\nAna\n123\n3000\n500\n0\n");
        FuncionarioView view = new FuncionarioView(service, entrada);
        view.iniciar();
        assertEquals(0, service.listarFuncionarios().size());
    }

    @Test
    void deveRemoverPeloMenu() throws Exception {
        service.cadastrar(new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500));
        Scanner entrada = new Scanner("6\n1\n0\n");
        FuncionarioView view = new FuncionarioView(service, entrada);
        view.iniciar();
        assertEquals(0, service.listarFuncionarios().size());
    }
}
