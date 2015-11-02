package com.trigital.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;




public class DataProcessor {
	
	public String processFile(File file){
		BufferedReader br = null;	
	try {
		//telnetClient = new Socket("202.88.131.170",22);
	
		String sCurrentLine=null;
	//MongoClient mongoClient = new MongoClient("202.88.131.169",27017);

	//BufferedReader br = new BufferedReader(new FileReader("C:\\kiran\\Mongo\\cm\\cm.txt"));

	br = new BufferedReader(new FileReader(file));
	
	
	  MongoClient mongoClient = new MongoClient("localhost",27017);
    DB db = mongoClient.getDB("cmts_shcm");
    
   
  
  //  boolean auth = db.authenticate("admin", "password".toCharArray());
   //System.out.println("Authentication: "+auth);
    DBCollection coll = db.getCollection(file.getName());
    System.out.println("Collection created successfully"+coll);
/*//    BasicDBObject doc = new BasicDBObject("title", "MongoDB").
//            append("description", "database").
//            append("likes", 100).
//            append("url", "http://www.tutorialspoint.com/mongodb/").
//            append("by", "tutorials point");
*/          int linenum = 0;
    while ((sCurrentLine = br.readLine()) != null) {
		//System.out.println(sCurrentLine);
		final String[] currentLineData = sCurrentLine.split("\\s+");
		
		BasicDBObject documentDetail = new BasicDBObject();
		if(currentLineData.length > 8 && linenum > 3){
			documentDetail.put("Mac Address", currentLineData[0]);
			documentDetail.put("IP Address ", currentLineData[1]);
			documentDetail.put("I/F", currentLineData[2]);
			documentDetail.put("Mac State", currentLineData[3]);
			documentDetail.put("Prime Sid", currentLineData[4]);
			documentDetail.put("RxPwr", currentLineData[5]);
			documentDetail.put("Time Offset", currentLineData[6]);
			documentDetail.put("Num Cpe", currentLineData[7]);
			documentDetail.put("DIP", currentLineData[8]);
			coll.insert(documentDetail);
		}
		linenum++;
	}
    
         System.out.println("Document inserted successfully");
    
         return "Ok";
         
 
}
 catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Fail";
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "Fail";
	}catch (Exception e) {
         e.printStackTrace();
         return "Fail";
	}finally{
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	
}
