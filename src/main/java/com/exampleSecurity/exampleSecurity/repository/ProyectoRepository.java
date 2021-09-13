package com.exampleSecurity.exampleSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleSecurity.exampleSecurity.entity.Proyecto;
import com.exampleSecurity.exampleSecurity.entity.Roll;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{

}
