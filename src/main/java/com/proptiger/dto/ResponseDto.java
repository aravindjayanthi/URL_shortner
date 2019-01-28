package com.proptiger.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -463733764905907763L;
	private String url;

	public ResponseDto(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
