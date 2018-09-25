package com.taxation.resource;

import com.taxation.model.Document;

import java.util.List;

public class TransferPropertyRequest {
    private Integer propertyId;
    private String transferToSamagraId;
    private String newSubHolder;
    private String residentName;
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getTransferToSamagraId() {
        return transferToSamagraId;
    }

    public void setTransferToSamagraId(String transferToSamagraId) {
        this.transferToSamagraId = transferToSamagraId;
    }

    public String getNewSubHolder() {
        return newSubHolder;
    }

    public void setNewSubHolder(String newSubHolder) {
        this.newSubHolder = newSubHolder;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }
}
