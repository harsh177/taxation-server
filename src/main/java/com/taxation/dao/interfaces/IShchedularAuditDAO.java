package com.taxation.dao.interfaces;

import com.taxation.model.SchedularAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IShchedularAuditDAO extends JpaRepository<SchedularAudit,Integer> {


    @Query("select sa from SchedularAudit sa where sa.nameOfSchedular = ?1")
    SchedularAudit getBySchedularName(String nameOfSchedular);
}
