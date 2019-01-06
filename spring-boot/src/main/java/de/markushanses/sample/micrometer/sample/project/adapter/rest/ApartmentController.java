package de.markushanses.sample.micrometer.sample.project.adapter.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Apartment management endpoint", description = "Operation for apartment lifecycle management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/apartment")
public class ApartmentController {

    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<ApartmentResource>> retrieveAll() {
        return new ResponseEntity<>(
                createApartmentList(),
                HttpStatus.OK);
    }

    private List<ApartmentResource> createApartmentList() {
        ApartmentResource apartmentResourceOne = new ApartmentResource();
        apartmentResourceOne.setApartmentId(1L);
        apartmentResourceOne.setCity("Hamburg");
        apartmentResourceOne.setStatus(ApartmentResource.Status.reserved);
        ApartmentResource apartmentResourceTwo = new ApartmentResource();
        apartmentResourceTwo.setApartmentId(2L);
        apartmentResourceTwo.setCity("Berlin");
        apartmentResourceTwo.setStatus(ApartmentResource.Status.free);
        return List.of(apartmentResourceOne, apartmentResourceTwo);
    }

}
