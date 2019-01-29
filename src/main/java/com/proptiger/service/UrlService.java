package com.proptiger.service;


import java.sql.Date;

import com.proptiger.model.Report;
import com.proptiger.model.Url;

public interface UrlService {
	
	
	public String createUrl(Url url);
	
	public String getLongUrlString(int id);

	public String getLongUrl(String shortUrl);
		
	public Report getTodayReport();
	
	public Report getParticularDateReport(Date date);
	
	public void emailDailyReport();
	
	public void updateUsedAt();
	
	public String updateLongUrl(int id, String hash, String longUrl);

}
