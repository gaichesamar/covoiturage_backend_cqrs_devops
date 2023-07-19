package com.example.CarMs.commonApi.events;

import com.example.CarMs.commonApi.enums.CarStatus;
import lombok.Getter;

public class CarRemovedEvent extends BaseEvent<String>{
    @Getter private CarStatus status;


    public CarRemovedEvent(String id) {
    super(id);
    this.status=CarStatus.DELETED;

    }


}
