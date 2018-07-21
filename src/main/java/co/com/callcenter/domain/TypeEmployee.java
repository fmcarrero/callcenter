/**
 * 
 */
package co.com.callcenter.domain;

/**
 * @author MAURICIO
 *
 */
public abstract class TypeEmployee {
	
	
	private long id;
	private String name;
	private String type;	

	public String geType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name){
		this.name =name;
	}
	public String getName(){
		return this.name;
	}

	
}
