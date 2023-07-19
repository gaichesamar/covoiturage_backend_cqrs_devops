package com.covoiturage.reclamation.commandsApi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent<T>{
    @Getter private  T id;
}
