package com.skaypal.ebay_clone.domain.country.repository;

import com.skaypal.ebay_clone.domain.country.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    public Optional<Country> findByCountry(String country);
    public List<Country> findAll();
}
