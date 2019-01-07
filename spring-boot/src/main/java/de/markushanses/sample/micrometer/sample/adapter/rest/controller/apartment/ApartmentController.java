package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import java.util.List;

import de.markushanses.sample.micrometer.sample.domain.Apartment;
import de.markushanses.sample.micrometer.sample.domain.ApartmentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Apartment management endpoint", description = "Operation for apartment lifecycle management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/apartment", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApartmentController {

	private final ApartmentRepository apartmentRepository;
	private final ApartmentResourceMapper apartmentResourceMapper;

	@Autowired
	public ApartmentController(ApartmentRepository apartmentRepository, ApartmentResourceMapper apartmentResourceMapper) {
		this.apartmentRepository = apartmentRepository;
		this.apartmentResourceMapper = apartmentResourceMapper;
	}

	@ApiOperation(value = "View a list of available employees")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list")
	})
	@GetMapping
	public ResponseEntity<List<ApartmentResource>> retrieveAll() {
		List<Apartment> apartmentList = apartmentRepository.findAll();
		List<ApartmentResource> apartmentResourceList = apartmentResourceMapper.mapApartmentsToResources(apartmentList);
		return new ResponseEntity<>(apartmentResourceList, HttpStatus.OK);
	}


}
