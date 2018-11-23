package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import java.time.Clock;
import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "apartment")
public class ApartmentController {

	private final Counter rentSuccessCounter;
	private final Counter startRentCounter;
	private final Counter cancelCounter;
	private final AtomicInteger rentProgress;

	@Autowired
	public ApartmentController(MeterRegistry registry) {
		this.startRentCounter = registry.counter("counter.apartment.rent.start", "object_type", "apartment", "rent_process_state", "started");
		this.rentSuccessCounter = registry.counter("counter.apartment.rent.success", "object_type", "apartment", "rent_process_state", "success");
		this.cancelCounter = registry.counter("counter.apartment.rent.cancel", "object_type", "apartment", "rent_process_state", "aborted");
		this.rentProgress = registry.gauge("gauge.apartment.rent.progress", Tags.of("object_type", "apartment", "rent_process_state", "ongoing"), new AtomicInteger(0));
	}

	@GetMapping("/start-rent-apartment/{apartmentNumber}")
	public ResponseEntity<String> startRentApartment(@PathVariable Long apartmentNumber) {
		if (apartmentNumber == 2l) {
			throw new ApartmentNotFoundException("No apartment found");
		}
		startRentCounter.increment();
		rentProgress.incrementAndGet();
		return new ResponseEntity("Apartment start rent ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}

	@GetMapping("/rent-apartment/{apartmentNumber}")
	public ResponseEntity<String> rentApartment(@PathVariable Long apartmentNumber) {
		if (apartmentNumber == 2l) {
			throw new ApartmentNotFoundException("No apartment found");
		}
		rentProgress.decrementAndGet();
		rentSuccessCounter.increment();
		return new ResponseEntity("Apartment rent ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}


	@GetMapping("/abort-rent-apartment")
	public ResponseEntity<String> abortRentApartment() {
		cancelCounter.increment();
		rentProgress.decrementAndGet();
		return new ResponseEntity("Apartment rent aborted ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}
}