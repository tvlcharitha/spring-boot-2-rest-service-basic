package com.springboot.rest.demo.repository;

import com.springboot.rest.demo.model.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {
}
