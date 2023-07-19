package com.example.CarMs.commonApi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
public abstract class BaseCommand<T>  {
    @TargetAggregateIdentifier
    @Getter
    private T id;
}
