package com.bkprog.hotel;

import com.bkprog.hotel.modelo.TipoHabitacion;
import com.bkprog.hotel.servicio.CalculadoraTarifa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas unitarias para {@link CalculadoraTarifa}.
 *
 * <p>Cubren el criterio b) de la rubrica: pruebas asociadas a la
 * refactorizacion. Si la refactorizacion no cambia el comportamiento, estos
 * tests deben seguir pasando con la nueva implementacion.</p>
 */
class CalculadoraTarifaTest {

    private CalculadoraTarifa calculadora;

    @BeforeEach
    void inicializar() {
        calculadora = new CalculadoraTarifa();
    }

    @Test
    @DisplayName("Estandar: precio base x noches + IVA")
    void calcularEstandar() {
        // 50 EUR x 2 noches = 100; +10% IVA = 110
        assertEquals(110.0d,
                calculadora.calcular(TipoHabitacion.ESTANDAR, 2, 50.0d, 10.0d));
    }

    @Test
    @DisplayName("Superior aplica coeficiente 1.25")
    void calcularSuperior() {
        // 100 x 1 x 1.25 = 125; +10% = 137.50
        assertEquals(137.5d,
                calculadora.calcular(TipoHabitacion.SUPERIOR, 1, 100.0d, 10.0d));
    }

    @Test
    @DisplayName("Suite aplica coeficiente 1.75")
    void calcularSuite() {
        // 80 x 3 x 1.75 = 420; +10% = 462
        assertEquals(462.0d,
                calculadora.calcular(TipoHabitacion.SUITE, 3, 80.0d, 10.0d));
    }

    @ParameterizedTest(name = "{0} noches a {1} EUR con IVA {2}% => {3}")
    @CsvSource({
            "1,  50.0, 0.0,   50.00",
            "2,  60.0, 10.0, 132.00",
            "5, 100.0, 21.0, 605.00",
            "7,  40.0, 4.0,  291.20"
    })
    @DisplayName("Tabla de casos parametrizados (Estandar)")
    void calcularConValoresParametrizados(final int noches, final double base,
                                          final double iva, final double esperado) {
        assertEquals(esperado,
                calculadora.calcular(TipoHabitacion.ESTANDAR, noches, base, iva));
    }

    @Test
    @DisplayName("Lanza excepcion si las noches no son positivas")
    void rechazaNochesInvalidas() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(TipoHabitacion.ESTANDAR, 0, 50d, 10d));
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(TipoHabitacion.ESTANDAR, -1, 50d, 10d));
    }

    @Test
    @DisplayName("Lanza excepcion si el tipo es nulo")
    void rechazaTipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcular(null, 1, 50d, 10d));
    }
}
