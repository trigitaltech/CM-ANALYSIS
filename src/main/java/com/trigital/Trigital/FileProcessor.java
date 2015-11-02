package com.trigital.Trigital;

import java.io.File;
import java.io.OutputStream;
import java.util.Properties;

import com.trigital.mongo.DataProcessor;

public class FileProcessor implements Runnable{
	
	Properties prop = new Properties();
	OutputStream output = null;
	public void run() { 
	    try{
	    	DataProcessor dataProcessor = new DataProcessor();
	    
	    	 File folder = new File("C:\\kiran\\Mongo\\in\\");
	    	   File[] listOfFiles = folder.listFiles();

	    	   for (int i = 0; i < listOfFiles.length; i++) {
	    	     File file = listOfFiles[i];
	    	     if (file.isFile() && file.getName().endsWith(".txt")) {
	    	       System.out.println(file.getName());
	    	   String status =dataProcessor.processFile(file);
                        if("OK".equalsIgnoreCase(status)){
                        	file.renameTo(new File("C:\\kiran\\Mongo\\out\\"+file.getName()));
                        }
	    	     } 
	    	   }
	    	    
	    	   
	    	Thread.sleep(5000);
	    	}catch(InterruptedException e){
	    		System.out.println(e);
	    		}  
	  }  
	
	 public static void main(String args[]){  
		 FileProcessor t1=new FileProcessor();  
		   Thread thread = new Thread(t1);
		  thread.start();  
		 }  

}
