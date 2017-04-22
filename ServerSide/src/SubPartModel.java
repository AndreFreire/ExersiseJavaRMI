
public class SubPartModel{
	int id;
	String name;
	String description;
	int quantity;
	int idParent;
	public SubPartModel(int id, String name, String description, int quantity, int idParent) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.idParent = idParent;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIdParent() {
		return idParent;
	}
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}	
	
	@Override
	public String toString(){		
		return 	"\nId: " + this.id +
				"\nName: " + this.name +
				"\nDescription: " + this.description +
				"\nId parent: " + this.idParent +
				"\nQuantity: " + this.quantity;	
	}
}
