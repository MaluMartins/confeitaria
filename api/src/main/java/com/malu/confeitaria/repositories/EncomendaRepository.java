package com.malu.confeitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malu.confeitaria.models.Encomenda;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

}
