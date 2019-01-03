package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import lombok.Data;

import java.io.Serializable;

@Data
class ReserveApartmentRequest implements Serializable {

    private Long apartmentId;

}
