/**
 * 
 */
package co.com.callcenter;

import java.util.List;
import java.util.Random;
import co.com.callcenter.app.Dispatcher;
import co.com.callcenter.app.ThreadCall;
import co.com.callcenter.builder.EmployeeBuilder;
import co.com.callcenter.constant.ConcurrencyConstant;
import co.com.callcenter.domain.Call;
import co.com.callcenter.domain.TypeEmployee;

/**
 * @author MAURICIO
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Dispatcher dispatcher = buildDispatcher();
		ThreadCall taskOne = new ThreadCall( dispatcher, new Call(),calculateTimeThread() );
		ThreadCall taskTwo = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskThree = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskFour = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskFive = new ThreadCall(dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskSix = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskSeven = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskEight = new ThreadCall(dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskNine = new ThreadCall( dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskTen = new ThreadCall(dispatcher, new Call(),calculateTimeThread());
		ThreadCall taskEleven = new ThreadCall( dispatcher, new Call(),calculateTimeThread());	
		
		taskOne.start();
		taskTwo.start();
		taskThree.start();
		taskFour.start();
		taskFive.start();
		taskSix.start();		
		taskSeven.start();
		taskEight.start();
		taskNine.start();
		taskTen.start();
		taskEleven.start();
	}
	public static Dispatcher buildDispatcher() {
		Dispatcher dispatcher = new Dispatcher();		
		List<TypeEmployee> employees= EmployeeBuilder.build(1,2,8);
		dispatcher.addEmployeeToListAvalaible(employees);		
		return dispatcher;
	}
	public static int calculateTimeThread() {
		return  (new Random().nextInt(10 - 5 + 1) + 5) * ConcurrencyConstant.MILLI_CALLS_CONCURRENT;		
	}

}
