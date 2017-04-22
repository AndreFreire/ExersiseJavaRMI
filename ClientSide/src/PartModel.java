public class PartModel {	
	int id;
	String name;
	String description;
	boolean isPrimitive;
	
	public PartModel(int id, String name, String description, boolean isPrimitive) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isPrimitive = isPrimitive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public void setPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
	}	
	
	@Override
	public String toString(){		
		return 	"\nId: "+ this.id +
				"\nName: "+ this.name +
				"\nDescription: "+this.description +
				"\nPrimitive"+ this.isPrimitive;
		
	}
}
