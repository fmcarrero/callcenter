package co.com.callcenter.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.callcenter.domain.Call;

public class ThreadCall extends Thread {
	private final  Logger logger = Logger.getLogger("ThreadCall");
	private int time;
	private Call call;
	private Dispatcher dispatcher;
	
	public ThreadCall(Dispatcher dispatcher, Call call,int time) {
		
		this.time = time ;
		this.dispatcher = dispatcher;
		this.call = call;		
		this.call.setDuration(this.time);
	}
	
	@Override
	public void run() {
		
		try {			
			 getDispatcher().dispatchCall(call);
			 Thread.sleep(this.time);
			 getDispatcher().unDispatchCall(call);
						
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE,e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
		
	}
	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

}
