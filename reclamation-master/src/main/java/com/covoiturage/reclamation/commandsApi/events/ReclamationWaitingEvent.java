package com.covoiturage.reclamation.commandsApi.events;
import com.covoiturage.reclamation.commandsApi.enums.ReclamationStatus;

import lombok.Getter;

public class ReclamationWaitingEvent extends BaseEvent<String> {
    @Getter
    private ReclamationStatus status;

    public ReclamationWaitingEvent(String id, ReclamationStatus status) {
        super(id);
        this.status=status;
    }
}
