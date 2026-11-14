package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioCLTTest {

    @Test
    void deveCalcularRemuneracao() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        assertEquals(3500, clt.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveCalcularTributos() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        assertEquals(330, clt.calcularTributos(), 0.001);
    }

    @Test
    void deveRetornarTipoCLT() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        assertEquals("CLT", clt.getTipo());
    }

    @Test
    void naoDevePermitirBeneficiosNegativos() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "12345678909", 3000, -100));
    }

    @Test
    void naoDevePermitirSalarioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioCLT(1, "Ana", "12345678909", -3000, 500));
    }

    @Test
    void exibirDadosDeveTerONome() {
        FuncionarioCLT clt = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        assertTrue(clt.exibirDados().contains("Ana"));
    }
}
