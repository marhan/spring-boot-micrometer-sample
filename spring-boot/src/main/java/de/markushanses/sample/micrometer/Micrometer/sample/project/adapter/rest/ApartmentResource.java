package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import lombok.Data;

import java.io.Serializable;

@Data
class ApartmentResource implements Serializable {

    public enum Status {
        free, reserved, rented
    }

    private Long apartmentId;
    private String city;
    private Status status;

}
