package de.markushanses.sample.micrometer.sample.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "apartment")
@Data
public class Apartment {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "apartmentId")
	private UUID apartmentId;

	@Column(name = "city")
	private String city;

	@Column(name = "status")
	private ApartmentStatus status;

}
