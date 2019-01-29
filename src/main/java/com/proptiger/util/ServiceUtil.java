package com.proptiger.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {
	
	
	/*
	 * This function is used for converting
	 * Id into base64 short url string
	 */
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
	
	
	/*
	 * This function retrieves the id from short url
	 * which helps us to find the long url corresponding
	 * to the short url
	 */
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
	
	
	/*
	 * This function calculates the secure hash
	 * of the long url using MD5 algorithm which helps in 
	 * re-usability.
	 */
	public String md5(String longUrl) {
		
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(longUrl.getBytes());
			byte []h = m.digest();
			StringBuilder sb = new StringBuilder(2*h.length);
			for(byte b:h) {
				sb.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4));
			    sb.append("0123456789ABCDEF".charAt((b & 0x0F)));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}


}
