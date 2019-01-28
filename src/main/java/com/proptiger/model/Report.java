package com.proptiger.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="report", schema="urls")
public class Report {
	
	@Id
	@Column(name="date", columnDefinition="DATE")
	private Date date;
	
	@Column
	private int servicesCreated;
	
	@Column
	private int servicesUsed;
	
	@Column
	private int servicesRemoved;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getServicesCreated() {
		return servicesCreated;
	}

	public void setServicesCreated(int servicesCreated) {
		this.servicesCreated = servicesCreated;
	}

	public int getServicesUsed() {
		return servicesUsed;
	}

	public void setServicesUsed(int servicesUsed) {
		this.servicesUsed = servicesUsed;
	}

	public int getServicesRemoved() {
		return servicesRemoved;
	}

	public void setServicesRemoved(int servicesRemoved) {
		this.servicesRemoved = servicesRemoved;
	}
	
}
