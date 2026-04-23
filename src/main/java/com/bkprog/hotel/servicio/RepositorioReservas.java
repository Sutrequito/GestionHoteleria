package com.bkprog.hotel.servicio;

import com.bkprog.hotel.modelo.Reserva;

import java.util.List;

/**
 * Contrato de almacenamiento de reservas.
 *
 * <p>Aplica el patron <em>"Extraer Interfaz"</em>. Define las operaciones
 * publicas {@code non-static} que cualquier implementacion (memoria, fichero,
 * base de datos...) debe ofrecer. Esto permite cambiar de almacen sin
 * modificar el resto del sistema (principio de inversion de dependencias).</p>
 *
 * @author Rodri (rodrigueishonsi)
 * @version 1.0
 * @since 2026-05-05
 */
public interface RepositorioReservas {

    /**
     * Anade una reserva al almacen.
     *
     * @param reserva reserva a persistir, no nula
     */
    void guardar(Reserva reserva);

    /**
     * @return lista inmutable con todas las reservas almacenadas
     */
    List<Reserva> listar();

    /**
     * @return numero total de reservas registradas
     */
    int contar();
}
