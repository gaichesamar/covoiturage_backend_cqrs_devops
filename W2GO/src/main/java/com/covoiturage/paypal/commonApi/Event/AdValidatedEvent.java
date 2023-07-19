package com.covoiturage.paypal.commonApi.Event;

import com.covoiturage.paypal.commonApi.Enum.AdStatus;
import lombok.Getter;

public class AdValidatedEvent extends baseEvent<String> {
    @Getter private AdStatus status;
    public AdValidatedEvent(String id, AdStatus status)
    {
        super(id);
        this.status=status;
    }
}

