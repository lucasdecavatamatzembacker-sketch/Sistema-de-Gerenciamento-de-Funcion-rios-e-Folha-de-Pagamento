package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IdentificavelTest {
    @Test
    void cltDeveRetornarCodigoPelaInterface() {
        Identificavel i = new FuncionarioCLT(10, "Ana", "12345678909", 3000, 500);
        assertEquals(10, i.getCodigo());
    }

    @Test
    void pjDeveRetornarCodigoPelaInterface() {
        Identificavel i = new FuncionarioPJ(20, "Bruno", "98765432100", 2000, 3);
        assertEquals(20, i.getCodigo());
    }

    @Test
    void comissionadoDeveRetornarCodigoPelaInterface() {
        Identificavel i = new FuncionarioComissionado(30, "Carla", "11122233344", 2000, 10, 10000);
        assertEquals(30, i.getCodigo());
    }
}
