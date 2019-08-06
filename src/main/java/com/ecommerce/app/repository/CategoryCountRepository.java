package com.ecommerce.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Catelog;
import com.ecommerce.app.entity.CatelogCount;

@Repository
public interface CategoryCountRepository extends JpaRepository<CatelogCount, Integer> {


	public Optional<CatelogCount> findByCatelog(Catelog catelog);
	
	
	
}
