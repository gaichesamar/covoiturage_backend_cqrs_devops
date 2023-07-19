package com.example.CarMs.commonApi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public abstract class BaseEvent<T>{

    @Getter private  T id;
}
