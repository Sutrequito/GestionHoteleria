package com.bkprog.hotel;

import com.bkprog.hotel.modelo.Cliente;
import com.bkprog.hotel.modelo.Reserva;
import com.bkprog.hotel.modelo.TipoHabitacion;
import com.bkprog.hotel.servicio.CalculadoraTarifa;
import com.bkprog.hotel.servicio.GestorReservas;
import com.bkprog.hotel.servicio.RepositorioReservas;
import com.bkprog.hotel.servicio.RepositorioReservasMemoria;

/**
 * Punto de entrada de demostracion del proyecto.
 *
 * <p>Crea unas reservas de ejemplo para mostrar el flujo completo
 * tras aplicar los patrones de refactorizacion del Tema 4. No tiene
 * logica de negocio: actua como cliente del API publico.</p>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public final class Aplicacion {

    private Aplicacion() {
        // Clase de utilidad - se evita instanciacion
    }

    /**
     * Punto de entrada de la aplicacion.
     *
     * @param args argumentos de linea de comandos (no se utilizan)
     */
    public static void main(final String[] args) {
        final RepositorioReservas repo = new RepositorioReservasMemoria();
        final GestorReservas gestor = new GestorReservas(new CalculadoraTarifa(), repo);

        final Reserva r1 = gestor.crearReserva(
                new Cliente("12345678A", "Juan Lopez"),
                TipoHabitacion.ESTANDAR, 2, 60.0d, 10.0d);

        final Reserva r2 = gestor.crearReserva(
                new Cliente("87654321B", "Maria Garcia"),
                TipoHabitacion.SUITE, 4, 120.0d, 10.0d);

        System.out.println("=== BK Programacion - Gestion Hotelera ===");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println("Total reservas: " + gestor.totalReservas());
    }
}
