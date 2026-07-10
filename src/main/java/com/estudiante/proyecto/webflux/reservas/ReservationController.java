package com.estudiante.proyecto.webflux.reservas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ReservationController {

    @GetMapping("/api/reservations/stream")
    public Flux<ReservationEvent> streamReservations() {

        // 3 reservas válidas (precio > 0 y con al menos un email)
        ReservationEvent valida1 = new ReservationEvent(
                "R001", "Juan Perez", 45.50, List.of("juan@mail.com")
        );
        ReservationEvent valida2 = new ReservationEvent(
                "R002", "Maria Lopez", 120.0, List.of("maria@mail.com", "backup@mail.com")
        );
        ReservationEvent valida3 = new ReservationEvent(
                "R003", "Carlos Ruiz", 78.90, List.of("carlos@mail.com")
        );

        // 2 reservas inválidas
        ReservationEvent invalidaPrecio = new ReservationEvent(
                "R004", "Ana Torres", 0.0, List.of("ana@mail.com") // precio no es > 0
        );
        ReservationEvent invalidaSinEmails = new ReservationEvent(
                "R005", "Luis Diaz", 30.0, List.of() // lista de emails vacía
        );

        return Flux.just(valida1, valida2, valida3, invalidaPrecio, invalidaSinEmails)
                .filter(ReservationFilters.ES_VALIDA)
                .doOnNext(ReservationFilters.IMPRIMIR_EVENTO)
                .defaultIfEmpty(reservaPorDefecto());
    }

    private ReservationEvent reservaPorDefecto() {
        return new ReservationEvent(
                "R000",
                "Reserva Generica",
                1.0,
                List.of("default@mail.com")
        );
    }
}