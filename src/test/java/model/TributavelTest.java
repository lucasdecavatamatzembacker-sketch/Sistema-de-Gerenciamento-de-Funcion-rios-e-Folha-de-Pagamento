package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TributavelTest {

    @Test
    void cltDeveCalcularTributos() {
        Tributavel t = new FuncionarioCLT(1, "Ana", "12345678909", 3000, 500);
        assertEquals(330, t.calcularTributos(), 0.001);
    }

    @Test
    void comissionadoDeveCalcularTributos() {
        Tributavel t = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals(330, t.calcularTributos(), 0.001);
    }

    @Test
    void pjNaoDeveSerTributavel() {
        Funcionario pj = new FuncionarioPJ(2, "Bruno", "98765432100", 2000, 3);
        assertFalse(pj instanceof Tributavel);
    }
}
