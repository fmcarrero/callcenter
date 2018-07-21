/**
 * 
 */
package co.com.callcenter.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import co.com.callcenter.constant.ConcurrencyConstant;
import co.com.callcenter.domain.Call;
import co.com.callcenter.domain.Operator;
import co.com.callcenter.domain.Supervisor;
import co.com.callcenter.domain.TypeEmployee;

/**
 * @author MAURICIO
 * is responsible for assigning calls to employees
 */
public class Dispatcher {
	
	private final static Logger LOGGER = Logger.getLogger(Dispatcher.class.getSimpleName());
	
	private int countCallsInProgress = 0;
	private Queue<Call> callsOnHold;
	
	private List<TypeEmployee> operatorsAvalaible;
	private List<TypeEmployee> supervisoresAvalaible;
	private List<TypeEmployee> directoresAvalaible;
	
	/**
	 * Instantiates a new dispatcher.
	 */
	public Dispatcher() {		
		 operatorsAvalaible = new ArrayList<TypeEmployee>();
		 supervisoresAvalaible = new ArrayList<TypeEmployee>();
		 directoresAvalaible = new ArrayList<TypeEmployee>();
		 setCallsOnHold(new LinkedList<Call>());		

	}
	/**
	 * Sets calls in stage wait
	 *
	 * @param callsOnHold the new calls in  hold
	 */
	public void setCallsOnHold(Queue<Call> callsOnHold) {
		this.callsOnHold = callsOnHold;
	}
	/**
	 * Sets the count call in progress
	 *
	 * @param countCallsInProgress the new count calls in progress
	 */
	public void setCountCallsInProgress(int countCallsInProgress) {
		this.countCallsInProgress = countCallsInProgress;
	}
	public int getCountCallsInProgress() {
		return countCallsInProgress;
	}
	/**
	 * @author MAURICIO
	 *
	 * add call to employee
	 * 
	 * @param call	 	
	 * @throws InterruptedException 
	 */
	public synchronized void dispatchCall(Call call) throws InterruptedException  {
		
		TypeEmployee freeEmployee = getEmployeeAvalaible();
		if (getCountCallsInProgress() >= ConcurrencyConstant.COUNT_CALLS_CONCURRENT || freeEmployee == null ) {
			glue(call);
			wait();
			freeEmployee = getEmployeeAvalaible();
		}
		assignEmployeeToCall( freeEmployee,call);		
	}
	/**
	 * @author MAURICIO
	 * view employee avalaible
	 *
	 * @return the firts employee  avalaible
	 */
	private TypeEmployee getEmployeeAvalaible() {
		if (!operatorsAvalaible.isEmpty()) {
			return operatorsAvalaible.get(0);
		}
		if (!supervisoresAvalaible.isEmpty()) {
			return supervisoresAvalaible.get(0);
		}
		if (!directoresAvalaible.isEmpty()) {
			return directoresAvalaible.get(0);
		}
		return null;
	}
	/**
	 * @author MAURICIO
	 * 
	 * add call to queue
	 *
	 * @param call
	 */
	private void glue(Call call) {
		getCallsOnHold().add(call);
	}
	public Queue<Call> getCallsOnHold() {
		return callsOnHold;
	}
	/**
	 * @author MAURICIO
	 * assign Employee To Call
	 *
	 * @param Call call
	 * @param TypeEmployee freeEmployee	 
	 */
	private void assignEmployeeToCall(TypeEmployee freeEmployee,Call call)  {		
		call.setResponsibleEmployee(freeEmployee);
		occupyEmployee(freeEmployee);		
		setCountCallsInProgress(getCountCallsInProgress() + 1);		
	}
	
	private void occupyEmployee(TypeEmployee freeEmployee)  {
		if (freeEmployee instanceof Operator) {
			operatorsAvalaible.remove(freeEmployee);
		} else if (freeEmployee instanceof Supervisor) {
			supervisoresAvalaible.remove(freeEmployee);
		} else {
			directoresAvalaible.remove(freeEmployee);
		}
	}
	
	/**
	 * @author MAURICIO
	 * method finish call 
	 *
	 * @param Call call	
	 */
	public synchronized void unDispatchCall(Call call)  {
		vacateEmployee(call);
		notify();
	}
	
	private void vacateEmployee(Call call) {		
		addEmployeeToListAvalaible(call.getResponsibleEmployee());		
		call.setResponsibleEmployee(null); 		
		setCountCallsInProgress(getCountCallsInProgress() - 1);	
		LOGGER.info("call in progress " + this.getCountCallsInProgress());
	}
	public void addEmployeeToListAvalaible(TypeEmployee typeEmployee) {		
		this.detectedInstanceTypeEmployee(typeEmployee);
	}
	public void addEmployeeToListAvalaible(List<TypeEmployee> typeEmployees) {		
		for (TypeEmployee typeEmployee : typeEmployees) {
			this.detectedInstanceTypeEmployee(typeEmployee);
		}		
	}
	private void detectedInstanceTypeEmployee(TypeEmployee typeEmployee){
		if (typeEmployee instanceof Operator) {			
			operatorsAvalaible.add(typeEmployee);
		} else if (typeEmployee instanceof Supervisor) {
			supervisoresAvalaible.add(typeEmployee);
		} else {			
			directoresAvalaible.add(typeEmployee);
		}
	}
}
