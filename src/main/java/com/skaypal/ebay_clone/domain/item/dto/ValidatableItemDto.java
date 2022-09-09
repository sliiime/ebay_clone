package com.skaypal.ebay_clone.domain.item.dto;

import java.util.Date;

public interface ValidatableItemDto {

    public Float getMinBid();
    public Float getBuyPrice();

    public Date getStartDate();
    public Date getEndDate();
}
