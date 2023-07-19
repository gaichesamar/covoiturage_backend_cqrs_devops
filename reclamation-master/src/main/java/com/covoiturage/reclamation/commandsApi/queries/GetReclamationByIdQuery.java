package com.covoiturage.reclamation.commandsApi.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetReclamationByIdQuery {
    private String id;
}
