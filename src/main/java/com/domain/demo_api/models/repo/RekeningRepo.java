package com.domain.demo_api.models.repo;

import com.domain.demo_api.models.entities.Rekening;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepo extends JpaRepository<Rekening, Integer> {

}
