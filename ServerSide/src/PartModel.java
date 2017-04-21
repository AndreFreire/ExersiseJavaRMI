import java.util.ArrayList;

public class PartModel {
	
	int id;
	String name;
	String description;
	ArrayList<PartModel> subParts = new ArrayList<PartModel>();
	boolean isPrimitive;
	int quantity;	
	
	public PartModel(int id, String name, String description, ArrayList<PartModel> subParts, boolean isPrimitive,
			int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.subParts = subParts;
		this.isPrimitive = isPrimitive;
		this.quantity = quantity;
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

	public ArrayList<PartModel> getSubParts() {
		return subParts;
	}

	public void setSubParts(ArrayList<PartModel> subParts) {
		this.subParts = subParts;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public void setPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantidade) {
		this.quantity = quantidade;
	}

	public void addSubPart(PartModel subPart){
		this.subParts.add(subPart);
	}
	
	public PartModel getSubPartById(int id){
		for(int i=0; i<this.subParts.size(); i++){
			if(subParts.get(i).getId() == id){
				return subParts.get(i);
			}
		}
		return null;
	}
	
	public void deleteSubPartById(int id){
		for(int i=0; i<this.subParts.size(); i++){
			if(subParts.get(i).getId() == id){
				subParts.remove(i);
			}
		}
	}
}
