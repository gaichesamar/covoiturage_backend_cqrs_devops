package com.example.org.oga.commonapi.events;

import com.example.org.oga.commonapi.enums.ReservationStatus;
import lombok.Getter;


public class CancelResrvationEvent extends BaseEvent <String>{
    @Getter private ReservationStatus status;

    public CancelResrvationEvent(String id) {
        super(id);
        this.status = ReservationStatus.CANCELED;
    }


  public static class getId {
  }
}
