package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import java.time.Clock;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Timed
public class BusinessCaseController {

	private final Counter rentApartmentCounter;
	private final Counter cancelApartmentCounter;

	@Autowired
	public BusinessCaseController(MeterRegistry registry) {
		this.rentApartmentCounter = registry.counter("counter.apartment.rent.success");
		this.cancelApartmentCounter = registry.counter("counter.apartment.rent.cancel");
	}

	//@GetMapping("/rent-apartment")
	//@Timed(extraTags = {"region", "hamburg"})
	@Timed(value = "timer.apartment.rent.success", longTask = true)
	public ResponseEntity<String> rentApartment() {
		this.rentApartmentCounter.increment();
		return new ResponseEntity(Clock.systemDefaultZone().millis(), HttpStatus.OK);
	}


	//@GetMapping("/cancel-apartment")
	//@Timed(extraTags = {"region", "hamburg"})
	@Timed(value = "timer.apartment.rent.success", longTask = true)
	public ResponseEntity<String> cancelApartment() {
		this.cancelApartmentCounter.increment();
		return new ResponseEntity(Clock.systemDefaultZone().millis(), HttpStatus.OK);
	}
}
