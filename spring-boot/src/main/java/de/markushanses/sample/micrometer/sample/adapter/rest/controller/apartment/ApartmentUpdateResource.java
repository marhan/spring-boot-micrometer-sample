package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "The details of an apartment that can be updated. ")
class ApartmentUpdateResource implements Serializable {

    @ApiModelProperty(notes = "The city where the apartment is located.")
    private String city;

    @ApiModelProperty(notes = "The street where the apartment is located.")
    private String street;
}
