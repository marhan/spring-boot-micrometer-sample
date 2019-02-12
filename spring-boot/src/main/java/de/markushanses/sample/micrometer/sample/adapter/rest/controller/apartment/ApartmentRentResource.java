package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@ApiModel(description = "Reservation for an apartment.")
class ApartmentRentResource implements Serializable {

    @ApiModelProperty(notes = "The apartment ID.")
    private UUID apartmentId;

}
