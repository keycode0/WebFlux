package com.estudiante.proyecto.webflux.reservas;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase utilitaria que agrupa operaciones funcionales
 * sobre ReservationEvent, definidas como variables lambda.
 */
public final class ReservationFilters {

    // Constructor privado: esta clase no se instancia, solo se usan sus miembros estáticos.
    private ReservationFilters() {
    }

    /**
     * Predicate que valida que la reserva sea "válida":
     * - el precio debe ser mayor a 0
     * - la lista de emails no debe estar vacía
     */
    public static final Predicate<ReservationEvent> ES_VALIDA = evento ->
            evento.getPrice() != null
                    && evento.getPrice() > 0
                    && evento.getEmails() != null
                    && !evento.getEmails().isEmpty();

    /**
     * Consumer que imprime por consola el evento procesado.
     */
    public static final Consumer<ReservationEvent> IMPRIMIR_EVENTO = evento ->
            System.out.println("Evento procesado: " + evento);
}