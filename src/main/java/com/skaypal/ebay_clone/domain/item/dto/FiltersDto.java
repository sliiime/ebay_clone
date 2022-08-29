package com.skaypal.ebay_clone.domain.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;

import java.util.Collection;
import java.util.List;

public class FiltersDto {

    @JsonProperty("filters")
    private Collection<Filter> filters;

    public FiltersDto(){}

    public void setFilters(Collection<Filter> filters){
        this.filters = filters;
    }

    public Collection<Filter> getFilters(){
        return filters;
    }
}
