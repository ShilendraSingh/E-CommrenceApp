package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Catelog;

@Repository
public interface CatelogRepository extends JpaRepository<Catelog, Integer> {

}
