
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class ClientOperation {

	private static RMIInterface look_up;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException {
		
		String host;
		if (args.length == 0) host = "localhost:1099";
		else host = args[0];
		String[] names = Naming.list("//" + host + "/");
		for (int i = 0; i < names.length; i++)
			System.out.println(names[i]);
		

		look_up = (RMIInterface) Naming.lookup("//localhost/MyServer");
		
		
		String txt = JOptionPane.showInputDialog("What is your name?");

		String response = look_up.helloTo(txt);
		JOptionPane.showMessageDialog(null, response);

	}

}
