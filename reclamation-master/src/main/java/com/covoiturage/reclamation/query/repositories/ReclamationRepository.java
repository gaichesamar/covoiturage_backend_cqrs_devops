package com.covoiturage.reclamation.query.repositories;

import com.covoiturage.reclamation.query.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository  extends JpaRepository<Reclamation,String> {
    List<Reclamation> findByIdUser(String userId);

}
