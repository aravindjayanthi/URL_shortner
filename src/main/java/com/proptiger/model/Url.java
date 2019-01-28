package com.proptiger.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Url", schema="urls")
public class Url {
	
	
	@Id
	private int Id;
	
	@Column(length=30)
	private String shortUrl;
	
	@Column(length=2000)
	private String longUrl;
	
	@Column(name="usedAt", columnDefinition="DATE")
	private Date usedAt;
	
	@Column(length=512)
	private String hashOfLongUrl;
	
//	@Column
//	private Date expirationAt;

	public int getId() {
		return Id;
	}

	public String getHashOfLongUrl() {
		return hashOfLongUrl;
	}

	public void setHashOfLongUrl(String i) {
		this.hashOfLongUrl = i;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String string) {
		this.shortUrl = string;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	public Date getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(Date usedAt) {
		this.usedAt = usedAt;
	}
	
}
