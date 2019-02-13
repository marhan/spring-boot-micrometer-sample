package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import de.markushanses.sample.micrometer.sample.domain.apartment.Apartment;
import de.markushanses.sample.micrometer.sample.domain.apartment.ApartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "Apartment management endpoint", description = "Operation for apartment lifecycle management")
public class ApartmentController {

    private final ApartmentService apartmentService;
    private final ApartmentResourceMapper apartmentResourceMapper;

    @Autowired
    public ApartmentController(ApartmentService apartmentService, ApartmentResourceMapper apartmentResourceMapper) {
        this.apartmentService = apartmentService;
        this.apartmentResourceMapper = apartmentResourceMapper;
    }

    @ApiOperation(value = "View a list of available apartments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list of apartments")
    })
    @GetMapping(path = "api/apartment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ApartmentResource>> retrieveAll() {
        List<Apartment> apartmentList = apartmentService.findAll();
        List<ApartmentResource> apartmentResourceList = apartmentResourceMapper.mapApartmentsToResources(apartmentList);
        return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
    }

    @ApiOperation(value = "View a the specified apartment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the apartment")
    })
    @GetMapping(path = "api/apartment/{apartmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApartmentResource> retrieve(@PathVariable(name = "apartmentId") UUID apartmentId) {
        Apartment apartment = apartmentService.findByApartmentId(apartmentId);
        ApartmentResource apartmentResource = apartmentResourceMapper.mapApartmentToResource(apartment);
        return new ResponseEntity<>(apartmentResource, HttpStatus.OK);
    }

    @ApiOperation(value = "View a the specified apartment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the apartment")
    })
    @PutMapping(path = "api/apartment/{apartmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApartmentResource> update(@PathVariable(name = "apartmentId") UUID apartmentId, @RequestBody ApartmentUpdateResource resource) {
        Apartment apartment = apartmentService.update(apartmentId, resource.getStreet(), resource.getCity());
        ApartmentResource apartmentResource = apartmentResourceMapper.mapApartmentToResource(apartment);
        return new ResponseEntity<>(apartmentResource, HttpStatus.OK);
    }


}
