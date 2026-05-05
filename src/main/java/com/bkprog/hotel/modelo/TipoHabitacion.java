package com.bkprog.hotel.modelo;

/**
 * Enumeracion que representa los tipos de habitacion disponibles en el hotel,
 * con su correspondiente coeficiente multiplicador sobre el precio base.
 *
 * <p>Aplica el patron de refactorizacion <em>"Sustituir constante por enumeracion"</em>:
 * en la version legacy se usaban literales {@code String} ("STD", "SUP", "SUI")
 * dispersos por el codigo, lo que era propenso a errores tipograficos. Al moverlos
 * a un {@code enum} se obtiene seguridad de tipos en tiempo de compilacion.</p>
 *
 * @author Rodrigo Siboldi
 * @version 1.0
 * @since 2026-05-05
 */
public enum TipoHabitacion {

    /** Habitacion estandar. Sin recargo. */
    ESTANDAR(1.00d, "Estandar"),

    /** Habitacion superior. 25% de recargo sobre el precio base. */
    SUPERIOR(1.25d, "Superior"),

    /** Suite. 75% de recargo sobre el precio base. */
    SUITE(1.75d, "Suite");

    private final double coeficiente;
    private final String descripcion;

    TipoHabitacion(final double coeficiente, final String descripcion) {
        this.coeficiente = coeficiente;
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el coeficiente multiplicador que se aplica al precio base.
     *
     * @return coeficiente como valor decimal (1.00 para estandar)
     */
    public double getCoeficiente() {
        return coeficiente;
    }

    /**
     * Devuelve la descripcion legible del tipo de habitacion.
     *
     * @return descripcion en texto plano
     */
    public String getDescripcion() {
        return descripcion;
    }
}
