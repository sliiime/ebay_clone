package com.skaypal.ebay_clone.utils.geo;


import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.recurse.geocoding.reverse.ReverseGeocoder;

@Service
public class GeoUtils {


    public static String latLongToISO(Double latitude,Double longitude){
        return locateCountry(latitude,longitude);

    }

    public static String latLongToISO(LatLongMapped location){
        return locateCountry(location.getLatitude(), location.getLongitude());
    }

    private static String locateCountry(Double latitude,Double longitude){

        ReverseGeocoder geocoder = new ReverseGeocoder();

        var country = geocoder.getCountry(latitude,longitude);

        String iso = country.map((c)-> c.iso()).orElse(null);

        return iso;
    }
}
