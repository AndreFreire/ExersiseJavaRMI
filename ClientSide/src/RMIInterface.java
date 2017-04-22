import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    public String helloTo(String name) throws RemoteException;
    public String savePart(PartModel part) throws RemoteException;
    public String getPart(int id) throws RemoteException;
    public String deletePart(int id) throws RemoteException;
    public String listParts() throws RemoteException;
    public String listSubPartsById(int id) throws RemoteException;
    public String listRemoteSubPartsById(int id) throws RemoteException, MalformedURLException, NotBoundException;
    public void cleanSubParts(int idParent) throws RemoteException;
    public String addSubPart(SubPartModel subPart) throws RemoteException;
    public String DeleteSubPart(int idSubPart) throws RemoteException;
	String cleanRemoteSubParts(int idPart) throws RemoteException, MalformedURLException, NotBoundException;
	boolean verifyPart(int idPart) throws RemoteException;
	boolean verifySubPart(int idSubPart) throws RemoteException;
}