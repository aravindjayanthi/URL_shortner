package com.proptiger.controller;


import java.net.MalformedURLException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proptiger.dto.ResponseDto;
import com.proptiger.model.Report;
import com.proptiger.model.Url;
import com.proptiger.service.UrlService;

@Controller
public class UrlController {
	

	@Autowired
	private UrlService urlService;
		
	@RequestMapping(value = "/long", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto generateShortUrl(@RequestBody Url url) {
		return new ResponseDto(urlService.createUrl(url));
	}
	
	
	@RequestMapping(value = "/short", method = RequestMethod.GET)
	@ResponseBody
	//@Cacheable("longUrl")
	public String getLongUrl(@RequestParam("surl") String shortUrl) throws MalformedURLException {
		return urlService.getLongUrl(shortUrl);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	@ResponseBody
	public Report getNowReport() {
		return urlService.getTodayReport();
	}

	@RequestMapping(value = "/report/{date}", method = RequestMethod.GET)
	@ResponseBody
	public Report getParticularDateReport(@PathVariable Date date) {
		return urlService.getParticularDateReport(date);
	}

	@Scheduled(cron = "0 40 12 * * ?")
	public void emailDailyReportMail() {
		urlService.emailDailyReport();
	}

	@Scheduled(cron="0 25 12 * * ?")
	public void updateUsedAt() {
		urlService.updateUsedAt();
	}
	

}
