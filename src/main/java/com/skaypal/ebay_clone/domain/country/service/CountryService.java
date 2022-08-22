package com.skaypal.ebay_clone.domain.country.service;


import com.skaypal.ebay_clone.domain.country.exception.CountryNotFoundException;
import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CountryService {

    CountryRepository countryRepository;

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
}
