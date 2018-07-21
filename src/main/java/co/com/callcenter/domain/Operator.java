/**
 * 
 */
package co.com.callcenter.domain;

/**
 * @author MAURICIO
 *
 */
public class Operator extends TypeEmployee {
	public Operator(String name) {
		super();
		this.setName(name);
		this.setType(this.getClass().getSimpleName());
	}

}
