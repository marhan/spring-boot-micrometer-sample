package de.markushanses.sample.micrometer.sample.domain.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> retrieveApartments() {
        return apartmentRepository.findAll();
    }

    public void reserveApartment(UUID apartmentId) {
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.reserve();
        apartmentRepository.save(apartment);
    }


    public void rentApartment(UUID apartmentId) {
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.rent();
        apartmentRepository.save(apartment);
    }

    public void cancelApartment(UUID apartmentId) {
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.cancel();
        apartmentRepository.save(apartment);
    }
}
