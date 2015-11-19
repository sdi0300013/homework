package std03013;

import java.util.concurrent.BlockingQueue;
import java.io.IOException;
import java.lang.Runnable;

public class PeriodicThread implements Runnable{

public BlockingQueue<String> cQueue;	
private String s;
private int time;
public  static volatile boolean keepRunning=true;

public PeriodicThread(BlockingQueue<String> cQueue,String s,String time){	
	
	 
	this.cQueue=cQueue; 
    this.s=s;
    this.time=Integer.parseInt(time);
     }
@Override	
public void run(){	
	
	while (keepRunning){
		
	  //String[] parts=s.split("-"); 
	 // Runtime rt=Runtime.getRuntime();
	  //try {
		//Process proc=rt.exec("nmap",parts);
		//} catch (IOException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
	  try {
		cQueue.put(s);
	   } catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	  }
	
	  try {
		Thread.sleep(time*10000);
	   } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }}

}






}
