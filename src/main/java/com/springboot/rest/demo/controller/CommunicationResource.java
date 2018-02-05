package com.springboot.rest.demo.controller;

import com.springboot.rest.demo.model.Communication;
import com.springboot.rest.demo.repository.CommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class CommunicationResource {

    @Autowired
    private CommunicationRepository communicationRepository;

    @GetMapping("/communications")
    public List<Communication> retrieveAllCommunications() {
        return communicationRepository.findAll();
    }



    @DeleteMapping("/communications/{id}")
    public void deleteCommunication(@PathVariable long id) {
        communicationRepository.deleteById(id);
    }

    @PostMapping("/communications")
    public ResponseEntity<Object> createCommunication(@RequestBody Communication communication) {
        Communication savedCommunication = communicationRepository.save(communication);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCommunication.getEmail()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/communications/{id}")
    public ResponseEntity<Object> updateCommunication(@RequestBody Communication communication, @PathVariable long id) {

        Optional<Communication> communicationOptional = communicationRepository.findById(id);

        if (!communicationOptional.isPresent())
            return ResponseEntity.notFound().build();

        communication.setId(id);

        communicationRepository.save(communication);

        return ResponseEntity.noContent().build();
    }
}

