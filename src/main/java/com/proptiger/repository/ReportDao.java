package com.proptiger.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.proptiger.model.Report;

public interface ReportDao extends JpaRepository<Report, Date>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO urls.report(date, servicesCreated, servicesUsed, servicesRemoved) VALUES(?,1,0,0) ON DUPLICATE KEY UPDATE servicesCreated=servicesCreated+1", nativeQuery=true)
	public void incservicesCreated(Date date);
	
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO urls.report(date, servicesCreated, servicesUsed, servicesRemoved) VALUES(?,0,1,0) ON DUPLICATE KEY UPDATE servicesUsed=servicesUsed+1", nativeQuery=true)
	public void incservicesUsed(Date date);
	
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO urls.report(date, servicesCreated, servicesUsed, servicesRemoved) VALUES(?,0,0,1) ON DUPLICATE KEY UPDATE servicesRemoved=servicesRemoved+1", nativeQuery=true)
	public void incservicesRemoved(Date date);
}
