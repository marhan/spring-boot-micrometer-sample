package de.markushanses.sample.micrometer.sample.project.adapter.rest.controller.apartment;

public class  ApartmentNotFoundException extends RuntimeException {


	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
