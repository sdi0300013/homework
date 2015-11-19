package gr.uoa.di;

import java.io.*;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import std03013.OneTimeThread;
import std03013.PeriodicThread;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import std03013.SenderThread;

public class Main {
static volatile boolean keepRunning = true;

public static void main(String[] args) {
	
	
BlockingQueue<String> bQueue=new LinkedBlockingQueue<String>();	
BlockingQueue<String> cQueue=new LinkedBlockingQueue<String>();	
ExecutorService executor=Executors.newFixedThreadPool(5);
ExecutorService service=Executors.newCachedThreadPool();
BufferedReader in=null;
Random ran=new Random();
String str="";
int randomInt,j;


Runtime.getRuntime().addShutdownHook(new Thread()               //shutdown if contol-c
{
    public void run()
    {
        keepRunning = false;
        OneTimeThread.keepRunning=false;
        PeriodicThread.keepRunning=false;
        SenderThread.keepRunning=false;
        try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
});



SenderThread s1=new SenderThread(cQueue);                //create SenderThread
for(int i=0;i<5;i++){                                    //create a threadpool
executor.submit(new OneTimeThread(bQueue,cQueue));}

try {
	in=new BufferedReader(new FileReader("nmap.txt"));    //read txt
} catch (FileNotFoundException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

while(keepRunning && !Thread.currentThread().isInterrupted()){    //loop until control-c
	if (str==null) {                               //if file finished start again
		try {
		    in=new BufferedReader(new FileReader("nmap.txt"));    
	     } catch (FileNotFoundException e1) {
		      // TODO Auto-generated catch block
		   e1.printStackTrace(); }			
	}
	randomInt=ran.nextInt(10)+1;                   //generate a random number 1-10
    j=0;
   try {
	while ((str=in.readLine())!=null && j<randomInt){                   //read lines,get parameters,put them in queue
		
		String[] parts=str.split(","); 
		if (parts[2].equals("false")){             //if false onetime thread else periodic
		try {
			bQueue.put(parts[1]);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		else {service.submit(new PeriodicThread(cQueue,parts[1],parts[3]));
		    } 
		s1.run();              //run senderthread
	}
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 try {
	Thread.sleep(2000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}


}}