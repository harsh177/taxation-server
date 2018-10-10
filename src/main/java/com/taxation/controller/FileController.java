package com.taxation.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taxation.common.URLConstants;
import com.taxation.entity.UploadFileResponse;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;
import com.taxation.security.CurrentUser;
import com.taxation.security.UserPrincipal;
import com.taxation.service.impl.FileStorageService;
import com.taxation.service.impl.ReportService;
import com.taxation.service.interfaces.IPropertyService;
import com.taxation.service.interfaces.ITaxDetailsService;

@RestController
@RequestMapping(URLConstants.TAXATION_API)
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private	ITaxDetailsService	iTaxDetailsService;
    
    @Autowired
    private	ReportService	reportService;
    
	@Autowired
	@Qualifier("propertyService")
	private IPropertyService propertyService;// If you are using interface and it has more then one implementation

	
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@CurrentUser UserPrincipal currentUser) {
    	System.out.println(currentUser);
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/downloadFile/report/{all}")
    public ResponseEntity<Resource> allPaidAndDueTaxDetails(@PathVariable	String	all,@RequestParam	String	area,HttpServletRequest request) throws Exception {
    	List<TaxDetail>	taxDetails=null;
    	List<Property>	properties=null;
    	Resource resource =null;
    	
    	if(all.equals("PAID")){
    		taxDetails=	iTaxDetailsService.getAllPaidTaxDetails();
    		 resource = this.reportService.getReport(this.reportService.createAllPaidAndDueInfo(taxDetails, all));
    	}else	if(all.equals("DUE")){
    		taxDetails=	iTaxDetailsService.getAllDueTaxDetails();
    		 resource = this.reportService.getReport(this.reportService.createAllPaidAndDueInfo(taxDetails, all));
    	}else{
    		if(area.equalsIgnoreCase("--Select--")){
    			properties=propertyService.getAllActiveProperties();
    		}else{
    			properties=propertyService.getAllActivePropertiesBasedOnArea(area);
    		}
    		resource = this.reportService.getPropertyReport(this.reportService.createPropertyInfo(properties));
    	}
        
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
}