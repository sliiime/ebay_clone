package com.skaypal.ebay_clone.domain.country.service;


import com.skaypal.ebay_clone.domain.country.exception.CountryNotFoundException;
import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public Country getCountry(String country){
        return countryRepository.findByCountry(country).orElseThrow(() -> new CountryNotFoundException(country));
    }

    public List<Country> getCountries(){
        return countryRepository.findAll();
    }

    public Optional<Country> findByIso(String iso) {
        return countryRepository.findByIso(iso);
    }
}
