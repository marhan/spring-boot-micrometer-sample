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
@RequestMapping(value = "api/house")
public class HouseController {

	private final Counter rentCounter;
	private final Counter cancelCounter;
	private final AtomicInteger rentProgress;
	private final Counter startRentCounter;

	@Autowired
	public HouseController(MeterRegistry registry) {
		this.startRentCounter = registry.counter("counter.house.rent.start", "object_type", "house", "rent_process_state", "started");
		this.rentCounter = registry.counter("counter.house.rent.success", "object_type", "house", "rent_process_state", "success");
		this.cancelCounter = registry.counter("counter.house.rent.cancel", "object_type", "house", "rent_process_state", "aborted");
		this.rentProgress = registry.gauge("gauge.house.rent.progress", Tags.of("object_type", "house", "rent_process_state", "ongoing"), new AtomicInteger(0));
	}

	@GetMapping("/start-rent-house/{houseNumber}")
	public ResponseEntity<String> startRentHouse(@PathVariable Long houseNumber) {
		if (houseNumber == 2l) {
			throw new HouseNotFoundException("No house found");
		}
		startRentCounter.increment();
		rentProgress.incrementAndGet();
		return new ResponseEntity("House start rent ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}

	@GetMapping("/rent-house/{houseNumber}")
	public ResponseEntity<String> rentHouse(@PathVariable Long houseNumber) {
		if (houseNumber == 2l) {
			throw new HouseNotFoundException("No house found");
		}
		rentProgress.decrementAndGet();
		rentCounter.increment();
		return new ResponseEntity("House rent ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}


	@GetMapping("/abort-rent-house")
	public ResponseEntity<String> cancelHouse() {
		cancelCounter.increment();
		rentProgress.decrementAndGet();
		return new ResponseEntity("House abort ID: " + String.valueOf(Clock.systemDefaultZone().millis()), HttpStatus.OK);
	}
}
