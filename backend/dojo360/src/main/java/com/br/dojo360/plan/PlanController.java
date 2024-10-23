package com.br.dojo360.plan;

import com.br.dojo360.plan.dto.CreatePlan;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Inject
    PlanService planService;

    @PostMapping
    public ResponseEntity<CreatePlan> createStudent(@RequestBody CreatePlan newPlan) {

        var returnEntity = planService.createOrUpdatePlan(newPlan);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(returnEntity).toUri();
        return ResponseEntity.created(uri).body(returnEntity);
    }
}
