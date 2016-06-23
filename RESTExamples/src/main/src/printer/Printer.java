package printer;

public class Printer {
	private String id;
	private String location;
	
	public Printer(String id, String location) {
		super();
		this.id = id;
		this.location = location;
	}
	
	public Printer(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
