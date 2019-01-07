package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "All details about the apartment. ")
class ApartmentResource implements Serializable {

    public enum Status {
        free, reserved, rented
    }

    @ApiModelProperty(notes = "The apartment ID.")
    private Long apartmentId;

    @ApiModelProperty(notes = "The place of the apartment.")
    private String city;

    @ApiModelProperty(notes = "The status of the apartment.")
    private Status status;

}
