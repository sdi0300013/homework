package std03013;

import java.util.concurrent.BlockingQueue;
import java.io.IOException;
import java.lang.Runnable;



public class OneTimeThread implements Runnable {

public BlockingQueue<String> bQueue,cQueue;	
private String s;
public static volatile boolean keepRunning=true;

public OneTimeThread(BlockingQueue<String> bQueue,BlockingQueue<String> cQueue){	
	
	this.bQueue=bQueue; 
	this.cQueue=cQueue; 
     }
@Override	
public void run(){
	while (keepRunning==true){
	
	try {
		s=bQueue.take();                                  //take string from queue
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//String[] parts=s.split("-"); 
	//Runtime rt=Runtime.getRuntime();
	//try {
	//	Process proc=rt.exec("nmap",parts);
	//} catch (IOException e1) {
		// TODO Auto-generated catch block
	//	e1.printStackTrace();
	//}
	
	try {
		cQueue.put(s);                                    //put string into queue
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace(); }
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}}
}
