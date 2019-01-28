package com.proptiger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proptiger.model.UsedIds;

public interface UsedIdsDao extends JpaRepository<UsedIds, Integer>{
	
}
