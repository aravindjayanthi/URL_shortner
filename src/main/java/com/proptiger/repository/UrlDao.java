package com.proptiger.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.proptiger.model.Url;

public interface UrlDao extends JpaRepository<Url, Integer>{
	
	
	public Url findByHashOfLongUrl(String hashOfLongUrl); 
	
	@Query(value="select Id from urls.url where url.usedAt<=?", nativeQuery=true)
	public int findNextValidId(Date date);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO urls.url(id, shortUrl, longUrl, usedAt, hashOfLongUrl) VALUES(?1,0,0,?2,0) ON DUPLICATE KEY UPDATE usedAt=?2", nativeQuery=true)
	public void updateUsedAt(int id, Date usedAt);
	
	
	@Transactional
	@Modifying
	@Query(value="update urls.url set longUrl=?3, hashOfLongUrl=?2, usedAt=?4 where id=?1", nativeQuery=true)
	public void updateLongUrl(int id, String hash, String longUrl, Date date);
}
