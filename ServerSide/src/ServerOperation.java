import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface{

    private static final long serialVersionUID = 1L;
    
    ArrayList<PartModel> savedParts = new ArrayList<PartModel>();
    ArrayList<SubPartModel> savedSubParts = new ArrayList<SubPartModel>();
    String NOT_FOUND = "Not found";
    String SUCCESS = "Success";
	private static RMIInterface look_up;
    
    protected ServerOperation() throws RemoteException {
        super();
    }

    @Override
    public String helloTo(String name) throws RemoteException{    	
    	System.err.println(name + " is trying to contact!");        
        return "Server says hello to " + name;
    }
    public static void main(String[] args){
        try {
            Naming.rebind("//localhost/"+ args[0], new ServerOperation());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public String savePart(PartModel part) throws RemoteException {
		this.savedParts.add(part);
		return SUCCESS;
	}

	@Override
	public String getPart(int partId) throws RemoteException {
		for(int i=0; i<this.savedParts.size(); i++){
			if(this.savedParts.get(i).getId() == partId){
				return this.savedParts.get(i).toString(); 
			}
		}
		return NOT_FOUND;
	}

	@Override
	public String deletePart(int partId) throws RemoteException {
		for(int i=0; i<this.savedParts.size(); i++){
			if(this.savedParts.get(i).getId() == partId){
				this.savedParts.remove(i);
				return SUCCESS;
			}
		}
		return NOT_FOUND;
	
	}

	@Override
	public String listParts() throws RemoteException {
		String result = "";
		for(int i=0; i<this.savedParts.size(); i++){
			result = result + savedParts.get(i).toString();
		} 
		return result;
	}

	@Override
	public String listSubPartsById(int idPart) throws RemoteException {
		String result = "";
		for(int i=0; i<this.savedSubParts.size(); i++){
			if(savedSubParts.get(i).getIdParent() == idPart){
				result = result + savedSubParts.get(i).toString();
			}
		}
		return result;
	}

	@Override
	public String listRemoteSubPartsById(int idPart) throws RemoteException, MalformedURLException, NotBoundException {
		String result = "";
		result = result + listSubPartsById(idPart);
		
		String[] serverNames = getAllServers();
		for(int i=0; i< serverNames.length; i++){
			look_up = (RMIInterface) Naming.lookup(serverNames[i]);
			result = result + look_up.listSubPartsById(idPart);
		}
		
		if(result.equals("")){
			result = NOT_FOUND;
		}
		
		return result;
	}

	@Override
	public void cleanSubParts(int idPart) throws RemoteException {
		for(int i=0; i<this.savedSubParts.size(); i++){
			if(savedSubParts.get(i).getIdParent() == idPart){
				savedSubParts.remove(i);
			}
		}
	}
	
	@Override
	public String cleanRemoteSubParts(int idPart) throws RemoteException, MalformedURLException, NotBoundException {
		cleanSubParts(idPart);
		
		String[] serverNames = getAllServers();
		for(int i=0; i< serverNames.length; i++){
			look_up = (RMIInterface) Naming.lookup(serverNames[i]);
			look_up.cleanSubParts(idPart);
		}
		return SUCCESS;
	}

	@Override
	public String addSubPart(SubPartModel subPart) throws RemoteException {
		this.savedSubParts.add(subPart);
		return SUCCESS;
	}

	@Override
	public String DeleteSubPart(int idSubPart) throws RemoteException {
		for(int i=0; i<this.savedParts.size(); i++){
			if(this.savedSubParts.get(i).getId() == idSubPart){
				this.savedSubParts.remove(i);
				return SUCCESS;
			}
		}
		return NOT_FOUND;
	}
	
	public String[] getAllServers() throws RemoteException, MalformedURLException{
		return Naming.list("//localhost:1099/");		
	}

}