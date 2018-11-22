package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import java.time.Clock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessCaseController {

	@GetMapping("/businesscase")
	public ResponseEntity<String> applyBusinessCase() {
		return new ResponseEntity(Clock.systemDefaultZone().millis(), HttpStatus.OK);
	}
}
