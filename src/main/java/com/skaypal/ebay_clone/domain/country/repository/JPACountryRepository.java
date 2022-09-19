package com.skaypal.ebay_clone.domain.country.repository;

import com.skaypal.ebay_clone.domain.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPACountryRepository extends JpaRepository<Country,Integer> {
    public Optional<Country> findByCountry(String country);
    public Optional<Country> findByIso(String iso);
}
