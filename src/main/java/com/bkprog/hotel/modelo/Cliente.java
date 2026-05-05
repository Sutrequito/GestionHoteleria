package com.bkprog.hotel.modelo;

import java.util.Objects;

/**
 * Representa a un cliente del hotel.
 *
 * <p>Aplica el patron <em>"Encapsular Campos"</em>: los campos se declaran
 * {@code private} y el acceso se realiza a traves de metodos {@code getter},
 * tal como recomienda el Tema 4 del modulo.</p>
 *
 * <p>La clase es <em>inmutable</em>: una vez creado el cliente, sus datos
 * no se pueden modificar. Esto facilita el razonamiento concurrente y
 * elimina toda una familia de bugs.</p>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public final class Cliente {

    private final String dni;
    private final String nombre;

    /**
     * Crea un cliente con DNI y nombre validados.
     *
     * @param dni DNI o documento identificativo del cliente, no nulo ni vacio
     * @param nombre nombre completo del cliente, no nulo ni vacio
     * @throws IllegalArgumentException si {@code dni} o {@code nombre} estan vacios o son nulos
     */
    public Cliente(final String dni, final String nombre) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.dni = dni;
        this.nombre = nombre;
    }

    /**
     * Devuelve el DNI del cliente.
     *
     * @return DNI nunca nulo
     */
    public String getDni() {
        return dni;
    }

    /**
     * Devuelve el nombre completo del cliente.
     *
     * @return nombre nunca nulo
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        final Cliente otro = (Cliente) o;
        return Objects.equals(dni, otro.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
