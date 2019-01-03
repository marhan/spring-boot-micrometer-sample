package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import lombok.Data;

import java.io.Serializable;

@Data
class ApartmentResource implements Serializable {

    private Long apartmentId;

}
