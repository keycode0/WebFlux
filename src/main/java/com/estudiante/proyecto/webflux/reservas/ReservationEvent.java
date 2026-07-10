package com.estudiante.proyecto.webflux.reservas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = Objects.requireNonNull(id, "id no puede ser null");
        this.passengerName = Objects.requireNonNull(passengerName, "passengerName no puede ser null");
        this.price = Objects.requireNonNull(price, "price no puede ser null");

        // Copia defensiva en el constructor: no se guarda la referencia recibida,
        // se crea una lista NUEVA con el mismo contenido.
        this.emails = (emails == null) ? List.of() : new ArrayList<>(emails);
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getEmails() {
        // Copia defensiva en el getter: no se expone la referencia interna,
        // se devuelve una lista NUEVA e inmutable.
        return List.copyOf(this.emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationEvent that)) return false;
        return id.equals(that.id)
                && passengerName.equals(that.passengerName)
                && price.equals(that.price)
                && emails.equals(that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengerName, price, emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}
