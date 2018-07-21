package co.com.callcenter.app;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

import co.com.callcenter.builder.EmployeeBuilder;
import co.com.callcenter.constant.ConcurrencyConstant;
import co.com.callcenter.domain.Call;
import co.com.callcenter.domain.TypeEmployee;

@RunWith(ConcurrentTestRunner.class)
public class DispatchCallTestzeroCallinHold {

	private Dispatcher dispatcher;	
	private void configDispatcher(int countDirectors, int countSupervisors, int countOperators){
		Dispatcher dispatcher = new Dispatcher();		
		List<TypeEmployee> employees= EmployeeBuilder.build(countDirectors,countSupervisors,countOperators);
		dispatcher.addEmployeeToListAvalaible(employees);		
		this.dispatcher = dispatcher;	
	}
	
	/**
	 * @author MAURICIO
	 * Test verifica las peticiones que no quedan peticiones encoladas
	 */
	@Test
	@ThreadCount(ConcurrencyConstant.COUNT_CALLS_CONCURRENT)	
	public void dispatchCallTestzeroCallinHold() {
		//arrange
		this.configDispatcher(1,2,7);
		ThreadCall threadCall = new ThreadCall(dispatcher, new Call(),2);
		//act
		threadCall.run();
		//assert
		assertTrue(dispatcher.getCountCallsInProgress() <= 9);
		assertEquals(0,dispatcher.getCallsOnHold().size());	
	}	
}
