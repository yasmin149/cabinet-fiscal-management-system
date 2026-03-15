package com.cabinet.cabinet_fiscal_api.repository;

import com.cabinet.cabinet_fiscal_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}