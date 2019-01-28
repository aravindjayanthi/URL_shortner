package com.proptiger.util;

import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {
	
	
	
	public String indexTobase64(int index) {
		
		char[] b64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                	   'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
                	   'U', 'V', 'W', 'X', 'Y', 'Z', 
                	   'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                	   'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
                	   'u', 'v', 'w', 'x', 'y', 'z', 
                	   '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                	   '-', '_'};
		StringBuilder sb = new StringBuilder("AAAAAAAA");
		for(int i=7;i>=0;i--) {
			int tempRem = index%64;
			index/=64;
			sb.setCharAt(i, b64[tempRem]);
		}
		return sb.toString();
	}
	
	public int getIdFromShortUrl(String shortUrl) {
		int mul = 1;
		int id = 0;
		for(int i=7;i>=0;i--) {
			if(shortUrl.charAt(i)>='A' && shortUrl.charAt(i)<='Z') {
				id += mul*(shortUrl.charAt(i)-'A');
			}else if(shortUrl.charAt(i)>='a' && shortUrl.charAt(i)<='z') {
				id += mul*(26+shortUrl.charAt(i)-'a');
			}else {
				id += mul*((shortUrl.charAt(i)=='-')?62:63);
			}
			mul *= 64;
		}
		return id;
	}

}
