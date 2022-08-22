package com.skaypal.ebay_clone.domain.country.validator;

import com.skaypal.ebay_clone.domain.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryValidator {

    CountryRepository countryRepository;

    @Autowired
    public CountryValidator(CountryRepository countryRepository ){
        this.countryRepository = countryRepository;
    }

    public boolean countryExists(String country){
        return countryRepository.findByCountry(country).isPresent();
    }
}
