package com.taxation.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IDocumentDAO;
import com.taxation.model.Document;

@Service
public class CleaningService {
	
	@Autowired
	private	IDocumentDAO	iDocumentDAO;
	
    public	void	clearDanglingDocuments(){
    	List<String> results = new ArrayList<String>();

    	File[] files = new File("./uploads").listFiles();
    	//If this pathname does not denote a directory, then listFiles() returns null. 
    	for (File file : files) {
    	    if (file.isFile()) {
    	        results.add(file.getName());
    	    }
    	}
    	
    	List<String>	diffList=new	ArrayList<>();
    	
    	List<Document>	docList=iDocumentDAO.findAll();
    	
    	boolean isfound = false;
        
        for(int i = 0; i < results.size(); i++) 
        { 
            isfound = false;
             
            for(int j = 0; j < docList.size(); j++) 
            { 
                if(results.get(i).equalsIgnoreCase(docList.get(j).getName()))
                {
                    isfound = true;
                }
         
            } 
            if(!isfound) 
            { 
            	diffList.add(results.get(i));
            }
           
        }
        System.out.println(diffList);
        
        diffList.forEach(obj->{
        	File	f=new	File("./uploads/"+obj);
        	f.delete();
        });
    }
}
