package com.cabinet.cabinet_fiscal_api.repository;

import com.cabinet.cabinet_fiscal_api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByMatricule(String matricule);

}