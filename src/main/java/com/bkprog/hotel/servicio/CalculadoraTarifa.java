package com.bkprog.hotel.servicio;

import com.bkprog.hotel.modelo.TipoHabitacion;

/**
 * Servicio responsable unicamente del calculo de tarifas.
 *
 * <p>Aplica el patron <em>"Extraer Metodo"</em>: la formula del total estaba
 * embebida dentro de un metodo gigante {@code fn1} en la version legacy. Al
 * aislarla en una clase con responsabilidad unica, conseguimos:</p>
 * <ul>
 *   <li>Reutilizacion: cualquier servicio puede pedir un calculo.</li>
 *   <li>Testabilidad: las pruebas de tarifa se escriben sin dependencias.</li>
 *   <li>Legibilidad: el {@link com.bkprog.hotel.servicio.GestorReservas} ya no
 *       mezcla varias responsabilidades.</li>
 * </ul>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public class CalculadoraTarifa {

    /** Constante extraida (eliminamos numeros magicos del codigo legacy). */
    private static final double PORCENTAJE_BASE = 100.0d;

    /**
     * Calcula el total a pagar por una estancia.
     *
     * <p>La formula aplica el coeficiente del tipo de habitacion sobre el precio
     * base por noche y, finalmente, el IVA expresado como porcentaje.</p>
     *
     * @param tipo tipo de habitacion seleccionada, no nulo
     * @param noches numero de noches, positivo
     * @param precioBase tarifa base por noche en euros, no negativa
     * @param ivaPorcentaje porcentaje de IVA aplicable (por ejemplo {@code 10.0} para el 10%)
     * @return importe total con IVA, redondeado al centimo mas cercano
     * @throws IllegalArgumentException si las precondiciones no se cumplen
     */
    public double calcular(final TipoHabitacion tipo, final int noches,
                           final double precioBase, final double ivaPorcentaje) {
        validarParametros(tipo, noches, precioBase, ivaPorcentaje);
        final double subtotal = precioBase * noches * tipo.getCoeficiente();
        final double conIva = subtotal + (subtotal * ivaPorcentaje / PORCENTAJE_BASE);
        return redondearADosDecimales(conIva);
    }

    private void validarParametros(final TipoHabitacion tipo, final int noches,
                                   final double precioBase, final double iva) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de habitacion es obligatorio");
        }
        if (noches <= 0) {
            throw new IllegalArgumentException("Las noches deben ser positivas");
        }
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio base no puede ser negativo");
        }
        if (iva < 0) {
            throw new IllegalArgumentException("El IVA no puede ser negativo");
        }
    }

    private double redondearADosDecimales(final double valor) {
        return Math.round(valor * PORCENTAJE_BASE) / PORCENTAJE_BASE;
    }
}
