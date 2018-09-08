package com.taxation.controller;

import com.taxation.common.ApplicationConstants;
import com.taxation.common.URLConstants;
import com.taxation.entity.ApplicationResponse;
import com.taxation.model.Person;
import com.taxation.service.interfaces.IPersonService;
import com.taxation.service.interfaces.ISeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class SeedController {


    @Autowired
    @Qualifier("seedService")
    private ISeedService seedService;

    @RequestMapping(value = URLConstants.SEED_ALL, method = RequestMethod.GET, produces = ApplicationConstants.APP_JSON)
    public ResponseEntity<ApplicationResponse> seedAll() {
        return new ResponseEntity<ApplicationResponse>(new ApplicationResponse(seedService.seedAll(),true,"Default Data Feeded"), HttpStatus.OK);
    }
}
