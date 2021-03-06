package com.proptiger.service;


import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.proptiger.model.Report;
import com.proptiger.model.Url;
import com.proptiger.model.UsedIds;
import com.proptiger.repository.ReportDao;
import com.proptiger.repository.UrlDao;
import com.proptiger.repository.UsedIdsDao;
import com.proptiger.util.ServiceUtil;


@Service
public class UrlServiceImp implements UrlService{
	
	@Autowired
	private UrlDao urlDao;
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private UsedIdsDao usedIdsDao;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private ServiceUtil serviceUtil;
	private int maxLinksStored = 7;
	
	
	public String createUrl(Url url) {
		
		String hash = serviceUtil.md5(url.getLongUrl());
		Url tempUrl = urlDao.findByHashOfLongUrl(hash);
		if(tempUrl!=null) {
			incUsedService();
			updateUsedIds(tempUrl.getId());
			return tempUrl.getShortUrl();
		}
		
		int nextId = (int)urlDao.count();
		if(nextId>=maxLinksStored) {
			nextId = getNextValidId();
			applicationContext.getBean(UrlService.class).updateLongUrl(nextId, hash, url.getLongUrl());
			return "https://mk.com/"+serviceUtil.indexTobase64(nextId);
		}
		
		url.setId(nextId);
		url.setHashOfLongUrl(hash);	
		url.setUsedAt(Date.valueOf(LocalDate.now()));	
		url.setShortUrl("https://mk.com/"+serviceUtil.indexTobase64(url.getId()));
		incCreateService();
		return urlDao.save(url).getShortUrl();
	}
	
	public String getLongUrl(String shortUrl) {
		
		//return "test";
		String []tempArr = shortUrl.split("/");
		
		if(!tempArr[tempArr.length-2].equals("mk.com"))
			return "invalid url!!";
		
		int id = serviceUtil.getIdFromShortUrl(tempArr[tempArr.length-1]);
		
		incUsedService();
		updateUsedIds(id);
		
		//return urlDao.findOne(id).getLongUrl();
		return applicationContext.getBean(UrlService.class).getLongUrlString(id);
	}
	
	public Report getTodayReport() {
		return reportDao.findOne(Date.valueOf(LocalDate.now()));
	}
	
	public Report getParticularDateReport(Date date) {
		return reportDao.findOne(date);
	}
	
	public void emailDailyReport() {
		Report tempReport = reportDao.findOne(Date.valueOf(LocalDate.now()));
		System.out.println("Date: "+tempReport.getDate());
		System.out.println("servicesCreated: "+tempReport.getServicesCreated());
		System.out.println("servicesUsed: "+tempReport.getServicesUsed());
		System.out.println("servicesRemoved: "+tempReport.getServicesRemoved());
	}
	
	public void updateUsedAt() {
		Iterable<UsedIds> arr = usedIdsDao.findAll();
		for(UsedIds u : arr)
			updateDateInUrl(u);
		usedIdsDao.deleteAll();
	}
	
	// Helper Function
	@Async
	private void incCreateService() {
		reportDao.incservicesCreated(Date.valueOf(LocalDate.now()));
	}
	
	@Async
	private void incUsedService() {
		reportDao.incservicesUsed(Date.valueOf(LocalDate.now()));
	}
	
	private int getNextValidId() {
		return urlDao.findNextValidId(Date.valueOf(LocalDate.now().minusDays(3)));
	}
	
	@Async
	private void updateUsedIds(int id) {
		
		if(usedIdsDao.exists(id))
			return ;
		UsedIds tempUid = new UsedIds();
		tempUid.setuId(id);
		usedIdsDao.save(tempUid);
	}

	private void updateDateInUrl(UsedIds uid) {
		urlDao.updateUsedAt(uid.getuId(), Date.valueOf(LocalDate.now()));
	}
	
	//Caching functions
	@Cacheable("longUrl")
	public String getLongUrlString(int id) {
		return urlDao.findOne(id).getLongUrl();
	}

	@CachePut(value="longUrl", key="#id")
	public String updateLongUrl(int id, String hash, String longUrl) {
		urlDao.updateLongUrl(id, hash, longUrl, Date.valueOf(LocalDate.now()));
		return longUrl;
	}

}
