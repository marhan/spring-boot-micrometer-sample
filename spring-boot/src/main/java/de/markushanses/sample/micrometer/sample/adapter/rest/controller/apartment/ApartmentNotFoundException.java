package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

public class  ApartmentNotFoundException extends RuntimeException {


	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
