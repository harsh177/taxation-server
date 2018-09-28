package com.taxation.service.impl;

import com.taxation.dao.interfaces.IShchedularAuditDAO;
import com.taxation.model.SchedularAudit;
import com.taxation.service.interfaces.ISchedularAuditService;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedularAuditService implements ISchedularAuditService {

    @Autowired
    IShchedularAuditDAO iShchedularAuditDAO;

    @Override
    public void createShcedularAuditRecord(SchedularAudit schedularAudit) {
        iShchedularAuditDAO.save(schedularAudit);
    }

    @Override
    public SchedularAudit getBySchedularName(String schedularName) {
        return iShchedularAuditDAO.getBySchedularName(schedularName);
    }
}
