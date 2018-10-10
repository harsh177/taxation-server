package com.taxation.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taxation.service.impl.CleaningService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

@Component
public class TaxationSchedular {
	@Autowired
	private	CleaningService	cleaningService;
	
	@Scheduled(fixedRate = 1000*60*10)
	public void call() {
		cleaningService.clearDanglingDocuments();
		//System.out.println("Schedular is running"); 
	}

	@Scheduled(fixedRate = 1000 *10)
	public void createBackUp(){
		String executeCmd = "mysqldump -u root -p jaishreeram -B ptm -r " + "/home/npc/MyProjects/taxation-client";

			System.out.println(executeCmd);

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


}
