package com.taxation.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxation.model.Document;

@Repository
public interface IDocumentDAO extends JpaRepository<Document,Long>   {

}
