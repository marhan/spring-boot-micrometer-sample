package de.markushanses.sample.micrometer.sample.adapter.rest.exception;

public class  ApartmentNotFoundException extends RuntimeException {


	public ApartmentNotFoundException(String message) {
		super(message);
	}
}
