package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final Counter errorApartmentCounter;
	private final Counter errorHouseCounter;

	@Autowired
	public RestExceptionHandler(MeterRegistry registry) {
		errorApartmentCounter = registry.counter("counter.apartment.rent.error", "object_type", "apartment", "rent_process_state", "error");
		errorHouseCounter = registry.counter("counter.house.rent.error", "object_type", "house", "rent_process_state", "error");
	}

	@ExceptionHandler({ApartmentNotFoundException.class})
	protected ResponseEntity<Object> handleApartmentNotFound(Exception ex, WebRequest request) {
		errorApartmentCounter.increment();
		return handleExceptionInternal(ex, "Apartment not found, try later!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({HouseNotFoundException.class})
	protected ResponseEntity<Object> handleHouseFound(Exception ex, WebRequest request) {
		errorHouseCounter.increment();
		return handleExceptionInternal(ex, "House not found, try later!", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}