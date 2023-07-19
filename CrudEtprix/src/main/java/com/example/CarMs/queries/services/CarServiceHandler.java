package com.example.CarMs.queries.services;
import com.example.CarMs.commonApi.events.CarAddedEvent;
import com.example.CarMs.commonApi.events.CarRemovedEvent;
import com.example.CarMs.commonApi.events.CarUpdatedEvent;
import com.example.CarMs.commonApi.query.GetAllCarsQuery;
import com.example.CarMs.commonApi.query.GetCarByIdQuery;
import com.example.CarMs.queries.entities.Car;
import com.example.CarMs.queries.repositories.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class CarServiceHandler {
    private CarRepository carRepository;
    @EventHandler
    public void on(CarAddedEvent event){
        Car car = new Car();
        car.setId(event.getId());
        car.setCouleur(event.getCouleur());
        car.setModele(event.getModele());
        car.setMarque(event.getMarque());
        car.setType(event.getType());
        car.setImmatriculation(event.getImmatriculation());
        car.setNbrPlace(event.getNbrPlace());
        car.setStatus(event.getStatus());
        carRepository.save(car);
    }
    
@EventHandler
public void on(CarUpdatedEvent event)
{

    Car car = carRepository.getById(event.getId());
    car.setMarque(event.getMarque());
    car.setModele(event.getModele());
    car.setImmatriculation(event.getImmatriculation());
    car.setCouleur(event.getCouleur());
    car.setNbrPlace(event.getNbrPlace());
    car.setType(event.getType());
    carRepository.save(car);
}
    @EventHandler
    public void on(CarRemovedEvent event) {
        try {
            carRepository.deleteById(event.getId());
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Car not found with id: " + event.getId());
        }    }

    @QueryHandler
    public List<Car> on (GetAllCarsQuery query) {
        return carRepository.findAll();
    }
    @QueryHandler
    public Car on(GetCarByIdQuery query) {
        return carRepository.findById(query.getId()).get();
    }

    public List<Car> getAdsByUserId(String modele) {
        return carRepository.findByModele(modele);
    }
}
