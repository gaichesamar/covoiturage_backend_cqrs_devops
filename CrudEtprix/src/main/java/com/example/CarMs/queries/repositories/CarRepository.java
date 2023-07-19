package com.example.CarMs.queries.repositories;

import com.example.CarMs.queries.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,String>{
    List<Car> findByModele(String modele);

}