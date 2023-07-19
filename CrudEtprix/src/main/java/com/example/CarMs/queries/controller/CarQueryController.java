package com.example.CarMs.queries.controller;

import com.example.CarMs.commonApi.query.GetAllCarsQuery;
import com.example.CarMs.commonApi.query.GetCarByIdQuery;
import com.example.CarMs.queries.entities.Car;
import com.example.CarMs.queries.services.CarServiceHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/query/cars")
@CrossOrigin(origins = "*")
@Slf4j

public class CarQueryController {
    private QueryGateway queryGateway;

    private CarServiceHandler adServicer;

    @GetMapping("/GetAllCars")
    public List<Car> GetAllCars() {
        List<Car> reponse=    queryGateway.query(new GetAllCarsQuery(), ResponseTypes.multipleInstancesOf(Car.class)).join();
        return reponse;
    }
    @GetMapping("/GetCarById/{id}")
    public Car GetCarById(@PathVariable String id){
        return  queryGateway.query(new GetCarByIdQuery(id),ResponseTypes.instanceOf(Car.class)).join();
    }
    @GetMapping("/user/{modele}")
    public List<Car> getAdsByUserId(@PathVariable String modele) {
        return  adServicer.getAdsByUserId(modele);
    }



}
