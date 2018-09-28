package com.taxation.service.interfaces;

import com.taxation.model.SchedularAudit;

public interface ISchedularAuditService {

    public void createShcedularAuditRecord(SchedularAudit schedularAudit);

    public SchedularAudit getBySchedularName(String schedularName);

}
