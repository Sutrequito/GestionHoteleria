package com.bkprog.hotel.servicio;

import com.bkprog.hotel.modelo.Cliente;
import com.bkprog.hotel.modelo.Reserva;
import com.bkprog.hotel.modelo.TipoHabitacion;

import java.util.logging.Logger;

/**
 * Servicio de alto nivel que orquesta la creacion de reservas.
 *
 * <p>Esta clase es el resultado de aplicar varios patrones de refactorizacion
 * sobre la clase legacy {@code legacy.GestorHotel}:</p>
 * <ul>
 *   <li><b>Renombrado</b>: {@code fn1} ahora se llama {@link #crearReserva}.</li>
 *   <li><b>Extraer Metodo</b>: el calculo se delega en
 *       {@link CalculadoraTarifa}.</li>
 *   <li><b>Mover Clase</b>: las reservas se han trasladado a una entidad
 *       de dominio {@link Reserva} en su propio paquete.</li>
 *   <li><b>Encapsular Campos</b>: los datos internos son privados y solo se
 *       exponen mediante metodos.</li>
 *   <li><b>Extraer Interfaz</b>: el almacenamiento esta detras de
 *       {@link RepositorioReservas}.</li>
 *   <li><b>Cambiar parametros</b>: ahora reciben tipos de dominio
 *       ({@link Cliente}, {@link TipoHabitacion}) en lugar de cadenas.</li>
 *   <li><b>Borrado seguro</b>: se eliminaron {@code metodoNoUsado} y la
 *       duplicacion {@code fn2} (analizado con PMD/CPD).</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 * <pre>{@code
 * RepositorioReservas repo = new RepositorioReservasMemoria();
 * GestorReservas gestor = new GestorReservas(new CalculadoraTarifa(), repo);
 * Reserva r = gestor.crearReserva(
 *         new Cliente("12345678A", "Juan Lopez"),
 *         TipoHabitacion.SUITE, 3, 80.0d, 10.0d);
 * }</pre>
 *
 * @author Rodri (rodrigueishonsi)
 * @version 1.0
 * @since 2026-05-05
 * @see CalculadoraTarifa
 * @see RepositorioReservas
 */
public class GestorReservas {

    private static final Logger LOGGER = Logger.getLogger(GestorReservas.class.getName());

    private final CalculadoraTarifa calculadora;
    private final RepositorioReservas repositorio;

    /**
     * Construye el gestor con sus dependencias inyectadas.
     *
     * @param calculadora servicio de calculo de tarifas, no nulo
     * @param repositorio almacen de reservas, no nulo
     * @throws IllegalArgumentException si alguna dependencia es nula
     */
    public GestorReservas(final CalculadoraTarifa calculadora,
                          final RepositorioReservas repositorio) {
        if (calculadora == null || repositorio == null) {
            throw new IllegalArgumentException("Dependencias obligatorias");
        }
        this.calculadora = calculadora;
        this.repositorio = repositorio;
    }

    /**
     * Crea una nueva reserva, calcula su tarifa y la persiste.
     *
     * @param cliente cliente que efectua la reserva, no nulo
     * @param tipo tipo de habitacion, no nulo
     * @param noches numero de noches, debe ser positivo
     * @param precioBase tarifa base por noche en euros, no negativa
     * @param ivaPorcentaje porcentaje de IVA (por ejemplo, {@code 10.0} para el 10%)
     * @return reserva persistida, nunca nula
     * @throws IllegalArgumentException si algun argumento incumple precondiciones
     */
    public Reserva crearReserva(final Cliente cliente, final TipoHabitacion tipo,
                                final int noches, final double precioBase,
                                final double ivaPorcentaje) {
        final double total = calculadora.calcular(tipo, noches, precioBase, ivaPorcentaje);
        final Reserva reserva = new Reserva(cliente, tipo, noches, total);
        repositorio.guardar(reserva);
        LOGGER.info(() -> "Reserva creada: " + reserva);
        return reserva;
    }

    /**
     * Devuelve el numero de reservas registradas.
     *
     * @return numero entero no negativo
     */
    public int totalReservas() {
        return repositorio.contar();
    }
}
