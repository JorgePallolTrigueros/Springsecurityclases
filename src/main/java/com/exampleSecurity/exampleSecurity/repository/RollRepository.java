package com.exampleSecurity.exampleSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exampleSecurity.exampleSecurity.entity.Direccion;
import com.exampleSecurity.exampleSecurity.entity.Roll;
@Repository
public interface RollRepository extends JpaRepository<Roll, Long>{

}
