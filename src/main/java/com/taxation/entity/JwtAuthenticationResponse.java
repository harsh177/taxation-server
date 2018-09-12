package com.taxation.entity;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String name = "";
    private String username = "";

    public JwtAuthenticationResponse(String accessToken,String name,String username) {
        this.accessToken = accessToken;
        this.name = name;
        this.username = username;
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
