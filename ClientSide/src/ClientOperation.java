
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientOperation {

	private static RMIInterface look_up;
	public static String currentServer = "/";
	public static int currentPart = 0;
	public static int currentSubPart = 0;
	public static Scanner scanner = new Scanner(System.in);;
	static String command;

	public static void main(String[] args)
			throws MalformedURLException, RemoteException, NotBoundException {

		boolean continueRunning = true;

		System.out.println("Iniciando cliente...");
		while (continueRunning) {
			System.out.println();
			System.out.println("Digite o numero da ação que deseja fazer");
			System.out.println("1 - Listar os repositórios disponiveis");
			System.out.println("2 - Conectar ao repositório");
			System.out.println("3 - Adicionar uma nova peça");
			System.out.println("4 - Adicionar uma nova subpeça");
			System.out.println("5 - Listar peças do repositório");
			System.out.println("6 - Listar subpeças");
			System.out.println("7 - Limpar subpeças");
			System.out.println("8 - Selecionar peça");
			System.out.println("9 - Selecionar subpeça");
			System.out.println("10 - Mostrar peça atual");
			System.out.println("11 - Mostrar subpeça atual");
			System.out.println("12 - Sair");
			System.out.println("--------------");
			System.out.println("Repositório atual: " + currentServer);
			System.out.println("Parte atual: " + currentPart);
			System.out.println("Subarte atual: " + currentSubPart);
			System.out.println("--------------");

			command = scanner.nextLine();

			switch(command){
			case "1":
				listServers();
				scanner.nextLine();
				break;
			case "2":
				connectServer();
				scanner.nextLine();
				break;
			case "3":
				addPart();
				scanner.nextLine();
				break;
			case "4":
				addSubPart();
				scanner.nextLine();
				break;
			case "5":
				listRepositoryParts();
				scanner.nextLine();
				break;
			case "6":
				listSubParts();
				scanner.nextLine();
				break;
			case "7":
				clearSubParts();
				scanner.nextLine();
				break;
			case "8":
				selectPart();
				scanner.nextLine();
				break;
			case "9":
				selectSubPart();
				scanner.nextLine();
				break;
			case "10":
				showPart();
				scanner.nextLine();
				break;
			case "11":
				showSubPart();
				scanner.nextLine();
				break;				
			case "12":
				continueRunning = false;
				break;					
			default:
				System.out.println("Comando não encontrado");
			}

		}
	}


	private static void showSubPart() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		if(currentPart == 0){
			System.out.println("Peça não selecionada");
			return;
		}
		if(currentSubPart == 0){
			System.out.println("Subpeça não selecionada");
			return;
		}
		System.out.println(look_up.getSubPart(currentPart));
	}

	private static void showPart() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		if(currentPart == 0){
			System.out.println("Peça não selecionada");
			return;
		}
		System.out.println(look_up.getPart(currentPart));
		
	}

	private static void listSubParts() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		if(currentPart == 0){
			System.out.println("Peça não selecionada");
			return;
		}
		System.out.println(look_up.listSubPartsById(currentPart));


	}

	private static void connectServer() throws RemoteException, MalformedURLException, NotBoundException {
		System.out.println("Digite o nome do server");
		String serverName = scanner.nextLine();
		String[] names = Naming.list("//localhost:1099/");
		String aux = currentServer;

		for (int i = 0; i < names.length; i++){
			System.out.println(names[i]);
			if(serverName.equals(names[i])){
				currentServer = names[i];
				look_up = (RMIInterface) Naming.lookup(currentServer);
				currentPart = 0;
				currentSubPart = 0;
			}
		}
		if(currentServer.equals(aux)){
			System.out.println("Repositório não encontrado");
		}else{
			System.out.println("Repositório atual: " + currentServer);
		}
	}

	private static void selectSubPart() throws RemoteException, MalformedURLException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		if(currentPart == 0){
			System.out.println("Peça não selecionada");
			return;
		}
		System.out.println("Digite o codigo da subpeça");
		int idSubPart = scanner.nextInt();
		if(look_up.verifySubPart(idSubPart)){
			currentSubPart = idSubPart;
			System.out.println("Subpeça selecionada");
		}else{
			System.out.println("SubPeça não encontrada");
		}	
	}

	private static void selectPart() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		System.out.println("Digite o codigo da peça");
		int idPart = scanner.nextInt();
		if(look_up.verifyPart(idPart)){
			currentPart = idPart;
			currentSubPart = 0;
			System.out.println("Peça selecionada");
		}else{
			System.out.println("Peça não encontrada");
		}			
	}

	private static void clearSubParts() throws RemoteException, MalformedURLException, NotBoundException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		if(currentPart == 0){
			System.out.println("Peça não selecionada");
			return;
		}
		look_up.cleanRemoteSubParts(currentPart);
		System.out.println("Subpeças limpadas");
	}

	private static void listRepositoryParts() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		System.out.println(look_up.listParts());		
	}

	private static void addPart() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}
		System.out.println("Digite os valores: <<codigo>>/<<nome>>/<<descricao>>/<<éPrimitivo(0 ou 1)>>");
		String part = scanner.nextLine();
		String[] splitPart = part.split("/");
		if(splitPart.length != 4){
			System.out.println("Entrada inválida");
			return;
		}
		try {
			int idPart = Integer.parseInt(splitPart[0]);
			int primitive = Integer.parseInt(splitPart[3]);
			boolean isPrimitive;
			if(primitive == 1){
				isPrimitive = true;
			}else{
				if(primitive == 0){
					isPrimitive = false;
				}else{
					System.out.println("Entrada inválida");
					return;
				}
			}
			System.out.println(look_up.savePart(part));
			currentPart = idPart;

		}catch (NumberFormatException e) {
			System.out.println("Entrada inválida");
			return;
		}
	}

	private static void addSubPart() throws RemoteException {
		if(currentServer.equals("/")){
			System.out.println("Repositório não selecionado");
			return;
		}		
		System.out.println("Digite os valores: <<codigo>>/<<nome>>/<<descricao>>/<<quantidade>>/<<codigo peça pai>>");
		String part = scanner.nextLine();
		String[] splitPart = part.split("/");
		if(splitPart.length != 5){
			System.out.println("Entrada inválida");
			return;
		}
		try {
			int idSubPart = Integer.parseInt(splitPart[0]);
			int parent = Integer.parseInt(splitPart[4]);
			if(look_up.verifyPart(parent)){
				System.out.println(look_up.addSubPart(part));
				currentSubPart = idSubPart;
			}else{
				System.out.println("Peça não encontrada");
			}
		}catch (NumberFormatException e) {
			System.out.println("Entrada inválida");
			return;
		}

	}

	private static void listServers() throws RemoteException, MalformedURLException {
		String[] names = Naming.list("//localhost:1099/");
		System.out.println("Os servidores disponiveis são:");
		for (int i = 0; i < names.length; i++)
			System.out.println("\t" + names[i]);		

	}

}
