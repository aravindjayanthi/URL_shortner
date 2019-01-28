package com.proptiger.service;


import java.sql.Date;
import org.springframework.stereotype.Service;
import com.proptiger.model.Report;
import com.proptiger.model.Url;

@Service
public interface UrlService {
	
	
	public String createUrl(Url url);

	public String getLongUrl(String shortUrl);
		
	public Report getTodayReport();
	
	public Report getParticularDateReport(Date date);
	
	public void emailDailyReport();
	
	public void updateUsedAt();

}
