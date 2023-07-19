package com.example.org.oga.commonapi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
@NoArgsConstructor
public abstract  class BaseEvent <T> {

    @TargetAggregateIdentifier
    @Getter
   private T id;

}
