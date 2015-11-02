package com.trigital.Trigital;

import java.io.File;

public class MoveFileExample 
{
    public static void main(String[] args)
    {	
    	try{
    		
    	 /*  File afile =new File("D:\\Dust\\DustCopy.sql");
    		
    	   if(afile.renameTo(new File("D:\\Dust\\"+afile.getName()))){
    		System.out.println("File is moved successful!");
    	   }else{
    		System.out.println("File is failed to move!");
    	   }*/
    	   
    	   File folder = new File("C:\\kiran\\Mongo\\in");
    	   File[] listOfFiles = folder.listFiles();

    	   for (int i = 0; i < listOfFiles.length; i++) {
    	     File file = listOfFiles[i];
    	     if (file.isFile() && file.getName().endsWith(".sql")) {
    	       System.out.println(file.getName());
    	       /* do somthing with content */
    	     } 
    	   }
    	    
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}