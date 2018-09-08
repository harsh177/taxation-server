package com.taxation.resource;

public class FindByPhoneOrSamagraRequest {
    private String phoneNumber;
    private String samagraId;

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
