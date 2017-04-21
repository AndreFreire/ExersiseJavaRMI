import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {
    public String helloTo(String name) throws RemoteException;
    public String savePart(PartModel part) throws RemoteException;
    public String getPart(int id) throws RemoteException;
    public String deletePart(int id) throws RemoteException;
    public ArrayList<PartModel> listParts() throws RemoteException;
    public ArrayList<PartModel> listSubPartsById(int id) throws RemoteException;
    public String cleanSubParts(int id) throws RemoteException;
    public String addSubPart(PartModel part) throws RemoteException;
}