package com.skaypal.ebay_clone.domain.country.repository;

import com.skaypal.ebay_clone.domain.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepositoryImpl implements CountryRepository {

    JPACountryRepository jpaCountryRepository;

    @Autowired
    public CountryRepositoryImpl(JPACountryRepository jpaCountryRepository){
        this.jpaCountryRepository = jpaCountryRepository;
    }

    public Optional<Country> findByCountry(String country){

        return jpaCountryRepository.findByCountry(country);
    }

    @Override
    public List<Country> findAll() {
        return jpaCountryRepository.findAll();
    }
}
