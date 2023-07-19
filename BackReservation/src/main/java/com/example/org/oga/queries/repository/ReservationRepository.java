package com.example.org.oga.queries.repository;

import com.example.org.oga.queries.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation,String> {
    List<Reservation> findByidConsommateur(String id);
    List<Reservation> findByidAnnonce(String id);


}
