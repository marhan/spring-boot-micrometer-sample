package de.markushanses.sample.micrometer.Micrometer.sample.project.adapter.rest;

public class ApartmentNotFoundException extends RuntimeException {


	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
