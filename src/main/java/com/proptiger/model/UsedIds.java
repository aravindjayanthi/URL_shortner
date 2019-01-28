package com.proptiger.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UsedIds", schema="urls")
public class UsedIds {
	
	@Id
	private int uId;


	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

}
