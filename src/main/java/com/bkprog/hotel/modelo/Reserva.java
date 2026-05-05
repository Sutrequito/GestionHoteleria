package com.bkprog.hotel.modelo;

import java.util.Objects;

/**
 * Representa una reserva concreta dentro del sistema de Gestion Hotelera.
 *
 * <p>En la version legacy las reservas se almacenaban como cadenas de texto
 * concatenadas con barras verticales ({@code "Cliente|Tipo|Noches|Total"}),
 * lo que mezclaba representacion y datos. Tras aplicar el patron
 * <em>"Mover Clase"</em> y <em>"Encapsular Campos"</em>, la reserva pasa a
 * ser una entidad de dominio con campos privados, getters y validaciones.</p>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public final class Reserva {

    private final Cliente cliente;
    private final TipoHabitacion tipo;
    private final int noches;
    private final double total;

    /**
     * Crea una reserva calculada y la asocia a un cliente.
     *
     * @param cliente cliente que efectua la reserva, no nulo
     * @param tipo tipo de habitacion solicitada, no nulo
     * @param noches numero de noches, debe ser positivo
     * @param total importe total ya calculado (con IVA), debe ser positivo
     * @throws IllegalArgumentException si algun argumento no cumple las precondiciones
     */
    public Reserva(final Cliente cliente, final TipoHabitacion tipo,
                   final int noches, final double total) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nulo");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de habitacion nulo");
        }
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser un entero positivo");
        }
        if (total < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
        this.cliente = cliente;
        this.tipo = tipo;
        this.noches = noches;
        this.total = total;
    }

    /**
     * @return cliente asociado a la reserva
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return tipo de habitacion reservada
     */
    public TipoHabitacion getTipo() {
        return tipo;
    }

    /**
     * @return numero de noches reservadas
     */
    public int getNoches() {
        return noches;
    }

    /**
     * @return importe total con impuestos
     */
    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reserva)) {
            return false;
        }
        final Reserva otra = (Reserva) o;
        return noches == otra.noches
                && Double.compare(otra.total, total) == 0
                && Objects.equals(cliente, otra.cliente)
                && tipo == otra.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, tipo, noches, total);
    }

    @Override
    public String toString() {
        return String.format("Reserva[%s, %s, %d noches, %.2f EUR]",
                cliente, tipo.getDescripcion(), noches, total);
    }
}
