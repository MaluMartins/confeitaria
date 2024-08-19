package com.malu.confeitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malu.confeitaria.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
