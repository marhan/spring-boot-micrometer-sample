package de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment;

import java.util.List;
import java.util.stream.Collectors;

import de.markushanses.sample.micrometer.sample.adapter.rest.controller.apartment.ApartmentResource.Status;
import de.markushanses.sample.micrometer.sample.domain.Apartment;
import de.markushanses.sample.micrometer.sample.domain.ApartmentStatus;
import org.springframework.stereotype.Component;

@Component
public class ApartmentResourceMapper {

	List<ApartmentResource> mapApartmentsToResources(List<Apartment> apartmentList) {
		return apartmentList.stream().map(this::mapToResource).collect(Collectors.toList());
	}

	private ApartmentResource mapToResource(Apartment apartment) {
		ApartmentResource apartmentResource = new ApartmentResource();
		apartmentResource.setApartmentId(apartment.getApartmentId().toString());
		apartmentResource.setCity(apartment.getCity());
		apartmentResource.setStatus(mapToResourceStatus(apartment.getStatus()));
		return apartmentResource;
	}

	private Status mapToResourceStatus(ApartmentStatus apartmentStatus) {
		Status resourceStatus = null;
		switch (apartmentStatus) {
			case free:
				resourceStatus = Status.free;
				break;
			case reserved:
				resourceStatus = Status.reserved;
				break;
			case rented:
				resourceStatus = Status.rented;
				break;
		}
		return resourceStatus;
	}
}
