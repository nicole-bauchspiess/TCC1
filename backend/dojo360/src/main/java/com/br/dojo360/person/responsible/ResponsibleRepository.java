package com.br.dojo360.person.responsible;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsibleRepository extends JpaRepository<ResponsibleEntity, UUID> {
}
