package co.com.callcenter.domain;

/**
 * @author MAURICIO
 *
 */
public class Call {
	
	private long id;
	private long duration;	
	private TypeEmployee responsibleEmployee;
	
	

	public long getDuracion() {
		return duration;
	}
	public TypeEmployee getResponsibleEmployee() {
		return responsibleEmployee;
	}

	public void setResponsibleEmployee(TypeEmployee responsibleEmployee) {
		this.responsibleEmployee = responsibleEmployee;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
