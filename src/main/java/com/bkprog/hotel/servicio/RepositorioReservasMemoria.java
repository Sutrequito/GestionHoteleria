package com.bkprog.hotel.servicio;

import com.bkprog.hotel.modelo.Reserva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementacion en memoria de {@link RepositorioReservas}.
 *
 * <p>Util para pruebas y para el modo "single-user" del software. Una segunda
 * implementacion futura podria persistir en base de datos sin afectar al
 * resto del codigo gracias al patron <em>"Extraer Interfaz"</em>.</p>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public class RepositorioReservasMemoria implements RepositorioReservas {

    private final List<Reserva> reservas = new ArrayList<>();

    @Override
    public void guardar(final Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva nula");
        }
        reservas.add(reserva);
    }

    @Override
    public List<Reserva> listar() {
        return Collections.unmodifiableList(reservas);
    }

    @Override
    public int contar() {
        return reservas.size();
    }
}
