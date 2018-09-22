package com.taxation.resource;

public class FindByPhoneOrSamagraOrUniqueRequest {
    private String phoneNumber;
    private String samagraId;
    private	String	customUniqueId;
    

    public String getCustomUniqueId() {
		return customUniqueId;
	}

	public void setCustomUniqueId(String customUniqueId) {
		this.customUniqueId = customUniqueId;
	}

	public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSamagraId() {
        return samagraId;
    }

    public void setSamagraId(String samagraId) {
        this.samagraId = samagraId;
    }
}
