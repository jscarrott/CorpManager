package main;

import gui.GUImain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.WatchService;
import javax.xml.bind.JAXBException;


public class watchDir extends Thread{

	
	private WatchService watcher;
	private CoordinatingClass coord;
	private long lastTimeBuff;

	public watchDir(CoordinatingClass coordinatingClass) throws IOException, JAXBException, InterruptedException  {
		this.coord = coordinatingClass;
		
	}

	public void run(){
		
		File buffPathFile = new File("data.xml");
		lastTimeBuff = buffPathFile.lastModified();
	      while(true){
	    	  if(lastTimeBuff != buffPathFile.lastModified()){
	    		  try {
					coord.readFromXmlFile();
					System.out.println("poll file");
					lastTimeBuff = buffPathFile.lastModified();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	  }
	    	  
	    	  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }

	       
	       
	}
}
