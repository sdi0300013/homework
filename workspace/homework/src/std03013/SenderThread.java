package std03013;

import java.util.concurrent.BlockingQueue;
import java.lang.Runnable;


public class SenderThread implements Runnable {
	
	public BlockingQueue<String> cQueue;
	public  static volatile boolean keepRunning=true;
	private String str="";
	
	public SenderThread(BlockingQueue<String> cQueue) {
	
	 this.cQueue=cQueue; }
	
	@Override	
	public void run(){	
		
		while (keepRunning && !cQueue.isEmpty()){                  
		 
		 
		 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		try {
			str=cQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str);}
	}
	
	
	
	
	
	
	
	
	
	
	

}
