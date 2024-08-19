package com.malu.confeitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malu.confeitaria.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
