package com.taxation.entity;

import com.taxation.model.Panchayat;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private String name = "";
	private String username = "";
	private Long uid;
	private Panchayat panchayat;

	public JwtAuthenticationResponse(String accessToken, String name, String username, Long uid,
			Panchayat panchayat) {
		this.accessToken = accessToken;
		this.name = name;
		this.username = username;
		this.uid = uid;
		this.panchayat = panchayat;
	}

	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
