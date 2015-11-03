package com.trigital.Trigital;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.trigital.mongo.DataProcessor;

public class CmAnalyser {

	public static void main(String[] args) {

		final PropertyManager propertyManager = new PropertyManager();
		// Task to Run Goes Here
		final DataProcessor dataProcessor = new DataProcessor();
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("Process started");
				 File folder = new File(propertyManager.getProp("IN_FOLDER"));
		    	   File[] listOfFiles = folder.listFiles();
                   
		    	   for (int i = 0; i < listOfFiles.length; i++) {
		    	     File file = listOfFiles[i];
		    	     if (file.isFile() && file.getName().endsWith(".txt")) {
                         String status = dataProcessor.processFile(file);
                         if("OK".equalsIgnoreCase(status)){
                        	 file.renameTo(new File(propertyManager.getProp("OUT_FOLDER")+""+file.getName()));
                         }
		    	     } 
		    	   }
				System.out.println("Process Ended");
			}
		};

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable,0,new Long(propertyManager.getProp("TimeInterval")), TimeUnit.MILLISECONDS);
		
	}

	

}
