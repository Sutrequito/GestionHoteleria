package com.bkprog.hotel;

import com.bkprog.hotel.modelo.Cliente;
import com.bkprog.hotel.modelo.Reserva;
import com.bkprog.hotel.modelo.TipoHabitacion;
import com.bkprog.hotel.servicio.CalculadoraTarifa;
import com.bkprog.hotel.servicio.GestorReservas;
import com.bkprog.hotel.servicio.RepositorioReservas;
import com.bkprog.hotel.servicio.RepositorioReservasMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas de integracion ligera del {@link GestorReservas} con su
 * {@link RepositorioReservasMemoria}. Verifican que la refactorizacion
 * preserva el comportamiento observable: dado un cliente y un tipo de
 * habitacion, se crea una reserva con el total correcto y queda persistida.
 */
class GestorReservasTest {

    private GestorReservas gestor;
    private RepositorioReservas repo;

    @BeforeEach
    void inicializar() {
        repo = new RepositorioReservasMemoria();
        gestor = new GestorReservas(new CalculadoraTarifa(), repo);
    }

    @Test
    @DisplayName("Crear reserva persiste y devuelve total correcto")
    void crearReservaSuite() {
        final Cliente c = new Cliente("12345678A", "Juan Lopez");
        final Reserva r = gestor.crearReserva(c, TipoHabitacion.SUITE, 3, 80d, 10d);

        assertNotNull(r);
        assertSame(c, r.getCliente());
        assertEquals(TipoHabitacion.SUITE, r.getTipo());
        assertEquals(3, r.getNoches());
        assertEquals(462.0d, r.getTotal());
        assertEquals(1, gestor.totalReservas());
    }

    @Test
    @DisplayName("Multiples reservas se acumulan en el repositorio")
    void variasReservas() {
        final Cliente a = new Cliente("11111111A", "Ana Reyes");
        final Cliente b = new Cliente("22222222B", "Carlos Diaz");
        gestor.crearReserva(a, TipoHabitacion.ESTANDAR, 2, 50d, 10d);
        gestor.crearReserva(b, TipoHabitacion.SUPERIOR, 1, 100d, 10d);
        assertEquals(2, gestor.totalReservas());
    }

    @Test
    @DisplayName("Constructor rechaza dependencias nulas")
    void rechazaDependenciasNulas() {
        assertThrows(IllegalArgumentException.class,
                () -> new GestorReservas(null, repo));
        assertThrows(IllegalArgumentException.class,
                () -> new GestorReservas(new CalculadoraTarifa(), null));
    }

    @Test
    @DisplayName("Cliente con DNI vacio no puede crearse")
    void clienteInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("", "Sin DNI"));
        assertThrows(IllegalArgumentException.class,
                () -> new Cliente("123", ""));
    }
}
