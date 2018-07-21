package co.com.callcenter.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.com.callcenter.domain.Operator;
import co.com.callcenter.domain.Supervisor;
import co.com.callcenter.domain.TypeEmployee;

public class EmployeeBuilder {
	
	protected EmployeeBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	static String[] names = {"n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "n10","n11", "n12", "n13", "n14", "n15"};
	static String[] lastnames = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10","a11", "a12", "a13", "a14", "a15"};
	
	/**
	 * @author MAURICIO
	 * generate list employes
	 *
	 * @param countDirectors	 	
	 * @param countSupervisors	
	 * @param countOperators	
	 * @return list employees generates
	 */
	public static List<TypeEmployee> build(int countDirectors, int countSupervisors, int countOperators) {
		
		
		List<TypeEmployee> employees = new ArrayList<TypeEmployee>();
		for(int i=0;i< countOperators ;i++){
			String name =getName();
			employees.add(new Operator(name));
		}
		for(int i=0;i<countSupervisors ;i++){
			String name =getName();
			employees.add(new Supervisor(name ));
		}
		for(int i=0;i<countDirectors ;i++){
			String name =getName();
			employees.add(new Supervisor(name ));
		}
		return employees;
	}
	private static String getName (){
		String name =names[new Random().nextInt(names.length)];
		String lastname =lastnames[new Random().nextInt(lastnames.length)];
		StringBuilder builder = new StringBuilder(name);
		builder.append(" ");
		builder.append(lastname);
		return builder.toString();
	}
}
