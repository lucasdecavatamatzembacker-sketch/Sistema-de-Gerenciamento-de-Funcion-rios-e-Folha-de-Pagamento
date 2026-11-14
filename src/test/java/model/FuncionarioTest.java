package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioTest {

    @Test
    void deveCriarFuncionarioComDadosValidos() {
        Funcionario f = new FuncionarioCLT(1, "Ana", "12345678909", 1500, 0);
        assertEquals(1, f.getCodigo());
        assertEquals("Ana", f.getNome());
        assertEquals("12345678909", f.getCpf());
        assertEquals(1500, f.getSalarioBase(), 0.001);
    }

    @Test
    void deveAceitarCpfComPontosETraco() {
        Funcionario f = new FuncionarioCLT(1, "Ana", "123.456.789-09", 1500, 0);
        assertEquals("12345678909", f.getCpf());
    }

    @Test
    void naoDeveAceitarCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "123", 1500, 0));
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "11111111111", 1500, 0));
    }

    @Test
    void naoDeveAceitarCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "Ana", "", 1500, 0));
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(1, "", "12345678909", 1500, 0));
    }

    @Test
    void naoDeveAceitarCodigoZeroOuNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(0, "Ana", "12345678909", 1500, 0));
        assertThrows(IllegalArgumentException.class, () -> new FuncionarioCLT(-1, "Ana", "12345678909", 1500, 0));
    }

    @Test
    void naoDeveAceitarSalarioNegativo() {
        Funcionario f = new FuncionarioCLT(1, "Ana", "12345678909", 1500, 0);
        assertThrows(IllegalArgumentException.class, () -> f.setSalarioBase(-500));
    }

    @Test
    void deveTestarPolimorfismo() {
        Funcionario funcionario1 = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        Funcionario funcionario2 = new FuncionarioPJ(2, "Bruno", "98765432100", 2000, 3);
        Funcionario funcionario3 = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);

        assertEquals(3500, funcionario1.calcularRemuneracao(), 0.001);
        assertEquals(6000, funcionario2.calcularRemuneracao(), 0.001);
        assertEquals(3000, funcionario3.calcularRemuneracao(), 0.001);
    }
}
