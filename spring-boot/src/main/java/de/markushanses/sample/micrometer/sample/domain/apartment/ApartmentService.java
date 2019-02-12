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

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public Apartment findByApartmentId(UUID apartmentId) {
        return apartmentRepository.findByApartmentId(apartmentId);
    }

    public Apartment update(UUID apartmentId, String street, String city) {
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId);
        apartment.setStreet(street);
        apartment.setCity(city);
        return apartmentRepository.save(apartment);
    }
}
