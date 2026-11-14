package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioPJTest {

    @Test
    void deveCalcularRemuneracaoPorProjetos() {
        FuncionarioPJ pj = new FuncionarioPJ(2, "Bruno", "98765432100", 2000, 3);
        assertEquals(6000, pj.calcularRemuneracao(), 0.001);
    }

    @Test
    void semProjetosRemuneracaoDeveSerZero() {
        FuncionarioPJ pj = new FuncionarioPJ(2, "Bruno", "98765432100", 2000, 0);
        assertEquals(0, pj.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveRetornarTipoPJ() {
        FuncionarioPJ pj = new FuncionarioPJ(2, "Bruno", "98765432100", 2000, 3);
        assertEquals("PJ", pj.getTipo());
    }

    @Test
    void naoDevePermitirValorPorProjetoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(2, "Bruno", "98765432100", -2000, 3));
    }

    @Test
    void naoDevePermitirQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioPJ(2, "Bruno", "98765432100", 2000, -1));
    }
}
