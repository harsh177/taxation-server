package com.taxation.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taxation.service.impl.CleaningService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.time.LocalDateTime;

@Component
public class TaxationSchedular {
	@Autowired
	private	CleaningService	cleaningService;
	
	@Scheduled(fixedRate = 1000*60*10)
	public void call() {
		cleaningService.clearDanglingDocuments();
		//System.out.println("Schedular is running"); 
	}

	@Scheduled(fixedRate = 1000 *60*60*3)
	public void createBackUp(){
		String executeCmd = "mysqldump -u root --password=jaishreeram -B ptm -r D:/Backup_Taxation/Taxation_Backup_"+System.currentTimeMillis()+".sql";

			//System.out.println(executeCmd);

			Process runtimeProcess;

			try
			{
				runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });

				int processComplete = runtimeProcess.waitFor();

				System.out.println(processComplete);

				if(processComplete == 0)
				{
					System.out.println("Backup Created Successfully !");
				}
				else
				{
					System.out.println("Couldn't Create the backup !");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}

		
    }


}
