package com.covoiturage.reclamation.query.controllers;

import com.covoiturage.reclamation.commandsApi.queries.GetAllReclamationQuery;
import com.covoiturage.reclamation.commandsApi.queries.GetReclamationByIdQuery;
import com.covoiturage.reclamation.query.entities.Reclamation;
import com.covoiturage.reclamation.query.service.ReclamationServiceHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/query/reclamation")
@AllArgsConstructor
@Slf4j
public class ReclamationController {
    private QueryGateway queryGateway;
    private ReclamationServiceHandler adServicer;

    @GetMapping("/allReclamation")
    public List<Reclamation> ReclamationList() {
        List<Reclamation> reponse=    queryGateway.query(new GetAllReclamationQuery(), ResponseTypes.multipleInstancesOf(Reclamation.class)).join();
        return reponse;
    }
    @GetMapping("/{id}")
    public Reclamation getReclamation(@PathVariable String id){
        return  queryGateway.query(new GetReclamationByIdQuery(id), ResponseTypes.instanceOf(Reclamation.class)).join();

    }



}