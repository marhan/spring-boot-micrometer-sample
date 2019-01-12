package de.markushanses.sample.micrometer.sample.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    public Apartment findByApartmentId(UUID apartmentId);
    
}
