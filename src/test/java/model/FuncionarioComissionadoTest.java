package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FuncionarioComissionadoTest {

    @Test
    void deveCalcularComissao() {
        FuncionarioComissionado c = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals(1000, c.calcularComissao(), 0.001);
    }

    @Test
    void deveCalcularRemuneracao() {
        FuncionarioComissionado c = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals(3000, c.calcularRemuneracao(), 0.001);
    }

    @Test
    void deveCalcularTributos() {
        FuncionarioComissionado c = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals(330, c.calcularTributos(), 0.001);
    }

    @Test
    void naoDevePermitirPercentualInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioComissionado(3, "Carla", "11122233344", 2000, -5, 10000));
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 150, 10000));
    }

    @Test
    void naoDevePermitirVendasNegativas() {
        assertThrows(IllegalArgumentException.class,
                () -> new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, -10000));
    }

    @Test
    void deveRetornarTipoComissionado() {
        FuncionarioComissionado c = new FuncionarioComissionado(3, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals("Comissionado", c.getTipo());
    }
}
